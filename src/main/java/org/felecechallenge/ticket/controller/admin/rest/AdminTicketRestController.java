package org.felecechallenge.ticket.controller.admin.rest;

import org.felecechallenge.ticket.facade.TicketFacade;
import org.felecechallenge.ticket.facade.dto.ticket.AdminTicketTransferData;
import org.felecechallenge.ticket.facade.dto.ticket.TicketPurchaseData;
import org.felecechallenge.ticket.facade.dto.ticket.TicketUpdateData;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView purchase(TicketPurchaseData data){
        try{
            this.ticketFacade.saveTicket(data);
            return new RedirectView("/admin/tickets?err=false");
        }catch (Exception e){
            return new RedirectView("/admin/tickets?err=true");
        }



    }
    @PutMapping
    public void update(@RequestBody TicketUpdateData data){
        this.ticketFacade.updateTicket(data);
    }


}
