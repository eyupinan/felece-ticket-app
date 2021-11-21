package org.felecechallenge.ticket.controller.admin.rest;

import org.felecechallenge.ticket.facade.RouteFacade;
import org.felecechallenge.ticket.facade.dto.route.AdminRouteData;
import org.felecechallenge.ticket.facade.dto.route.AdminRouteTransferData;
import org.felecechallenge.ticket.filter.RouteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/route")
public class AdminRouteRestController {
    @Autowired
    private RouteFacade routeFacade;

    @GetMapping
    public AdminRouteTransferData getRoutes(RouteFilter filter){
        AdminRouteTransferData data = routeFacade.getRouteDataforAdmin(filter);
        return data;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRoute(@RequestBody AdminRouteData routeData){
        routeFacade.saveRoute(routeData);
    }
    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRoute( @RequestBody AdminRouteData routeData, @PathVariable(value="id") Long id){
        routeFacade.updateRoute(id, routeData);
    }
    @DeleteMapping(value="/{id}")
    public void deleteRoute(@PathVariable(value="id") Long id){
        routeFacade.deleteRoute(id);
    }
}
