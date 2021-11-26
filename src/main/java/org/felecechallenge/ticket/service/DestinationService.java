package org.felecechallenge.ticket.service;

import org.felecechallenge.ticket.model.Destination;

import java.util.List;

public interface DestinationService {
    public Destination getById(Long id);
    public Destination getByName(String name);
    public List<Destination> getAll();
    public void save(Destination destination);
    public void disableDestination(Long id);
}
