package com.teste_backend_statement_labs.teste_backend_statement_labs.presentation.controller;

import com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase.CheckInVehicleUseCase;
import com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase.CheckOutVehicleUseCase;
import com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase.ListActiveTicketsUseCase;
import com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase.ListHistoryUseCase;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final CheckInVehicleUseCase checkIn;
    private final CheckOutVehicleUseCase checkOut;
    private final ListHistoryUseCase listHistory;
    private final ListActiveTicketsUseCase listActive;

    public ParkingController(CheckInVehicleUseCase checkIn,
                            CheckOutVehicleUseCase checkOut,
                            ListHistoryUseCase listHistory,
                            ListActiveTicketsUseCase listActive) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.listHistory = listHistory;
        this.listActive = listActive;
    }

    @Operation(summary = "Registrar entrada de veículo")
    @PostMapping("/check-in")
    public ParkingTicket checkIn(@RequestParam String plate) {
        return checkIn.execute(plate);
    }

    @Operation(summary = "Registrar saída de veículo")
    @PostMapping("/check-out")
    public ParkingTicket checkOut(@RequestParam String plate) {
        return checkOut.execute(plate);
    }

    @Operation(summary = "Vaiculos ativos")
    @GetMapping("/active")
    public List<ParkingTicket> getActiveTickets() {
        return listActive.execute();
    }

    @Operation(summary = "Historico de registro")
    @GetMapping("/history")
    public List<ParkingTicket> getHistory() {
        return listHistory.execute();
    }
}
