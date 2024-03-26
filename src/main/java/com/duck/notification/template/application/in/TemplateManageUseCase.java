package com.duck.notification.template.application.in;

import com.duck.notification.template.domain.Template;

import java.util.Optional;

public interface TemplateManageUseCase {
    <T extends Template<?>> Optional<T> searchById(String id);

    <T extends Template<?>> T create(T template);

    <T extends Template<?>> T update(T template);

    <T extends Template<?>> T delete(T template);

}
