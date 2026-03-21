package com.teste_backend_statement_labs.teste_backend_statement_labs.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class ParkingTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private ParkingSpotEntity spot;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private BigDecimal amount;

    public ParkingTicketEntity() {}

    public ParkingTicketEntity(Long id, String plate, ParkingSpotEntity spot, LocalDateTime entryTime, LocalDateTime exitTime, BigDecimal amount) {
        this.id = id;
        this.plate = plate;
        this.spot = spot;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public ParkingSpotEntity getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpotEntity spot) {
        this.spot = spot;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @PreUpdate
    public void onUpdate() {
        if (exitTime == null) {
            exitTime = LocalDateTime.now();
        }
    }
}
