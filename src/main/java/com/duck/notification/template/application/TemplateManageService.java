package com.duck.notification.template.application;

import com.duck.notification.template.application.in.TemplateManageUseCase;
import com.duck.notification.template.application.out.TemplateManagePort;
import com.duck.notification.template.domain.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateManageService implements TemplateManageUseCase {
    private final TemplateManagePort templateManagePort;

    @Override
    public <T extends Template<?>> T create(T template) {
        return templateManagePort.create(template);
    }

    @Override
    public <T extends Template<?>> T update(T template) {
        return templateManagePort.update(template);
    }

    @Override
    public <T extends Template<?>> T delete(T template) {
        return templateManagePort.delete(template);
    }
}
