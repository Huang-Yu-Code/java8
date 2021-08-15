# Java Message Service

* P2P(Point To Point)
    * 点对点消息传输模消息提供者提供消息给先进先出的的消息队列,消息消费者从消息队列获取自己对应的消息.
* Pub/Sub
    * 消息提供者发布消息到Topic(主题),topic发布消息给所有订阅的消费者. 注意:发布订阅模式下,需要先订阅

JMS组成结构
* JMS provider JMS消息中间件(ActiveMQ,rabbitMQ,kafka等)
* JMS producer 消息生产者
* JMS consumer 消息消费者
* JMS message 消息

JMS的可靠性
* 持久化
* 事务
* ACK
  * 自动确认
  * 手动确认

## [ActiveMQ](./activemq)

## [RabbitMQ](./rabbitmq)

## [Kafka](./kafka)