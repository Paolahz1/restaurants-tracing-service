package com.foodcourt.tracing.infrastructure.output.mongo.mapper;

import com.foodcourt.tracing.domain.model.OrderTracing;
import com.foodcourt.tracing.infrastructure.output.mongo.entity.OrderTracingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderTracingMapper {

    OrderTracing toDomain(OrderTracingEntity entity);

    OrderTracingEntity toEntity(OrderTracing orderTracing);
}
