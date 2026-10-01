#!/bin/bash
PROJECT_HOME=$PWD

cd $GEMFIRE_HOME/bin

$GEMFIRE_HOME/bin/gfsh -e "start locator --name=locator-fraud --port=10334 --J=-Dgemfire.prometheus.metrics.emission=Default --J=-Dgemfire.prometheus.metrics.port=7977 --J=-Dgemfire.prometheus.metrics.host=127.0.0.1 --J=-Dgemfire.prometheus.metrics.interval=15s --bind-address=127.0.0.1 --J=-Dgemfire.enable-management-rest-service=true --J=-Dgemfire.enable-cluster-configuration=true  --initial-heap=512m --max-heap=512m  --J=-Dgemfire.start-rest-api=true --J=-Dgemfire.tcp-port=40000"

$GEMFIRE_HOME/bin/gfsh -e "connect" -e "configure pdx --read-serialized=true --disk-store "

$GEMFIRE_HOME/bin/gfsh -e "start server --name=server1-fraud --locators=127.0.0.1[10334] --initial-heap=2g --max-heap=2g --server-port=2881 --J=-Dgemfire.prometheus.metrics.emission=Default --J=-Dgemfire.prometheus.metrics.port=7971 --J=-Dgemfire.tcp-port=40001 --J=-Dgemfire.prometheus.metrics.host=127.0.0.1 --J=-Dgemfire.prometheus.metrics.interval=15s --bind-address=127.0.0.1  --http-service-port=7080 --J=-Dgemfire.enable-management-rest-service=true --J=-Dgemfire.enable-cluster-configuration=true --J=-XX:+AlwaysPreTouch --start-rest-api=true " &

$GEMFIRE_HOME/bin/gfsh -e "start server --name=server2-fraud --locators=127.0.0.1[10334] --initial-heap=2g --max-heap=2g --server-port=2882 --J=-Dgemfire.prometheus.metrics.emission=Default --J=-Dgemfire.prometheus.metrics.port=7972 --J=-Dgemfire.tcp-port=40002 --J=-Dgemfire.prometheus.metrics.host=127.0.0.1 --J=-Dgemfire.prometheus.metrics.interval=15s --bind-address=127.0.0.1  --http-service-port=7082 --J=-Dgemfire.enable-management-rest-service=true --J=-Dgemfire.enable-cluster-configuration=true --J=-XX:+AlwaysPreTouch --start-rest-api=true"&



# -------------------------------------------------------------------------
# Wait Logic: Poll until both servers are visible in the member list
# -------------------------------------------------------------------------
echo "Waiting for GemFire servers to be fully online and recognized..."
MAX_ATTEMPTS=30
ATTEMPT=1

while [ $ATTEMPT -le $MAX_ATTEMPTS ]; do
  # Get the member list from gfsh
  MEMBER_LIST=$($GEMFIRE_HOME/bin/gfsh -e "connect" -e "list members")

  # Check if both server names appear in the member list output
  if echo "$MEMBER_LIST" | grep -q "server1-fraud" && echo "$MEMBER_LIST" | grep -q "server2-fraud"; then
    echo "All servers are successfully online!"
    break
  fi

  echo "Servers not fully ready yet. Attempt $ATTEMPT/$MAX_ATTEMPTS. Waiting 5 seconds..."
  sleep 5
  ATTEMPT=$((ATTEMPT + 1))
done

if [ $ATTEMPT -gt $MAX_ATTEMPTS ]; then
  echo "Error: Timed out waiting for GemFire servers to start. Exiting script."
  exit 1
fi


$GEMFIRE_HOME/bin/gfsh -e "connect" -e "create region --name=Alert --type=PARTITION --skip-if-exists"
$GEMFIRE_HOME/bin/gfsh -e "connect" -e "create region --name=Activity --type=PARTITION --skip-if-exists"
$GEMFIRE_HOME/bin/gfsh -e "connect" -e "create region --name=SearchResults --type=PARTITION --skip-if-exists"

$GEMFIRE_HOME/bin/gfsh -e "connect" -e "deploy --jar=$PWD/deployments/local/dataServices/gemfire/libs/gemfire-clear-region-function-1.0.1.jar"

