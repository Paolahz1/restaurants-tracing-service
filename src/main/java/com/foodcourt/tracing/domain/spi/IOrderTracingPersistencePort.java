package com.foodcourt.tracing.domain.spi;

import com.foodcourt.tracing.domain.model.OrderTracing;

import java.util.List;

public interface IOrderTracingPersistencePort {

    OrderTracing save(OrderTracing tracingEvent);

    List<OrderTracing> findByOrderId(Long orderId);

    List<OrderTracing> findByClientIdAndOrderId(Long clientId, Long orderId);

    List<OrderTracing> findByRestaurantId(Long restaurantId);

    List<OrderTracing> findAll();

}
