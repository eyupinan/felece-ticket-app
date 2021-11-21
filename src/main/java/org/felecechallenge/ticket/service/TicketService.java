package org.felecechallenge.ticket.service;

import org.felecechallenge.ticket.enums.TicketState;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.felecechallenge.ticket.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    public List<Ticket> getTicketsByUsername(String username);
    public Ticket getTicketsById(Long id);
    public Page<Ticket> getTicketswithFilter(TicketFilter filter);
    public void save(Ticket ticket);
    public void delete(Long id);
    public Page<Ticket> getRoutesActiveTickets(Long routeId);
}
