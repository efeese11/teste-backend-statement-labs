package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;

public class ParkingSpot {
    private final Long id;
    private final String code; // ex: A1
    private SpotStatus status;

    public ParkingSpot(Long id, String code, SpotStatus status) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Spot code cannot be null or empty");
        }
        this.id = id;
        this.code = code;
        this.status = status != null ? status : SpotStatus.FREE;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public SpotStatus getStatus() {
        return status;
    }


    public void occupy() {
        if (status == SpotStatus.OCCUPIED) {
            throw new IllegalStateException("Spot already occupied");
        }
        this.status = SpotStatus.OCCUPIED;
    }

    public void free() {
        if (status == SpotStatus.FREE) {
            throw new IllegalStateException("Spot already free");
        }
        this.status = SpotStatus.FREE;
    }

    @Override
    public String toString() {
        return "ParkingSpot{id=" + id + ", code='" + code + "', status=" + status + '}';
    }

}
