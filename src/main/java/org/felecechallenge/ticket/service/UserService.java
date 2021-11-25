package org.felecechallenge.ticket.service;

import org.felecechallenge.ticket.filter.UserFilter;
import org.felecechallenge.ticket.model.User;
import org.felecechallenge.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User getUserByName(String name);
    public Page<User> findAll();
    public Page<User> findByFilter(UserFilter filter);
    public User getById(Long id);
    public void save(User user);
    public void disable(Long id);
}
