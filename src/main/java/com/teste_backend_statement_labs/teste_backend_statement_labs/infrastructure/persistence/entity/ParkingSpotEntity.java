package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.entity;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import jakarta.persistence.*;

@Entity
@Table()
public class ParkingSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    private SpotStatus status;

    public ParkingSpotEntity() {}

    public ParkingSpotEntity(Long id, String code, SpotStatus status) {
        this.id = id;
        this.code = code;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SpotStatus getStatus() {
        return status;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }
}
