Postman Collection - https://github.com/chandan13tiwari/kafka-poc/blob/master/kafka-poc.postman_collection.json

Docker command to run zookeeper - docker run -p 2181:2181 zookeeper

Docker command to run Kafka - docker run -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=<your_ip_add>:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://<your_ip_add>:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka

