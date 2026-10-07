$GEMFIRE_HOME/bin/gfsh -e "connect" -e "execute function --id=ClearRegionRemoveAllFunction --region=/Alert"
$GEMFIRE_HOME/bin/gfsh -e "connect" -e "execute function --id=ClearRegionRemoveAllFunction --region=/Activity"
$GEMFIRE_HOME/bin/gfsh -e "connect" -e "execute function --id=ClearRegionRemoveAllFunction --region=/spring-ai-gemfire-index"
$GEMFIRE_HOME/bin/gfsh -e "connect" -e "execute function --id=ClearRegionRemoveAllFunction --region=/SearchResults"


echo "Clearing Valkey"

valkey-cli --scan --pattern "my-chat:agent-demo:*" | xargs -r valkey-cli UNLINK


echo "Clearing Greenplum"
PGPASSWORD=$LC_GREENPLUM_PASSWORD psql -h localhost -p 15432 -U $LC_GREENPLUM_USER -d postgres -c "delete from fraud.activity_entity;"
