package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;

import java.util.List;

public interface ParkingSpotRepository {

    ParkingSpot findAvailableSpot();
    ParkingSpot findById(Long id);
    void save(ParkingSpot spot);
    List<ParkingSpot> findAll();

}
