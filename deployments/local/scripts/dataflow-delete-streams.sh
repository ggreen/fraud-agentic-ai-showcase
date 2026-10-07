#!/usr/bin/env bash
set -euo pipefail

# Configuration
SCDF_URL="${SCDF_URL:-http://localhost:9393}"

echo "Fetching all stream definitions from ${SCDF_URL}..."

# Retrieve all stream names (handles pagination if size is set large, defaults to page size 10000)
STREAM_NAMES=$(curl -s "${SCDF_URL}/streams/definitions?size=10000" \
  -H "Accept: application/json" \
  | jq -r '._embedded.streamDefinitionResourceList[]?.name // empty')

if [ -z "$STREAM_NAMES" ]; then
  echo "No streams found to destroy."
  exit 0
fi

echo "Found streams:"
echo "$STREAM_NAMES"
echo "----------------------------------------"

# Loop and destroy each stream
for STREAM_NAME in $STREAM_NAMES; do
  echo "Destroying stream: ${STREAM_NAME}..."

  RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE "${SCDF_URL}/streams/definitions/${STREAM_NAME}")

  if [ "$RESPONSE" -eq 200 ] || [ "$RESPONSE" -eq 204 ]; then
    echo "Successfully destroyed '${STREAM_NAME}' (HTTP ${RESPONSE})."
  else
    echo "Failed to destroy '${STREAM_NAME}' (HTTP ${RESPONSE})."
  fi
done

echo "Done."