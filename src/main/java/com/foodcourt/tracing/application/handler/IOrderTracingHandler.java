package com.foodcourt.tracing.application.handler;

import com.foodcourt.tracing.application.dto.GetTracingByClientIdResponse;
import com.foodcourt.tracing.application.dto.RecordEventCommand;

public interface IOrderTracingHandler {

    void recordEvent(RecordEventCommand command);
    GetTracingByClientIdResponse getTracing(Long orderId);
}
