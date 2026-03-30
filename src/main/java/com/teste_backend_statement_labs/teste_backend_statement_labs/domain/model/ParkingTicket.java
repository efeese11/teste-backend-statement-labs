package com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket {

    private final Long id;
    private final Plate plate;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal amount;

    public ParkingTicket(Long id, Plate plate, ParkingSpot spot) {

        this.id = id;
        this.plate = plate;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();

    }

    public Long getId() {
        return id;
    }

    public Plate getPlate() {
        return plate;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public void checkOut() {
        this.spot.free();
        this.amount = calculateAmount();
    }


    private BigDecimal calculateAmount() {

        if (exitTime == null) {

            exitTime = LocalDateTime.now();
        }

        long hours = Duration.between(entryTime, exitTime).toHours();
        hours = hours == 0 ? 1 : hours;

        BigDecimal amount = BigDecimal.ZERO;

        if (hours <= 6) {
            amount = BigDecimal.valueOf(hours).multiply(BigDecimal.valueOf(300));
        } else {
            amount = BigDecimal.valueOf(6 * 300)
                    .add(BigDecimal.valueOf((hours - 6) * 200));
        }
        return amount;
    }

    @Override
    public String toString() {
        return "ParkingTicket{id=" + id + ", plate=" + plate + ", spot=" + spot.getCode() +
                ", entryTime=" + entryTime + ", exitTime=" + exitTime + ", amount=" + amount + '}';
    }
}
