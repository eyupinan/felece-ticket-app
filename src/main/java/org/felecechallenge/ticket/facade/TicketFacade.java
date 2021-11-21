package org.felecechallenge.ticket.facade;

import org.felecechallenge.ticket.enums.TicketState;
import org.felecechallenge.ticket.exception.BadRequestException;
import org.felecechallenge.ticket.exception.ConflictException;
import org.felecechallenge.ticket.exception.NotFoundException;
import org.felecechallenge.ticket.facade.converter.Converter;
import org.felecechallenge.ticket.facade.dto.ticket.*;
import org.felecechallenge.ticket.facade.populator.ticket.AdminTicketDataPopulator;
import org.felecechallenge.ticket.facade.populator.ticket.UserTicketDataPopulator;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.felecechallenge.ticket.model.Route;
import org.felecechallenge.ticket.model.Ticket;
import org.felecechallenge.ticket.model.User;
import org.felecechallenge.ticket.service.RouteService;
import org.felecechallenge.ticket.service.TicketService;
import org.felecechallenge.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketFacade {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private UserService userService;
    private Converter usetTicketConverter = new Converter(UserTicketData.class);
    private Converter adminTicketConverter = new Converter(AdminTicketData.class);
    public TicketFacade(){
        this.usetTicketConverter.addPopulator(new UserTicketDataPopulator());
        this.adminTicketConverter.addPopulator(new UserTicketDataPopulator());
        this.adminTicketConverter.addPopulator(new AdminTicketDataPopulator());
    }

    public UserTicketTransferData getTicketDataforUser(TicketFilter filter){
        Page<Ticket> ticketPage = this.ticketService.getTicketswithFilter(filter);
        List<Ticket> ticketList = ticketPage.getContent();
        List<UserTicketData> ticketDataList = this.usetTicketConverter.convertAll(ticketList);
        UserTicketTransferData data = new UserTicketTransferData();
        data.setTicketData(ticketDataList);
        data.setPageCount(ticketPage.getTotalPages());
        return data;

    }
    public AdminTicketTransferData getTicketDataforAdmin(TicketFilter filter){
        Page<Ticket> ticketPage = this.ticketService.getTicketswithFilter(filter);
        List<Ticket> ticketList = ticketPage.getContent();
        List<AdminTicketData> ticketDataList = this.adminTicketConverter.convertAll(ticketList);


        AdminTicketTransferData data = new AdminTicketTransferData();
        data.setTicketData(ticketDataList);
        data.setPageCount(ticketPage.getTotalPages());
        return data;

    }
    public void saveTicket(TicketPurchaseData data){
        if ((data.getUserId()==null && data.getUserName()==null) || data.getRouteId()==null){
            throw new BadRequestException();
        }
        Ticket ticket =new Ticket();
        ticket.setState(TicketState.RECEIPT);
        this.ticketPurchaseDataMapper(ticket,data);
        if (this.routeService.isFull(data.getRouteId())){
            throw new ConflictException();
        }
        this.ticketService.save(ticket);
        this.routeService.updateTicketParameters(data.getRouteId());
    }
    public void updateTicket(TicketUpdateData data){
        Ticket ticket;
        try{
            ticket =this.ticketService.getTicketsById(data.getId());
        }
        catch (Exception e){
            throw new BadRequestException();
        }

        Long lastRouteId=ticket.getRoute().getId();
        Long newRouteId=data.getRouteId();

        this.ticketUpdateDataMapper(ticket,data);
        if (lastRouteId!=newRouteId && newRouteId!=null && data.getState()==TicketState.POSTPONED){//if route is changed then route isFull and ticket count parameters need to be updated
            if (this.routeService.isFull(newRouteId)){
                throw new ConflictException();
            }
            this.ticketService.save(ticket);
            this.routeService.updateTicketParameters(lastRouteId);
            this.routeService.updateTicketParameters(newRouteId);
        }
        else if (data.getState()==TicketState.CANCELED){
            this.routeService.updateTicketParameters(lastRouteId);
            this.ticketService.save(ticket);
        }



    }
    public void deleteTicket(Long id){
        Ticket ticket = this.ticketService.getTicketsById(id);
        Long routeId = ticket.getRoute().getId();
        this.ticketService.delete(id);
        this.routeService.updateTicketParameters(routeId);
    }

    public void ticketPurchaseDataMapper(Ticket ticket,TicketPurchaseData data){
        if(data.getRouteId()!=null){
            Route route = this.routeService.getRouteById(data.getRouteId());
            ticket.setRoute(route);
            ticket.setPrice(route.getPrice());
        }
        if (data.getUserName()!=null || data.getUserId()!=null){
            if (data.getUserId()!=null){
                ticket.setUser(new User(data.getUserId()));
            }
            else{
                User user = this.userService.getUserByName(data.getUserName());
                ticket.setUser(user);
            }

        }

    }
    public void ticketUpdateDataMapper(Ticket ticket, TicketUpdateData data){
        if (data.getState()!=null){
            ticket.setState(data.getState());
        }
        if(data.getRouteId()!=null){
            Route route = this.routeService.getRouteById(data.getRouteId());
            ticket.setRoute(route);
            ticket.setPrice(route.getPrice());
        }
    }

}
