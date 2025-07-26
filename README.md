# message-web
다양한 이벤트 message 기능을 이용한 메시징 기능


## 기능 개요
- REST API → Redis Pub/Sub → WebSocket 실시간 전송
- 메시지 로그 및 읽음 상태 MariaDB 저장

## 구조
- Redis 기반 메시지 큐
- 확장성 고려 (Kafka 등으로 교체 가능)

## 실행 방법
```bash
docker run -d -p 6379:6379 redis:7.2
./gradlew bootRun
