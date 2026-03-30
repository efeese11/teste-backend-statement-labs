package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.repository;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.mapper.ParkingSpotMapper;
import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.mapper.ParkingTicketMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {

    private final JpaParkingSpotRepository jpaRepository;

    public ParkingSpotRepositoryImpl(JpaParkingSpotRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ParkingSpot findAvailableSpot() {
        return jpaRepository.findFirstByStatus(SpotStatus.FREE)
                .map(ParkingSpotMapper::toDomain)
                .orElse(null);
    }

    @Override
    public ParkingSpot findById(Long id) {
        return jpaRepository.findById(id)
                .map(ParkingSpotMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void save(ParkingSpot spot) {
        System.out.println("Saving spot: " + spot);
        jpaRepository.save(ParkingSpotMapper.toEntity(spot));

    }

    @Override
    public List<ParkingSpot> findAll() {
        return jpaRepository.findAll().stream()
                .map(ParkingSpotMapper::toDomain)
                .toList();
    }



}
