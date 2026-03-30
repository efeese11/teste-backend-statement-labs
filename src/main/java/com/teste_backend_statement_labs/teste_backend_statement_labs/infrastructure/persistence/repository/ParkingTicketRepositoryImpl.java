package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.repository;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.mapper.ParkingTicketMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingTicketRepositoryImpl implements ParkingTicketRepository {


    private final JpaParkingTicketRepository jpaRepository;

    public ParkingTicketRepositoryImpl(JpaParkingTicketRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ParkingTicket findById(Long id) {
        return jpaRepository.findById(id)
                .map(ParkingTicketMapper::toDomain)
                .orElse(null);
    }

    @Override
    public ParkingTicket findActiveTicketByPlate(Plate plate) {
        return jpaRepository.findByPlateAndExitTimeIsNull(plate.getValue())
                .map(ParkingTicketMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void save(ParkingTicket ticket) {
        jpaRepository.save(ParkingTicketMapper.toEntity(ticket));
    }

    @Override
    public List<ParkingTicket> findAll() {
        return jpaRepository.findAll().stream()
                .map(ParkingTicketMapper::toDomain)
                .toList();
    }

    @Override
    public List<ParkingTicket> findBySpotStatus(SpotStatus status) {
        return jpaRepository.findAll().stream()
                .map(ParkingTicketMapper::toDomain)
                .filter(ticket -> ( ticket).getSpot().getStatus() == status)
                .toList();
    }
}
