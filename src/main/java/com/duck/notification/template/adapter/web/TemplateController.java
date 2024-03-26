package com.duck.notification.template.adapter.web;

import com.duck.notification.template.application.in.TemplateManageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class TemplateController {
    private final TemplateManageUseCase templateManageUseCase;

    @PostMapping("/templates")
    public void createTemplate(@RequestBody TemplateExchange request) {
        templateManageUseCase.create(request.toTemplate());
    }

    @GetMapping("/templates/{id}")
    public TemplateExchange searchTemplates(@PathVariable(value = "id") String id) {
        return templateManageUseCase.searchById(id)
                .map(TemplateExchange::from)
                .orElseThrow();
    }
}
