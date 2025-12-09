package com.foodcourt.tracing.domain.usecase;

import com.foodcourt.tracing.domain.api.IGetTracingByClientIdServicePort;
import com.foodcourt.tracing.domain.model.OrderTracing;
import com.foodcourt.tracing.domain.spi.IOrderTracingPersistencePort;

import java.util.List;

public class GetTracingByClientIdIdUseCase implements IGetTracingByClientIdServicePort {

    private final IOrderTracingPersistencePort persistencePort;

    public GetTracingByClientIdIdUseCase(IOrderTracingPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public List<OrderTracing> getTracingForClient(Long clientId, Long orderId) {
        List<OrderTracing> tracingList = persistencePort.findByClientIdAndOrderId(clientId, orderId);
        return tracingList != null ? tracingList : List.of();
    }
}
