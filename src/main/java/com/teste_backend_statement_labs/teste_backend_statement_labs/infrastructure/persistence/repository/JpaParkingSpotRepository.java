package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.repository;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.entity.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaParkingSpotRepository extends JpaRepository<ParkingSpotEntity, Long> {
    Optional<ParkingSpotEntity> findFirstByStatus(SpotStatus status);
}
