package com.foodcourt.tracing.domain.usecase;

import com.foodcourt.tracing.domain.model.OrderTracing;
import com.foodcourt.tracing.domain.spi.IOrderTracingPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTracingByRestaurantUseCaseTest {

    @Mock
    IOrderTracingPersistencePort persistencePort;

    @InjectMocks
    GetTracingByRestaurantUseCase useCase;

    @Test
    void shouldReturnTracingListForRestaurant() {

        Long restaurantId = 5L;

        OrderTracing trace1 = OrderTracing.builder()
                .id("t1")
                .orderId(100L)
                .restaurantId(restaurantId)
                .status("CREATED")
                .timestamp(Instant.now())
                .build();

        OrderTracing trace2 = OrderTracing.builder()
                .id("t2")
                .orderId(101L)
                .restaurantId(restaurantId)
                .status("DELIVERED")
                .timestamp(Instant.now())
                .build();

        when(persistencePort.findByRestaurantId(restaurantId))
                .thenReturn(List.of(trace1, trace2));

        List<OrderTracing> result = useCase.getTracingForRestaurant(restaurantId);

        assertEquals(2, result.size());
        assertEquals("t1", result.get(0).getId());
        assertEquals("t2", result.get(1).getId());

        verify(persistencePort).findByRestaurantId(restaurantId);
    }

    @Test
    void shouldReturnEmptyListWhenNoTracingForRestaurant() {

        Long restaurantId = 999L;

        when(persistencePort.findByRestaurantId(restaurantId))
                .thenReturn(List.of());

        List<OrderTracing> result = useCase.getTracingForRestaurant(restaurantId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(persistencePort).findByRestaurantId(restaurantId);
    }


}