$GEMFIRE_HOME/bin/gfsh -e "connect" -e "shutdown --include-locators"


podman rm -f valkey tanzu-rabbitmq postgres ollama
docker rm -f greenplum
