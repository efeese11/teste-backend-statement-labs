package com.teste_backend_statement_labs.teste_backend_statement_labs.application.usecase;

import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.model.ParkingTicket;
import com.teste_backend_statement_labs.teste_backend_statement_labs.domain.repository.ParkingTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListHistoryUseCase {

    private final ParkingTicketRepository ticketRepository;

    public ListHistoryUseCase(ParkingTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<ParkingTicket> execute() {
        return ticketRepository.findAll();
    }
}
