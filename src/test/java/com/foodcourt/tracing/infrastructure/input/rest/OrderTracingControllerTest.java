package com.foodcourt.tracing.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.application.handler.IOrderTracingHandler;
import com.foodcourt.tracing.domain.model.OrderTracing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    void shouldSaveOrderTracing() throws Exception {

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

    @Test
    void shouldGetOrderTracing() throws Exception {
        long clientId = 1L;

        // Mock de la respuesta usando builder
        OrderTracing event1 = OrderTracing.builder().id("evt-1").orderId(100L)
                .clientId(clientId).employeeId(10L)
                .restaurantId(5L).status("CREATED")
                .timestamp(Instant.now())
                .build();

        OrderTracing event2 = OrderTracing.builder().id("evt-2").orderId(101L)
                .clientId(clientId).employeeId(11L)
                .restaurantId(5L).status("PREPARING")
                .timestamp(Instant.now())
                .build();


        when(handler.getTracing(clientId)).thenReturn(List.of(event1, event2));

        mockMvc.perform(
                        get("/tracing-service/{clientId}", clientId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("evt-1"))
                .andExpect(jsonPath("$[0].clientId").value((int) clientId))
                .andExpect(jsonPath("$[0].status").value("CREATED"))
                .andExpect(jsonPath("$[1].id").value("evt-2"))
                .andExpect(jsonPath("$[1].status").value("PREPARING"));

        verify(handler).getTracing(clientId);
    }

    @Test
    void shouldGetOrderTracingByRestaurantId() throws Exception {
        long restaurantId = 5L;

        // Mock de la respuesta usando builder
        OrderTracing event1 = OrderTracing.builder()
                .id("evt-1")
                .orderId(100L)
                .clientId(1L)
                .employeeId(10L)
                .restaurantId(restaurantId)
                .status("CREATED")
                .timestamp(Instant.now())
                .build();

        OrderTracing event2 = OrderTracing.builder()
                .id("evt-2")
                .orderId(101L)
                .clientId(2L)
                .employeeId(11L)
                .restaurantId(restaurantId)
                .status("PREPARING")
                .timestamp(Instant.now())
                .build();

        when(handler.getTracingForRestaurant(restaurantId)).thenReturn(List.of(event1, event2));

        mockMvc.perform(
                        get("/tracing-service/restaurant/{restaurantId}", restaurantId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("evt-1"))
                .andExpect(jsonPath("$[0].restaurantId").value((int) restaurantId))
                .andExpect(jsonPath("$[0].status").value("CREATED"))
                .andExpect(jsonPath("$[1].id").value("evt-2"))
                .andExpect(jsonPath("$[1].status").value("PREPARING"));

        verify(handler).getTracingForRestaurant(restaurantId);
    }
}