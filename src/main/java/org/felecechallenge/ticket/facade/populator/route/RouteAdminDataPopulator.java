package org.felecechallenge.ticket.facade.populator.route;

import org.felecechallenge.ticket.facade.dto.route.AdminRouteData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.Route;

public class RouteAdminDataPopulator implements Populator<Route, AdminRouteData> {
    @Override
    public void populate(Route route, AdminRouteData adminRouteData) {
        adminRouteData.setVehiclePlate(route.getVehicle().getPlate());
        adminRouteData.setCreatedAt(route.getCreatedAt());
        adminRouteData.setUpdatedAt(route.getUpdatedAt());
        adminRouteData.setStartDestinationId(route.getStartDestination().getId());
        adminRouteData.setEndDestinationId(route.getEndDestination().getId());
        adminRouteData.setVehicleId(route.getVehicle().getId());
    }
}