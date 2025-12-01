package com.foodcourt.tracing.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordEventCommand {

    private Long orderId;
    private Long clientId;
    private Long employeeId;
    private Long restaurantId;
    private String status;

}
