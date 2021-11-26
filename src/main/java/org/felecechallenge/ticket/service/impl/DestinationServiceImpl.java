package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.model.Destination;
import org.felecechallenge.ticket.repository.DestinationRepository;
import org.felecechallenge.ticket.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    @Override
    public Destination getById(Long id) {
        return destinationRepository.findById(id).get();
    }

    @Override
    public Destination getByName(String name) {
        return destinationRepository.findByName(name);
    }

    @Override
    public void save(Destination destination) {
        destinationRepository.save(destination);
    }

    @Override
    public void disableDestination(Long id) {
        Destination destination = this.getById(id);
        destination.setDisabled(true);
        this.save(destination);
    }
    public List<Destination> getAll(){
        return destinationRepository.findByDisabledNot(true);
    }
}
