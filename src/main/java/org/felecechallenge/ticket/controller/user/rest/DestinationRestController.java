package org.felecechallenge.ticket.controller.user.rest;

import org.felecechallenge.ticket.model.Destination;
import org.felecechallenge.ticket.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/destination")
public class DestinationRestController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public List<Destination> getDestinations(){
        return destinationService.getAll();
    }

}
