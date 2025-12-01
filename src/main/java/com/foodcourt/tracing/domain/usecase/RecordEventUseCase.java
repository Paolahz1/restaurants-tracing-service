package com.foodcourt.tracing.domain.usecase;

import com.foodcourt.tracing.domain.api.IRecordEventServicePort;
import com.foodcourt.tracing.domain.model.OrderTracing;
import com.foodcourt.tracing.domain.spi.IOrderTracingPersistencePort;

public class RecordEventUseCase implements IRecordEventServicePort {

    private final IOrderTracingPersistencePort persistencePort;

    public RecordEventUseCase(IOrderTracingPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void save(OrderTracing tracingEvent) {
        persistencePort.save(tracingEvent);
    }

}
