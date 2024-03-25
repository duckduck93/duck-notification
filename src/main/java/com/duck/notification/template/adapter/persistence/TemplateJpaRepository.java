package com.duck.notification.template.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateJpaRepository extends JpaRepository<TemplateJpaEntity, String> {
}
