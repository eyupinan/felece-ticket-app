package org.felecechallenge.ticket.controller.admin.rest;

import org.felecechallenge.ticket.exception.ConflictException;
import org.felecechallenge.ticket.facade.TicketFacade;
import org.felecechallenge.ticket.facade.dto.ticket.AdminTicketTransferData;
import org.felecechallenge.ticket.facade.dto.ticket.TicketPurchaseData;
import org.felecechallenge.ticket.facade.dto.ticket.TicketUpdateData;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/api/ticket")
public class AdminTicketRestController {
    @Autowired
    private TicketFacade ticketFacade;
    @GetMapping
    public AdminTicketTransferData getByFilter(TicketFilter filter){
        AdminTicketTransferData ticketData = this.ticketFacade.getTicketDataforAdmin(filter);
        return ticketData;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void purchase(@RequestBody TicketPurchaseData data){
        try{
            this.ticketFacade.saveTicket(data);
        }catch (Exception e){
            throw new ConflictException();
        }



    }
    @PutMapping
    public void update(@RequestBody TicketUpdateData data){
        this.ticketFacade.updateTicket(data);
    }


}
