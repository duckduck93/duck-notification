package com.duck.notification.message.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface MessageLogRepository extends JpaRepository<MessageLogJpaEntity, Long> {
}
