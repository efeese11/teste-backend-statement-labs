package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingTicketTest {

    @Test
    void shouldCreateTicketAndOccupySpot() {
        Plate plate = new Plate("LD-23-45-AB");
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);

        ParkingTicket ticket = new ParkingTicket(1L, plate, spot);

        assertEquals(SpotStatus.OCCUPIED, spot.getStatus());
        assertNotNull(ticket.getEntryTime());
        assertNull(ticket.getExitTime());
        assertNull(ticket.getAmount());
    }

    @Test
    void shouldCheckOutAndCalculateAmountUpTo6Hours() {
        Plate plate = new Plate("LD-23-45-AB");
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);

        ParkingTicket ticket = new ParkingTicket(1L, plate, spot);

        // Simula 4 horas de permanência
        LocalDateTime fakeExit = ticket.getEntryTime().plusHours(4);
        ticketCheckOutWithFakeTime(ticket, fakeExit);

        assertEquals(SpotStatus.FREE, spot.getStatus());
        assertEquals(BigDecimal.valueOf(4 * 300), ticket.getAmount());
    }

    @Test
    void shouldCheckOutAndCalculateAmountAbove6Hours() {
        Plate plate = new Plate("LD-23-45-AB");
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);

        ParkingTicket ticket = new ParkingTicket(1L, plate, spot);

        // Simula 8 horas de permanência
        LocalDateTime fakeExit = ticket.getEntryTime().plusHours(8);
        ticketCheckOutWithFakeTime(ticket, fakeExit);

        assertEquals(SpotStatus.FREE, spot.getStatus());
        assertEquals(BigDecimal.valueOf(6*300 + 2*200), ticket.getAmount());
    }

    @Test
    void shouldThrowIfAlreadyCheckedOut() {
        Plate plate = new Plate("LD-23-45-AB");
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);
        ParkingTicket ticket = new ParkingTicket(1L, plate, spot);

        ticket.checkOut();
        assertThrows(IllegalStateException.class, ticket::checkOut);
    }

    // Método helper para simular saída em horário diferente
    private void ticketCheckOutWithFakeTime(ParkingTicket ticket, LocalDateTime fakeExitTime) {
        try {
            java.lang.reflect.Field exitTimeField = ParkingTicket.class.getDeclaredField("exitTime");
            exitTimeField.setAccessible(true);
            exitTimeField.set(ticket, fakeExitTime);

            java.lang.reflect.Method calculateAmountMethod = ParkingTicket.class.getDeclaredMethod("calculateAmount");
            calculateAmountMethod.setAccessible(true);
            BigDecimal amount = (BigDecimal) calculateAmountMethod.invoke(ticket);

            java.lang.reflect.Field amountField = ParkingTicket.class.getDeclaredField("amount");
            amountField.setAccessible(true);
            amountField.set(ticket, amount);

            ticket.getSpot().free();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
