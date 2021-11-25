package org.felecechallenge.ticket.service;

import org.felecechallenge.ticket.filter.RouteFilter;
import org.felecechallenge.ticket.model.Route;
import org.springframework.data.domain.Page;

public interface RouteService {
    Route getRouteById(Long id);
    Page<Route> getRoutesQuery(RouteFilter filter);
    public void saveRoute(Route route);
    public void disableRoute(Long id);
    public void updateTicketParameters(Long routeId);// this function works for updating route's ticket count and isFull parameter
    public boolean isFull(Long id);
}
