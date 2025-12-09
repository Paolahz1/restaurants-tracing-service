package com.foodcourt.tracing.infrastructure.input.rest;

import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.application.handler.IOrderTracingHandler;
import com.foodcourt.tracing.domain.model.OrderTracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracing-service")
@RequiredArgsConstructor
public class OrderTracingController {

    private final IOrderTracingHandler handler;

    @PostMapping("/")
    public ResponseEntity<Void> saveOrderTracing(@RequestBody RecordEventCommand command) {
        handler.recordEvent(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/client/{clientId}/order/{orderId}")
    public ResponseEntity<List<OrderTracing>> getOrderTracingByClientAndOrder(@PathVariable Long clientId, @PathVariable Long orderId) {
        return ResponseEntity.ok(handler.getTracingForClientAndOrderId(clientId, orderId));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderTracing>> getOrderTracingForRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(handler.getTracingForRestaurant(restaurantId));
    }

}
