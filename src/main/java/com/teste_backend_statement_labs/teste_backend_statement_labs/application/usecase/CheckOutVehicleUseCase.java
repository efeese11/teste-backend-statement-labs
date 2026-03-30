package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.Plate;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingSpotRepository;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckOutVehicleUseCase {

    private final ParkingTicketRepository ticketRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    public CheckOutVehicleUseCase(ParkingTicketRepository ticketRepository, ParkingSpotRepository parkingSpotRepository) {
        this.ticketRepository = ticketRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingTicket execute(String plateValue) {
        Plate plate = new Plate(plateValue);


        ParkingTicket ticket = ticketRepository.findActiveTicketByPlate(plate);
        if (ticket == null) {
            throw new IllegalStateException("No active ticket for this vehicle");
        }


        ticket.checkOut();



        ticketRepository.save(ticket);
        parkingSpotRepository.save(ticket.getSpot());

        return ticket;
    }
}
