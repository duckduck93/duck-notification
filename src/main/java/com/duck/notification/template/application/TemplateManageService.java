package com.duck.notification.template.application;

import com.duck.notification.template.application.in.TemplateManageUseCase;
import com.duck.notification.template.application.out.TemplateManagePort;
import com.duck.notification.template.domain.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateManageService implements TemplateManageUseCase {
    private final TemplateManagePort templateManagePort;

    @Override
    @Transactional(readOnly = true)
    public <T extends Template<?>> Optional<T> searchById(String id) {
        return templateManagePort.findById(id);
    }

    @Override
    @Transactional
    public <T extends Template<?>> T create(T template) {
        return templateManagePort.create(template);
    }

    @Override
    @Transactional
    public <T extends Template<?>> T update(String id, T template) {
        return templateManagePort.update(id, template);
    }

    @Override
    @Transactional
    public void delete(String id) {
        templateManagePort.delete(id);
    }
}
