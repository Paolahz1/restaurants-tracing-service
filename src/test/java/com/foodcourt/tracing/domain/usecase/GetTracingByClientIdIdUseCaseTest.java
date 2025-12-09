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

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTracingByClientIdIdUseCaseTest {


    @Mock
    IOrderTracingPersistencePort persistencePort;

    @InjectMocks
    GetTracingByClientIdIdUseCase useCase;


    @Test
    void shouldReturnTracingListForClientAndOrder() {

        Long clientId = 1L;
        Long orderId = 10L;

        OrderTracing trace1 = OrderTracing.builder()
                .id("evt1")
                .orderId(orderId)
                .clientId(clientId)
                .status("CREATED")
                .timestamp(Instant.now())
                .build();

        OrderTracing trace2 = OrderTracing.builder()
                .id("evt2")
                .orderId(orderId)
                .clientId(clientId)
                .status("PREPARING")
                .timestamp(Instant.now())
                .build();

        when(persistencePort.findByClientIdAndOrderId(clientId, orderId))
                .thenReturn(List.of(trace1, trace2));

        List<OrderTracing> result = useCase.getTracingForClient(clientId, orderId);

        assertEquals(2, result.size());
        assertEquals("evt1", result.get(0).getId());
        assertEquals("evt2", result.get(1).getId());

        verify(persistencePort).findByClientIdAndOrderId(clientId, orderId);
    }

    @Test
    void shouldReturnEmptyListWhenPersistenceReturnsNull() {

        Long clientId = 1L;
        Long orderId = 99L;

        when(persistencePort.findByClientIdAndOrderId(clientId, orderId))
                .thenReturn(null);

        List<OrderTracing> result = useCase.getTracingForClient(clientId, orderId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoEvents() {

        Long clientId = 2L;
        Long orderId = 50L;

        when(persistencePort.findByClientIdAndOrderId(clientId, orderId))
                .thenReturn(List.of());

        List<OrderTracing> result = useCase.getTracingForClient(clientId, orderId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(persistencePort).findByClientIdAndOrderId(clientId, orderId);
    }

}