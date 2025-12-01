package com.foodcourt.tracing.application.handler;

import com.foodcourt.tracing.application.dto.RecordEventCommand;

public interface IOrderTracingHandler {

    void recordEvent(RecordEventCommand command);
}
