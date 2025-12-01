package com.foodcourt.tracing.infrastructure.configuration;

import com.foodcourt.tracing.domain.api.IGetTracingByClientIdServicePort;
import com.foodcourt.tracing.domain.api.IRecordEventServicePort;
import com.foodcourt.tracing.domain.spi.IOrderTracingPersistencePort;
import com.foodcourt.tracing.domain.usecase.GetTracingByClientIdIdUseCase;
import com.foodcourt.tracing.domain.usecase.RecordEventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderTracingConfiguration {

    private final IOrderTracingPersistencePort persistencePort;
    @Bean
    public IRecordEventServicePort recordEventServicePort(){
        return new RecordEventUseCase(persistencePort);
    }

    @Bean
    public IGetTracingByClientIdServicePort getTracingByOrderIdServicePort(){
        return new GetTracingByClientIdIdUseCase(persistencePort);
    }
}
