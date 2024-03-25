package com.duck.notification.template.application.out;

import com.duck.notification.template.domain.Template;

public interface TemplateManagePort {
    <T> Template<T> findById(String id);

    <T extends Template<?>> T create(T template);

    <T extends Template<?>> T update(T template);

    <T extends Template<?>> T delete(T template);
}
