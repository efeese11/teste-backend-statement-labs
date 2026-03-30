package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListHistoryUseCaseTest {

    private ParkingTicketRepository ticketRepository;
    private ListHistoryUseCase useCase;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(ParkingTicketRepository.class);
        useCase = new ListHistoryUseCase(ticketRepository);
    }

    @Test
    void shouldReturnAllTickets() {

        ParkingSpot spot1 = new ParkingSpot(1L,"A1", SpotStatus.FREE);
        ParkingSpot spot2 = new ParkingSpot(2L,"B1", SpotStatus.FREE);
        ParkingTicket t1 = new ParkingTicket(1L, new Plate("LD-23-45-AB"), spot1);
        t1.checkOut(); // já saiu → histórico real

        ParkingTicket t2 = new ParkingTicket(2L, new Plate("LU-12-34-CD"), spot2);
        when(ticketRepository.findAll()).thenReturn(List.of(t1, t2));

        List<ParkingTicket> result = useCase.execute();

        assertEquals(2, result.size());
        assertTrue(result.contains(t1));
        assertTrue(result.contains(t2));
    }

}
