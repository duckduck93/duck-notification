package com.duck.notification.template.adapter.web;

import com.duck.notification.core.web.NotFoundException;
import com.duck.notification.template.application.in.TemplateManageUseCase;
import com.duck.notification.template.domain.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class TemplateController {
    private final TemplateManageUseCase templateManageUseCase;

    @GetMapping("/templates/{id}")
    public TemplateExchange searchTemplates(@PathVariable(value = "id") String id) {
        return templateManageUseCase.searchById(id)
                .map(TemplateExchange::from)
                .orElseThrow(() -> new NotFoundException(Template.class, id));
    }

    @PostMapping("/templates")
    public TemplateExchange createTemplate(@RequestBody TemplateExchange request) {
        return TemplateExchange.from(templateManageUseCase.create(request.toTemplate()));
    }

    @PutMapping("/templates/{id}")
    public TemplateExchange updateTemplate(@PathVariable(value = "id") String id, @RequestBody TemplateExchange request) {
        return TemplateExchange.from(templateManageUseCase.update(id, request.toTemplate(id)));
    }

    @DeleteMapping("/templates/{id}")
    public void deleteTemplate(@PathVariable(value = "id") String id) {
        templateManageUseCase.delete(id);
    }
}
