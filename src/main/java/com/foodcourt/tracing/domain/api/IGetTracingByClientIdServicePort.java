package com.foodcourt.tracing.domain.api;

import com.foodcourt.tracing.domain.model.OrderTracing;

import java.util.List;

/**
 * Devuelve todos los eventos de un pedido de un cliente.
 */

public interface IGetTracingByClientIdServicePort {

    List<OrderTracing> getTracingForClient(Long clientId, Long orderId);

}
