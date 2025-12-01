package com.foodcourt.tracing.domain.api;

import com.foodcourt.tracing.domain.model.OrderTracing;

import java.util.List;

/**
 * Devuelve la trazabilidad completa de un pedido
 */
public interface IGetTracingByOrderServicePort {

    List<OrderTracing> getTracingForOrder(Long orderId);

}
