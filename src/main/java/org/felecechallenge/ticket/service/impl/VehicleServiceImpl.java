package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.model.Vehicle;
import org.felecechallenge.ticket.repository.VehicleRepository;
import org.felecechallenge.ticket.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.getById(id);
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findByDisabledNot(true);
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
    @Override
    public void disableVehicle(Long id) {
        Vehicle vehicle = this.getVehicleById(id);
        vehicle.setDisabled(true);
        this.saveVehicle(vehicle);
    }
    @Override
    public Vehicle getVehicleByPlate(String plate){
        return this.vehicleRepository.findByPlateAndDisabledNot(plate,true);
    }
}
