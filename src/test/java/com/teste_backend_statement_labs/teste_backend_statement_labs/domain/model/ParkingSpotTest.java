package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingSpotTest {

    @Test
    void shouldCreateSpot() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);
        assertEquals(SpotStatus.FREE, spot.getStatus());
        assertEquals("A1", spot.getCode());
    }

    @Test
    void shouldOccupySpot() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);
        spot.occupy();
        assertEquals(SpotStatus.OCCUPIED, spot.getStatus());
    }

    @Test
    void shouldThrowIfOccupyAlreadyOccupied() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.OCCUPIED);
        assertThrows(IllegalStateException.class, spot::occupy);
    }

    @Test
    void shouldFreeSpot() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.OCCUPIED);
        spot.free();
        assertEquals(SpotStatus.FREE, spot.getStatus());
    }

    @Test
    void shouldThrowIfFreeAlreadyFree() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", SpotStatus.FREE);
        assertThrows(IllegalStateException.class, spot::free);
    }

    @Test
    void shouldThrowIfCodeIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingSpot(1L, null, SpotStatus.FREE));
        assertThrows(IllegalArgumentException.class, () -> new ParkingSpot(1L, "", SpotStatus.FREE));
    }
}
