package org.felecechallenge.ticket.facade.dto.ticket;

public class AdminTicketData extends UserTicketData{
    private Long userId;
    private Long routeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}
