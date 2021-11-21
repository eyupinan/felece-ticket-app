package org.felecechallenge.ticket.facade.dto.route;

import java.util.Date;

public class AdminRouteData extends UserRouteData{

    private Long startDestinationId;
    private Long endDestinationId;
    private String vehiclePlate;
    private Long vehicleId;
    private Date createdAt;
    private Date updatedAt;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getStartDestinationId() {
        return startDestinationId;
    }

    public void setStartDestinationId(Long startDestinationId) {
        this.startDestinationId = startDestinationId;
    }

    public Long getEndDestinationId() {
        return endDestinationId;
    }

    public void setEndDestinationId(Long endDestinationId) {
        this.endDestinationId = endDestinationId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }


}
