package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.enums.TicketState;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.felecechallenge.ticket.model.Ticket;
import org.felecechallenge.ticket.repository.TicketRepository;
import org.felecechallenge.ticket.service.RouteService;
import org.felecechallenge.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.felecechallenge.ticket.specification.TicketSpecification;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    protected TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUsername(String username){
        return ticketRepository.findByUser_Username(username);
    }
    public Ticket getTicketsById(Long id){
        return this.ticketRepository.findById(id).get();
    }
    public Page<Ticket> getTicketswithFilter(TicketFilter filter){
        Pageable pageable = PageGenerator.generate(filter.getSortBy(),filter.getOffset(),filter.getLimit());
        Page<Ticket> ticketPage = ticketRepository.findAll(TicketSpecification.customTicketQuery(filter),pageable);
        return ticketPage;
    }
    public void save(Ticket ticket){

        this.ticketRepository.save(ticket);
    }
    public void delete(Long id){
        this.ticketRepository.deleteById(id);
    }


    public Page<Ticket> getRoutesActiveTickets(Long routeId) {
        Page<Ticket> ticketPage = this.ticketRepository.findAll(TicketSpecification.routesActiveTickets(routeId),
                                                                PageRequest.of(0,Integer.MAX_VALUE));
        return ticketPage;
    }
}
