package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.Mockito.*;

public class CheckInVehicleUseCaseTest {

    private ParkingSpotRepository spotRepository;
    private ParkingTicketRepository ticketRepository;
    private CheckInVehicleUseCase useCase;

    @BeforeEach
    void setUp() {
        spotRepository = mock(ParkingSpotRepository.class);
        ticketRepository = mock(ParkingTicketRepository.class);
        useCase = new CheckInVehicleUseCase(spotRepository, ticketRepository);
    }

    @Test
    void shouldCheckInVehicleSuccessfully() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);
        when(ticketRepository.findActiveTicketByPlate(any())).thenReturn(null);
        when(spotRepository.findAvailableSpot()).thenReturn(spot);

        ParkingTicket ticket = useCase.execute("LD-23-45-AB");

        assertNotNull(ticket);
        assertEquals(SpotStatus.OCCUPIED, spot.getStatus());
        verify(ticketRepository, times(1)).save(ticket);
        verify(spotRepository, times(1)).save(spot);
    }

    @Test
    void shouldThrowIfVehicleAlreadyParked() {
        when(ticketRepository.findActiveTicketByPlate(any())).thenReturn(mock(ParkingTicket.class));
        assertThrows(IllegalStateException.class, () -> useCase.execute("LD-23-45-AB"));
    }

    @Test
    void shouldThrowIfNoAvailableSpots() {
        when(ticketRepository.findActiveTicketByPlate(any())).thenReturn(null);
        when(spotRepository.findAvailableSpot()).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> useCase.execute("LD-23-45-AB"));
    }

}
