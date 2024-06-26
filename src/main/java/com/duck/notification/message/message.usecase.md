# 요구사항

- 수/발신자, 제목, 본문 등을 매개변수로 메시지 발송
- 템플릿, 템플릿 변수, 추가 수/발신자를 매개변수로 메시지 발송
- 다양한 방법의 메시징 기능 제공
    - Email, SMS, Push 서버에 직접 접근하여 발송
    - MQ를 활용하여 발송 -> Pub, Sub 방식 (Sub은 별도 인스턴스로도 생성할 수 있게 구성)
    - Kafka를 활용하여 발송 -> Pub, Sub 방식 (Sub은 별도 인스턴스로도 생성할 수 있게 구성)
    - DB를 활용하여 발송 -> 메시지 발송 후 status 수정

## UseCase

1. 메시지 발송요청 (MessageRequest) - MessageController
2. 메시지 발송
3. 메시지 발송 로그 생성

## 시스템 구성

```mermaid
flowchart LR
    Controller(Controller)
    RequestUseCase(MessageRequestUseCase)
    RequestPort(MessageRequestPort)
    SendUseCase(MessageSendUseCase)
    SendPort(MessageSendPort)
    DB[(Database)]
    MQ>RabbitMQ]
    Kafka>Kafka]
    Controller --> RequestUseCase
    Template --> RequestUseCase
    RequestUseCase --> RequestPort
    RequestPort -->|Direct| SendUseCase
    RequestPort --> DB & MQ & Kafka --> SendUseCase
    SendUseCase --> SendPort
    SendPort --> SMTP & SMS & PUSH
```
