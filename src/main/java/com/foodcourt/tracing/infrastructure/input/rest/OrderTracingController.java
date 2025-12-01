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

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<OrderTracing>> getOrderTracing(@PathVariable long clientId) {
        return ResponseEntity.ok(handler.getTracing(clientId));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderTracing>> getOrderTracingForRestaurant(@PathVariable long restaurantId) {
        return ResponseEntity.ok(handler.getTracingForRestaurant(restaurantId));
    }

}
