package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.repository;

import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.entity.ParkingTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaParkingTicketRepository extends JpaRepository<ParkingTicketEntity, Long> {
    Optional<ParkingTicketEntity> findByPlateAndExitTimeIsNull(String plate);
}
