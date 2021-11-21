package org.felecechallenge.ticket.facade.populator.vehicle;

import org.felecechallenge.ticket.facade.dto.vehicle.VehicleData;
import org.felecechallenge.ticket.facade.populator.Populator;
import org.felecechallenge.ticket.model.Vehicle;

public class VehiclePopulator implements Populator<Vehicle, VehicleData> {
    @Override
    public void populate(Vehicle vehicle, VehicleData vehicleData) {
        vehicleData.setPlate(vehicle.getPlate());
        vehicleData.setId(vehicle.getId());
        vehicleData.setTotalSeatCount(vehicle.getTotalSeatCount());
    }
}
