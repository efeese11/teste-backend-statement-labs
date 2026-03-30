package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.Mockito.*;

public class CheckOutVehicleUseCaseTest {

    private ParkingTicketRepository ticketRepository;
    private CheckOutVehicleUseCase useCase;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(ParkingTicketRepository.class);
        useCase = new CheckOutVehicleUseCase(ticketRepository);
    }

    @Test
    void shouldCheckOutVehicleSuccessfully() {
        Plate plate = new Plate("LD-23-45-AB");


        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);

        ParkingTicket ticket = new ParkingTicket(1L, plate, spot);


        when(ticketRepository.findActiveTicketByPlate(plate)).thenReturn(ticket);

        ParkingTicket checkedOut = useCase.execute("LD-23-45-AB");

        assertNotNull(checkedOut.getExitTime());
        assertEquals(SpotStatus.FREE, spot.getStatus());
        assertNotNull(checkedOut.getAmount());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void shouldThrowIfNoActiveTicket() {
        when(ticketRepository.findActiveTicketByPlate(any())).thenReturn(null);
        assertThrows(IllegalStateException.class, () -> useCase.execute("LD-23-45-AB"));
    }
}
