package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingSpot;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckInVehicleUseCase {

    private final ParkingSpotRepository spotRepository;
    private final ParkingTicketRepository ticketRepository;

    public CheckInVehicleUseCase(ParkingSpotRepository spotRepository, ParkingTicketRepository ticketRepository) {
        this.spotRepository = spotRepository;
        this.ticketRepository = ticketRepository;
    }

    public ParkingTicket execute(String plateValue) {
        Plate plate = new Plate(plateValue);


        if (ticketRepository.findActiveTicketByPlate(plate) != null) {
            throw new IllegalStateException("Vehicle is already parked");
        }


        ParkingSpot spot = spotRepository.findAvailableSpot();
        spot.occupy();
        if (spot == null) {
            throw new IllegalStateException("No available parking spots");
        }


        ParkingTicket ticket = new ParkingTicket(null, plate, spot);


        ticketRepository.save(ticket);
        spotRepository.save(spot);

        return ticket;
    }

}
