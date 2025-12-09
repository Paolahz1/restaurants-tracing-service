package com.foodcourt.tracing.domain.usecase;

import com.foodcourt.tracing.domain.api.IGetTracingByRestaurantServicePort;
import com.foodcourt.tracing.domain.model.OrderTracing;
import com.foodcourt.tracing.domain.spi.IOrderTracingPersistencePort;

import java.util.List;

public class GetTracingByRestaurantUseCase implements IGetTracingByRestaurantServicePort {

    public GetTracingByRestaurantUseCase(IOrderTracingPersistencePort tracingPersistencePort) {
        this.tracingPersistencePort = tracingPersistencePort;
    }

    private final IOrderTracingPersistencePort tracingPersistencePort;

    @Override
    public List<OrderTracing> getTracingForRestaurant(Long restaurantId) {
        List<OrderTracing> orderTracingList = tracingPersistencePort.findByRestaurantId(restaurantId);
        return orderTracingList != null ? orderTracingList : List.of();
    }


}
