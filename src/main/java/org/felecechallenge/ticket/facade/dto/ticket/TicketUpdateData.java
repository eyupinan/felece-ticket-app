package org.felecechallenge.ticket.facade.dto.ticket;

import org.felecechallenge.ticket.enums.TicketState;

import javax.validation.constraints.NotNull;

public class TicketUpdateData {
    @NotNull
    private Long id;
    private Long routeId;
    private TicketState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }
}
