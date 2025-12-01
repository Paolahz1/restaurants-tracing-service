package com.foodcourt.tracing.application.handler;

import com.foodcourt.tracing.application.dto.GetTracingByClientIdResponse;
import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.application.mapper.IGetTracingByOrderIdResponseMapper;
import com.foodcourt.tracing.application.mapper.IRecordEventMapper;
import com.foodcourt.tracing.domain.api.IGetTracingByClientIdServicePort;
import com.foodcourt.tracing.domain.api.IRecordEventServicePort;
import com.foodcourt.tracing.domain.model.OrderTracing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderTracingHandler implements IOrderTracingHandler{

    private final IRecordEventServicePort recordEventServicePort;
    private final IGetTracingByClientIdServicePort getTracingForClientServicePort;

    private final IRecordEventMapper recordEventMapper;
    private final IGetTracingByOrderIdResponseMapper getTracingMapper;

    @Override
    public void recordEvent(RecordEventCommand command) {
        OrderTracing orderTracing = recordEventMapper.toDomain(command);
        recordEventServicePort.save(orderTracing);
    }

    @Override
    public GetTracingByClientIdResponse getTracing(Long clientId) {
        List<OrderTracing> list = getTracingForClientServicePort.getTracingForClient(clientId);
        return getTracingMapper.toResponse(list);
    }
}
