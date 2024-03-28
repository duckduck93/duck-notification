package com.duck.notification.template.adapter.persistence;

import com.duck.notification.core.annotation.Adapter;
import com.duck.notification.template.application.out.TemplateManagePort;
import com.duck.notification.template.domain.Template;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
class TemplatePersistenceAdapter implements TemplateManagePort {
    private final TemplateJpaRepository templateJpaRepository;

    @Override
    public <T extends Template<?>> Optional<T> findById(String id) {
        return templateJpaRepository.findById(id)
                .map(TemplateJpaEntity::toTemplate);
    }

    @Override
    public <T extends Template<?>> T create(T template) {
        return templateJpaRepository.save(TemplateJpaEntity.fromTemplate(template)).toTemplate();
    }

    @Override
    public <T extends Template<?>> T update(String id, T template) {
        return templateJpaRepository.save(TemplateJpaEntity.fromTemplate(template)).toTemplate();
    }

    @Override
    public void delete(String id) {
        templateJpaRepository.deleteById(id);
    }
}
