package com.foodcourt.tracing.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OrderTracing {

    private String id;
    private Long orderId;
    private Long clientId;
    private Long employeeId;
    private Long restaurantId;
    private String status;
    private Instant timestamp;

}

