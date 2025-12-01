package com.foodcourt.tracing.infrastructure.output.mongo.adapter;

import com.foodcourt.tracing.domain.model.OrderTracing;
import com.foodcourt.tracing.domain.spi.IOrderTracingPersistencePort;
import com.foodcourt.tracing.infrastructure.output.mongo.entity.OrderTracingEntity;
import com.foodcourt.tracing.infrastructure.output.mongo.mapper.IOrderTracingMapper;
import com.foodcourt.tracing.infrastructure.output.mongo.repository.IOrderTracingMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderTracingMongoAdapter implements IOrderTracingPersistencePort {

    private final IOrderTracingMongoRepository repository;
    private final IOrderTracingMapper mapper;


    @Override
    public OrderTracing save(OrderTracing tracingEvent) {
        OrderTracingEntity entity = mapper.toEntity(tracingEvent);
        OrderTracingEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<OrderTracing> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<OrderTracing> findByClientId(Long clientId) {
        return repository.findByClientId(clientId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<OrderTracing> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<OrderTracing> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
