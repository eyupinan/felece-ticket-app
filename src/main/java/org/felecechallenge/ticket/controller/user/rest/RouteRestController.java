package org.felecechallenge.ticket.controller.user.rest;

import org.felecechallenge.ticket.facade.RouteFacade;
import org.felecechallenge.ticket.facade.dto.route.UserRouteTransferData;
import org.felecechallenge.ticket.filter.RouteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteRestController {
    @Autowired
    private RouteFacade routeFacade;

    @GetMapping("/api/route")
    public UserRouteTransferData getRoutes(RouteFilter filter){
        UserRouteTransferData data = routeFacade.getRouteDataforUser(filter);
        return data;
    }


}
