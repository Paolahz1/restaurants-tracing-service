package com.foodcourt.tracing.domain.api;


import com.foodcourt.tracing.domain.model.OrderTracing;

/**
 * Registra un evento de cambio de estado del pedido.
 */
public interface IRecordEventServicePort {
    void save(OrderTracing tracingEvent);

}
