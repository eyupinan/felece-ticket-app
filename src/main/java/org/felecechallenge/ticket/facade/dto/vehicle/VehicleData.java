package org.felecechallenge.ticket.facade.dto.vehicle;

public class VehicleData {
    private Long id;
    private String plate;
    private Long totalSeatCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Long getTotalSeatCount() {
        return totalSeatCount;
    }

    public void setTotalSeatCount(Long totalSeatCount) {
        this.totalSeatCount = totalSeatCount;
    }
}
