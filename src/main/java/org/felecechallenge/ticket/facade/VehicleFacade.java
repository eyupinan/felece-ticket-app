package org.felecechallenge.ticket.facade;

import org.felecechallenge.ticket.facade.converter.Converter;
import org.felecechallenge.ticket.facade.dto.route.UserRouteData;
import org.felecechallenge.ticket.facade.dto.vehicle.VehicleData;
import org.felecechallenge.ticket.facade.populator.vehicle.VehiclePopulator;
import org.felecechallenge.ticket.model.Vehicle;
import org.felecechallenge.ticket.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleFacade {
    @Autowired
    private VehicleService vehicleService;
    private Converter vehicleConverter = new Converter(VehicleData.class);
    public VehicleFacade(){
        this.vehicleConverter.addPopulator(new VehiclePopulator());
    }
    public List<VehicleData> getAllVehicleData(){
        return this.vehicleConverter.convertAll(vehicleService.getAll());
    }
    public void saveVehicle(VehicleData data){
        Vehicle vehicle = new Vehicle();
        this.vehicleDataMapper(vehicle,data);
        this.vehicleService.saveVehicle(vehicle);
    }
    public void updateVehicle(VehicleData data){
        Vehicle vehicle = this.vehicleService.getVehicleById(data.getId());
        vehicleDataMapper(vehicle,data);
        this.vehicleService.saveVehicle(vehicle);
    }
    public void vehicleDataMapper(Vehicle vehicle, VehicleData data){
        if (data.getId()!=null){
            vehicle.setId(data.getId());
        }
        if (data.getPlate()!=null && !data.getPlate().equals("")){
            vehicle.setPlate(data.getPlate());
        }
        if (data.getTotalSeatCount()!=null){
            vehicle.setTotalSeatCount(data.getTotalSeatCount());
        }
    }
}
