package com.foodcourt.tracing.application.handler;

import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.application.mapper.IRecordEventMapper;
import com.foodcourt.tracing.domain.api.IRecordEventServicePort;
import com.foodcourt.tracing.domain.model.OrderTracing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderTracingHandler implements IOrderTracingHandler{

    private final IRecordEventServicePort recordEventServicePort;

    private final IRecordEventMapper recordEventMapper;

    @Override
    public void recordEvent(RecordEventCommand command) {
        OrderTracing orderTracing = recordEventMapper.toDomain(command);
        recordEventServicePort.save(orderTracing);
    }
}
