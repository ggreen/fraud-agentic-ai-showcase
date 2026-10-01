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
fraud-stream=http --path-pattern=activities --port=8555 | alert-ai-agent --spring.ai.ollama.chat.options.model=llama3 --spring.profiles.active=cf  | alert-sink
```

```properties
app.http.spring.rabbitmq.username=vmware
app.http.spring.rabbitmq.password=tanzu
app.alert-sink.spring.rabbitmq.username=vmware
app.alert-sink.spring.rabbitmq.password=tanzu
app.alert-ai-agent.spring.rabbitmq.username=vmware
app.alert-ai-agent.spring.rabbitmq.password=tanzu
```

```shell
activities-stream=:fraud-stream.http > activity-sink
```

```properties
app.activity-sink.spring.rabbitmq.username=vmware
app.activity-sink.spring.rabbitmq.password=tanzu
```



Activities Testing
```shell
./deployments/local/scripts/post-activites.sh
```



Demo Storyboard: Real-Time Fraud & Alert Remediation

1. Real-Time Detection: An agent continuously intercepts account activities in real time. 
2. Risk Scoring: Leveraging an AI model, incoming events are dynamically scored into HIGH, MEDIUM, or LOW severity risk tiers. 
3. Low-Latency Alerting: Alerts and active policies are served directly from Tanzu GemFire to ensure millisecond response times. 
4. Context-Aware Recommendations: Through an AI chat assistant, users can request actionable remediation steps for flagged alerts. 
5. Unified Data Intelligence (MCP): Using Model Context Protocol (MCP) servers, the assistant contextually bridges real-time alert data in GemFire with long-term historical activity trends stored in Greenplum.


Script

1. Get Activities
```text
Provide list of current activities
```

```shell
./deployments/local/scripts/post-normal-activites.sh
```

Post Fraud Activities

```shell
./deployments/local/scripts/post-activites.sh
```

```text
what is the standard policy when there is a Series of very small transactions within 2 minutes
```


```text
The recommended policy to contact fraud@acme.immediately
```

```text
Provide a summary of our recent conversations
```

# Cleanup



```shell
./deployments/local/scripts/clean-all.sh 
```
