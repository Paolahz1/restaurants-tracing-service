package com.foodcourt.tracing.application.handler;

import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.domain.model.OrderTracing;

import java.util.List;

public interface IOrderTracingHandler {

    void recordEvent(RecordEventCommand command);
    List<OrderTracing> getTracingForClientAndOrderId(Long clientId, Long orderId);
    List<OrderTracing> getTracingForRestaurant(Long restauId);
}
