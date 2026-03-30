package com.teste_backend_statement_labs.teste_backend_statement_labs.presentation.controller;

import com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase.ListAvailableSpotsUseCase;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parking/spots")
public class SpotController {

    private final ListAvailableSpotsUseCase listAvailableSpots;

    public SpotController(ListAvailableSpotsUseCase listAvailableSpots) {
        this.listAvailableSpots = listAvailableSpots;
    }


    @GetMapping
    public List<ParkingSpot> getAllSpots() {
        return listAvailableSpots.execute();
    }


    @GetMapping("/available")
    public List<ParkingSpot> getAvailableSpots() {
        return listAvailableSpots.execute();
    }
}
