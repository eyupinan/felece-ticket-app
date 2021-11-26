package org.felecechallenge.ticket.repository;

import org.felecechallenge.ticket.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {
    public Destination findByName(String name);
    public List<Destination> findByDisabledNot(Boolean disabled);
}
