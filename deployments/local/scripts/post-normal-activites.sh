curl -X 'POST' \
  'http://localhost:8555/activities' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id" :  "11",
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
  "id" :  "12",
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
  "id" :  "13",
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
  "id" :  "14",
  "account" : "imani",
  "icon" : "fa-wallet",
  "time" : "06:31 PM",
  "activity" : "Visa credit card download to digital wallet"
}'