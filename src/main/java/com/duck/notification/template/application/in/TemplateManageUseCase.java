package com.duck.notification.template.application.in;

import com.duck.notification.template.domain.Template;

public interface TemplateManageUseCase {
    <T extends Template<?>> T create(T template);

    <T extends Template<?>> T update(T template);

    <T extends Template<?>> T delete(T template);

}
