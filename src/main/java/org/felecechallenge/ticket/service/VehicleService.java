package org.felecechallenge.ticket.service;

import org.felecechallenge.ticket.model.Vehicle;

import java.util.List;

public interface VehicleService {
    public Vehicle getVehicleById(Long id);
    public Vehicle getVehicleByPlate(String plate);
    public List<Vehicle> getAll();
    public void saveVehicle(Vehicle vehicle);
    public void disableVehicle(Long id);
}
