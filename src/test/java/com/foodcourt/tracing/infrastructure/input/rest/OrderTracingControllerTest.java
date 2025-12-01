package com.foodcourt.tracing.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.application.handler.IOrderTracingHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderTracingController.class)
class OrderTracingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    IOrderTracingHandler handler;

    @Test
    void saveOrderTracing() throws Exception {

        RecordEventCommand command = RecordEventCommand.builder()
                .orderId(1L)
                .build();

        mockMvc.perform(
                post("/tracing-service/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command))
        ).andExpect(status().isCreated());

        verify(handler).recordEvent(command);
    }
}