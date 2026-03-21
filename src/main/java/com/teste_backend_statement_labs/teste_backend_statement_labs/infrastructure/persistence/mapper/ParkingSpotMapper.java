package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.mapper;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.entity.ParkingSpotEntity;

public class ParkingSpotMapper {
    public static ParkingSpot toDomain(ParkingSpotEntity entity) {
        return new ParkingSpot(entity.getId(), entity.getCode(), entity.getStatus());
    }

    public static ParkingSpotEntity toEntity(ParkingSpot domain) {
        return new ParkingSpotEntity(domain.getId(), domain.getCode(), domain.getStatus());
    }
}
