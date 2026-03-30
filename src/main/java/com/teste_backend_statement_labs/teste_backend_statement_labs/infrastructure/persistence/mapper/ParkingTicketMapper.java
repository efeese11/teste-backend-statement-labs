package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.mapper;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;
import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.entity.ParkingTicketEntity;

public class ParkingTicketMapper {

    public static ParkingTicket toDomain(ParkingTicketEntity entity) {
        ParkingSpot spot = ParkingSpotMapper.toDomain(entity.getSpot());
        Plate plate = new Plate(entity.getPlate());

        ParkingTicket ticket = new ParkingTicket(
                entity.getId(),
                plate,
                spot
        );

        return ticket;
    }

    public static ParkingTicketEntity toEntity(ParkingTicket domain) {
        ParkingTicketEntity entity = new ParkingTicketEntity();

        entity.setId(domain.getId());
        entity.setPlate(domain.getPlate().getValue());
        entity.setSpot(ParkingSpotMapper.toEntity(domain.getSpot()));
        entity.setEntryTime(domain.getEntryTime());
        entity.setExitTime(domain.getExitTime());
        entity.setAmount(domain.getAmount());

        return entity;
    }
}
