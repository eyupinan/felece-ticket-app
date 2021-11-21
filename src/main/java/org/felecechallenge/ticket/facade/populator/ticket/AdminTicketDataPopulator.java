package org.felecechallenge.ticket.facade.populator.ticket;

import org.felecechallenge.ticket.facade.dto.ticket.AdminTicketData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.Ticket;

public class AdminTicketDataPopulator implements Populator<Ticket, AdminTicketData> {
    @Override
    public void populate(Ticket ticket, AdminTicketData adminTicketData) {
        adminTicketData.setRouteId(ticket.getRoute().getId());
        adminTicketData.setUserId(ticket.getUser().getId());
    }
}
