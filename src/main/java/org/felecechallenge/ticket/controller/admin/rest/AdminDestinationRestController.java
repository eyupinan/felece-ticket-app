package org.felecechallenge.ticket.controller.admin.rest;

import org.felecechallenge.ticket.exception.BadRequestException;
import org.felecechallenge.ticket.model.Destination;
import org.felecechallenge.ticket.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/destination")
public class AdminDestinationRestController {
    @Autowired
    private DestinationService destinationService;
    @GetMapping
    public List<Destination> getDestinations(){
        return destinationService.getAll();
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateDestination(@RequestBody Destination destination){

        this.destinationService.save(destination);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createDestination(@RequestBody Destination destination){
        if (destination.getName()!=null){
            this.destinationService.save(destination);
        }
        else{
            throw new BadRequestException();
        }

    }
    @DeleteMapping
    public void deleteDestination(@RequestParam Long id){
        this.destinationService.delete(id);
    }
}
