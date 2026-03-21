package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;

import java.util.List;

public interface ParkingTicketRepository {

    ParkingTicket findById(Long id);
    ParkingTicket findActiveTicketByPlate(Plate plate);
    void save(ParkingTicket ticket);
    List<ParkingTicket> findAll();
    List<ParkingTicket> findBySpotStatus(SpotStatus status);
}
