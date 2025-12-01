package com.foodcourt.tracing.infrastructure.input.rest;

import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.application.handler.IOrderTracingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
