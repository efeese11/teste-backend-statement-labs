package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListAvailableSpotsUseCaseTest {

    private ParkingSpotRepository spotRepository;
    private ListAvailableSpotsUseCase useCase;

    @BeforeEach
    void setUp() {
        spotRepository = mock(ParkingSpotRepository.class);
        useCase = new ListAvailableSpotsUseCase(spotRepository);
    }

    @Test
    void shouldReturnOnlyFreeSpots() {
        ParkingSpot freeSpot = new ParkingSpot(1L, "A1", SpotStatus.FREE);
        ParkingSpot occupiedSpot = new ParkingSpot(2L, "B1", SpotStatus.OCCUPIED);

        when(spotRepository.findAll()).thenReturn(List.of(freeSpot, occupiedSpot));

        List<ParkingSpot> available = useCase.execute();

        assertEquals(1, available.size());
        assertEquals(freeSpot, available.get(0));
    }
}
