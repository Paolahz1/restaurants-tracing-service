package com.foodcourt.tracing.application.mapper;

import com.foodcourt.tracing.application.dto.GetTracingByClientIdResponse;
import com.foodcourt.tracing.domain.model.OrderTracing;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGetTracingByOrderIdResponseMapper {

    default GetTracingByClientIdResponse toResponse(List<OrderTracing> orderTracing) {
        return GetTracingByClientIdResponse.builder()
                .events(orderTracing)
                .build();

    }
}
