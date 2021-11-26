package org.felecechallenge.ticket.repository;

import org.felecechallenge.ticket.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Vehicle findByPlateAndDisabledNot(String plate,Boolean disabled);
    List<Vehicle> findByDisabledNot(Boolean disabled);
}
