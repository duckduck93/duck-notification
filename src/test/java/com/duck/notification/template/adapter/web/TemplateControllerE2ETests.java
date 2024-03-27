package com.duck.notification.template.adapter.web;

import com.duck.notification.message.domain.Message;
import com.duck.notification.message.domain.Receiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class TemplateControllerE2ETests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("CREATE TEMPLATE_001")
    void _001_createEmailTemplate() throws Exception {
        TemplateExchange request = new TemplateExchange(
                "TEMPLATE_001",
                "TEMPLATE_NAME_001",
                Message.Type.EMAIL,
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                "sender@test.com",
                List.of(
                        new ReceiverExchange("receiver1@test.com", Receiver.Type.TO),
                        new ReceiverExchange("receiver2@test.com", Receiver.Type.CC)
                )
        );
        String requestBody = objectMapper.writeValueAsString(request);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/templates")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(requestBody));
    }

    @Test
    @DisplayName("SEARCH CREATED TEMPLATE_001")
    void _002_searchEmailTemplate() throws Exception {
        String id = "TEMPLATE_001";
        TemplateExchange request = new TemplateExchange(
                "TEMPLATE_001",
                "TEMPLATE_NAME_001",
                Message.Type.EMAIL,
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                "sender@test.com",
                List.of(
                        new ReceiverExchange("receiver1@test.com", Receiver.Type.TO),
                        new ReceiverExchange("receiver2@test.com", Receiver.Type.CC)
                )
        );
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/templates/{id}", id)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(requestBody));
    }

    @Test
    @DisplayName("UPDATE TEMPLATE_001")
    void _003_updateEmailTemplate() throws Exception {
        String id = "TEMPLATE_001";
        TemplateExchange request = new TemplateExchange(
                id,
                "TEMPLATE_NAME_UPDATED",
                Message.Type.EMAIL,
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                "sender-updated@test.com",
                List.of(
                        new ReceiverExchange("receiver1-updated@test.com", Receiver.Type.TO),
                        new ReceiverExchange("receiver2-updated@test.com", Receiver.Type.CC)
                )
        );
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/templates/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(requestBody));
    }

    @Test
    @DisplayName("SEARCH UPDATED TEMPLATE_001")
    void _004_searchEmailTemplate() throws Exception {
        String id = "TEMPLATE_001";
        TemplateExchange request = new TemplateExchange(
                id,
                "TEMPLATE_NAME_UPDATED",
                Message.Type.EMAIL,
                "<h1>#{header}</h1><p>#{variable1}</p><p>#{variable2}</p>",
                "sender-updated@test.com",
                List.of(
                        new ReceiverExchange("receiver1-updated@test.com", Receiver.Type.TO),
                        new ReceiverExchange("receiver2-updated@test.com", Receiver.Type.CC)
                )
        );
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/templates/{id}", id)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(requestBody));
    }

    @Test
    @DisplayName("DELETE TEMPLATE_001")
    void _005_deleteEmailTemplate() throws Exception {
        String id = "TEMPLATE_001";
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/templates/{id}", id)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("SEARCH DELETED TEMPLATE_001")
    void _006_searchEmailTemplate() throws Exception {
        String id = "TEMPLATE_001";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/templates/{id}", id)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("[Template](TEMPLATE_001) not found")));
    }
}
