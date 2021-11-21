package org.felecechallenge.ticket.controller.user.rest;

import org.felecechallenge.ticket.exception.BadRequestException;
import org.felecechallenge.ticket.exception.ForbiddenException;
import org.felecechallenge.ticket.facade.TicketFacade;
import org.felecechallenge.ticket.facade.dto.ticket.UserTicketData;
import org.felecechallenge.ticket.facade.dto.ticket.TicketPurchaseData;
import org.felecechallenge.ticket.facade.dto.ticket.TicketUpdateData;
import org.felecechallenge.ticket.facade.dto.ticket.UserTicketTransferData;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketRestController {
    @Autowired
    private TicketFacade ticketFacade;
    @GetMapping
    public UserTicketTransferData getByFilter(Authentication auth, TicketFilter filter){
        try{
            String username = auth.getName();
            filter.setUsername(username);
            UserTicketTransferData ticketDataList = this.ticketFacade.getTicketDataforUser(filter);
            return ticketDataList;
        }
        catch (Exception e){
            throw new ForbiddenException();
        }

    }
    @PostMapping(value = "/reservation",consumes = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView reservation(Authentication auth, @RequestBody TicketPurchaseData data){
        try{
            UserPrincipal principal =(UserPrincipal) auth.getPrincipal();
            data.setUserId(principal.getUser().getId());
            this.ticketFacade.saveTicket(data);
        }catch (Exception e){
            throw new BadRequestException();
        }
        return new RedirectView("/tickets");

    }
    @PutMapping("/update")
    public void update(Authentication auth, @RequestBody TicketUpdateData data){
        this.ticketFacade.updateTicket(data);
    }
}
