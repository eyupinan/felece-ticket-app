package org.felecechallenge.ticket.facade.populator.route;

import org.felecechallenge.ticket.facade.dto.route.UserRouteData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.Route;

public class RouteInfoDataPopulator implements Populator<Route, UserRouteData> {
    @Override
    public void populate(Route route, UserRouteData routeData) {
        routeData.setEndDestinationName(route.getEndDestination().getName());
        routeData.setStartDestinationName(route.getStartDestination().getName());
        routeData.setDate(route.getDate());
        routeData.setPrice(route.getPrice());
        routeData.setFull(route.getFull());
        routeData.setCreatedAt(route.getCreatedAt());
        routeData.setUpdatedAt(route.getUpdatedAt());
        routeData.setId(route.getId());
    }
}
