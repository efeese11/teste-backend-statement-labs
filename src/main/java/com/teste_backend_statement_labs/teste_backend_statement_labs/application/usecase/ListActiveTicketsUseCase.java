package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.enums.SpotStatus;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListActiveTicketsUseCase {

    private final ParkingTicketRepository ticketRepository;

    public ListActiveTicketsUseCase(ParkingTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<ParkingTicket> execute() {
        List<ParkingTicket> allTickets = ticketRepository.findBySpotStatus(SpotStatus.OCCUPIED);
        return allTickets.stream()
                .filter(ticket -> ticket.getExitTime() == null)
                .collect(Collectors.toList());
    }
}
