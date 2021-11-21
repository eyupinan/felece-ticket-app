package org.felecechallenge.ticket.facade.populator.ticket;

import org.felecechallenge.ticket.facade.dto.ticket.UserTicketData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.Ticket;

public class UserTicketDataPopulator implements Populator<Ticket, UserTicketData> {
    @Override
    public void populate(Ticket ticket, UserTicketData ticketData) {
        ticketData.setDate(ticket.getRoute().getDate());
        ticketData.setEndDestination(ticket.getRoute().getEndDestination().getName());
        ticketData.setStartDestination(ticket.getRoute().getStartDestination().getName());
        ticketData.setState(ticket.getState());
        ticketData.setUserName(ticket.getUser().getUsername());
        ticketData.setPrice(ticket.getPrice());
        ticketData.setCreatedAt(ticket.getCreatedAt());
        ticketData.setUpdatedAt(ticket.getUpdatedAt());
        ticketData.setId(ticket.getId());

    }
}
