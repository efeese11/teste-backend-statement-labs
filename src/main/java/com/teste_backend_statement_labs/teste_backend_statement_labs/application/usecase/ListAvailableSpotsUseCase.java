package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAvailableSpotsUseCase {

    private final ParkingSpotRepository spotRepository;

    public ListAvailableSpotsUseCase(ParkingSpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public List<ParkingSpot> execute() {
        List<ParkingSpot> allSpots = spotRepository.findAll(); // precisa adicionar findAll no repositório
        return allSpots.stream()
                .filter(spot -> spot.getStatus() == SpotStatus.FREE)
                .collect(Collectors.toList());
    }
}
