package org.felecechallenge.ticket.facade.dto.ticket;

import org.felecechallenge.ticket.enums.TicketState;

import javax.validation.constraints.NotNull;

public class TicketPurchaseData {
    @NotNull
    private Long routeId;
    private Long userId;
    @NotNull
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
