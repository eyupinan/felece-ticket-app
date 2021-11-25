package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.filter.RouteFilter;
import org.felecechallenge.ticket.model.Route;
import org.felecechallenge.ticket.model.Ticket;
import org.felecechallenge.ticket.repository.RouteRepository;
import org.felecechallenge.ticket.service.RouteService;
import org.felecechallenge.ticket.service.TicketService;
import org.felecechallenge.ticket.specification.RouteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private TicketService ticketService;
    public Route getRouteById(Long id){
        Route route = routeRepository.findById(id).get();
        return route;
    }
    public Page<Route> getRoutesQuery(RouteFilter filter){
        /*Date startDate = filter.getDate();
        Calendar cal =new GregorianCalendar();
        cal.setTime(startDate);
        cal.add(Calendar.DATE,1);
        Date endDate = cal.getTime();*/
        String sortby = filter.getSortBy();
        Integer limit =filter.getLimit();
        Integer offset = filter.getOffset();
        Pageable pageable = PageGenerator.generate(sortby,offset,limit);
        Page<Route> list =routeRepository.findAll(RouteSpecification.customRouteQuery(filter),pageable);
        return list;
    }
    public void saveRoute(Route route){
        this.routeRepository.save(route);
    }
    public void disableRoute(Long id){
        Route route = this.routeRepository.getById(id);
        route.setDisabled(true);
        this.saveRoute(route);
    }
    public void updateTicketParameters(Long routeId){
        Route route = this.getRouteById(routeId);
        Page<Ticket> ticketPage = this.ticketService.getRoutesActiveTickets(routeId);
        if (ticketPage.getTotalElements() >= route.getVehicle().getTotalSeatCount()){
            route.setFull(true);
        }
        else{
            route.setFull(false);
        }
        this.saveRoute(route);
    }
    public boolean isFull(Long id){
        Route route = this.getRouteById(id);
        if (route.getFull()==true){
            return true;
        }
        else{
            return false;
        }
    }
}
