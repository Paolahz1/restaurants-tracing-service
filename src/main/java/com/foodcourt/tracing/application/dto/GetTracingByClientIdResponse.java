package com.foodcourt.tracing.application.dto;

import com.foodcourt.tracing.domain.model.OrderTracing;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetTracingByClientIdResponse {
    private List<OrderTracing> events;
}
