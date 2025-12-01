package com.foodcourt.tracing.domain.api;

import com.foodcourt.tracing.domain.model.OrderTracing;

import java.util.List;

/**
 * Devuelve todos los eventos de todos los pedidos de un restaurante.
 */

public interface IGetTracingByRestaurantServicePort {

    List<OrderTracing> getTracingForRestaurant(Long restaurantId);

}
