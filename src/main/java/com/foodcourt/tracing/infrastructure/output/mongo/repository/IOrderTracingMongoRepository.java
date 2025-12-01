package com.foodcourt.tracing.infrastructure.output.mongo.repository;

import com.foodcourt.tracing.infrastructure.output.mongo.entity.OrderTracingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IOrderTracingMongoRepository extends MongoRepository<OrderTracingEntity, String> {

    List<OrderTracingEntity> findByOrderId(Long orderId);

    List<OrderTracingEntity> findByClientId(Long clientId);

    List<OrderTracingEntity> findByRestaurantId(Long restaurantId);
}
