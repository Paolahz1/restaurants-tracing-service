package com.foodcourt.tracing.infrastructure.output.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order_tracing")
public class OrderTracingEntity {

    @Id
    private String id;

    private Long orderId;
    private Long clientId;
    private Long employeeId;
    private Long restaurantId;
    private String status;
    private Instant timestamp;
}