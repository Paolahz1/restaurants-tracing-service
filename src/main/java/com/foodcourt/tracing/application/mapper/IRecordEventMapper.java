package com.foodcourt.tracing.application.mapper;

import com.foodcourt.tracing.application.dto.RecordEventCommand;
import com.foodcourt.tracing.domain.model.OrderTracing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRecordEventMapper {

    OrderTracing toDomain (RecordEventCommand command);
}
