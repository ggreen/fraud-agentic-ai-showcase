# fraud-agentic-ai-showcase
fraud-agentic-ai-showcase

![img.png](img.png)

Demo

```shell
deployments/local/dataFlow/print-app-properties.sh
```

Vector Pipeline

```shell
vector-stream=http --port=7888| vector-sink  --spring.profiles.active=cf
```


```properties
app.http.spring.rabbitmq.username=vmware
app.http.spring.rabbitmq.password=tanzu
app.vector-sink.spring.rabbitmq.username=vmware
app.vector-sink.spring.rabbitmq.password=tanzu
```

```shell
alerts-stream=http --path-pattern=activities --port=8555| alert-ai-agent --spring.ai.ollama.chat.options.model=llama3 --spring.profiles.active=cf | alert-sink
```

```properties
app.http.spring.rabbitmq.username=vmware
app.http.spring.rabbitmq.password=tanzu
app.alert-sink.spring.rabbitmq.username=vmware
app.alert-sink.spring.rabbitmq.password=tanzu
app.alert-ai-agent.spring.rabbitmq.username=vmware
app.alert-ai-agent.spring.rabbitmq.password=tanzu
```


Activities Testing
```shell
curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id" :  "1", 
  "account" : "imani",
  "icon" : "fa-credit-card",
  "time" : "06:30 PM", 
  "activity" : "Opened new credit card account"
}'
curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id" :  "2", 
  "account" : "imani",
  "icon" : "fa-file-invoice",
  "time" : "06:31 PM", 
  "activity" : "Account statements shipped to home address"
}'

curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id" :  "3", 
  "account" : "imani",
  "icon" : "fa-hand-holding-dollar",
  "time" : "06:33 PM", 
  "activity" : "Wire transfer of amount above threshold"
}'
curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id" :  "4", 
  "account" : "imani",
  "icon" : "fa-wallet",
  "time" : "06:31 PM", 
  "activity" : "Visa credit card download to digital wallet"
}'


curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "70", "icon" : "fa-credit-card", "account" : "josiah", "time" : "07:15 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 100.00, date: 1-7-2026 19:12:34 terminal_id: TERM_88291, merchant_id: MERCH_55432" }'

curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "71", "icon" : "fa-credit-card",  "account" : "josiah", "time" :  "07:17 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 1000.01, date: ''1-7-2026 19:17:32'' terminal_id: TERM_88291, merchant_id: MERCH_5555" }'

curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "72", "icon" : "fa-temperature-low",  "account" : "josiah", "time" : "07:19 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 1.01, date: ''1-7-2026 19:19:32'' terminal_id: TERM_88291, merchant_id: MERCH_55432"}' 
   
curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "73", "icon" : "fa-credit-card",  "account" : "josiah", "time" : "07:17 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 0.01, date: ''1-7-2026 19:17:32'' terminal_id: TERM_88291, merchant_id: MERCH_55432" }'
  
curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "74", "icon" : "fa-credit-card",  "account" : "josiah", "time" : "07:19 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 1.01, date: ''1-7-2026 19:19:32'' terminal_id: TERM_88291, merchant_id: MERCH_55432" }'
  
curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "75", "icon" : "fa-credit-card",  "account" : "josiah", "time" :  "07:20 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 5.01, date: ''1-7-2026 19:20:32'' terminal_id: TERM_88291, merchant_id: MERCH_5555" }'

curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{ "id" : "76", "icon" : "fa-credit-card",  "account" : "josiah", "time" : "07:15 PM", "activity" : "type: SALE, pan: 4111XXXXXX1111, amount: 100.00, date: ''1-7-2026 19:12:34'' terminal_id: TERM_88291, merchant_id: MERCH_55432" }'
```