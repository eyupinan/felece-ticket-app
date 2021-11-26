package org.felecechallenge.ticket.controller.admin.rest;

import org.felecechallenge.ticket.facade.VehicleFacade;
import org.felecechallenge.ticket.facade.dto.vehicle.VehicleData;
import org.felecechallenge.ticket.model.Vehicle;
import org.felecechallenge.ticket.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/vehicle")
public class AdminVehicleRestController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleFacade vehicleFacade;

    @GetMapping
    public List<VehicleData> getAll(){
        return this.vehicleFacade.getAllVehicleData();
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@RequestBody VehicleData vehicle){
        this.vehicleFacade.saveVehicle(vehicle);
    }
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void update(@RequestBody VehicleData vehicle){
        this.vehicleFacade.updateVehicle(vehicle);
    }
    @DeleteMapping(value="/{id}")
    public void disable(@PathVariable Long id){
        this.vehicleFacade.disableVehicle(id);
    }
}
