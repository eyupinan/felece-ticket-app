package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.filter.UserFilter;
import org.felecechallenge.ticket.model.User;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.felecechallenge.ticket.repository.UserRepository;
import org.felecechallenge.ticket.service.UserService;
import org.felecechallenge.ticket.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder , @Value("${admin.username:admin}") String adminUsername,
                           @Value("${admin.password:admin}") String adminPassword) {
        this.passwordEncoder=passwordEncoder;
        this.userRepository = userRepository;
        List<User> admins = this.userRepository.findByRole(Roles.ROLE_ADMIN);
        if (admins.isEmpty()){
            User defaultAdmin = new User();
            defaultAdmin.setUsername(adminUsername);
            defaultAdmin.setPassword(passwordEncoder.encode(adminPassword));
            defaultAdmin.setRole(Roles.ROLE_ADMIN);
            this.userRepository.save(defaultAdmin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
    @Override
    public User getUserByName(String name){
        return this.userRepository.findByUsername(name);
    }
    public Page<User> findAll(){
        return this.userRepository.findAll(PageRequest.of(0,Integer.MAX_VALUE));
    }
    public User getById(Long id){
        return this.userRepository.findById(id).get();
    }
    public void save(User user){
        this.userRepository.save(user);
    }
    @Transactional
    public void disable(Long id){
        User user = this.getById(id);
        user.setDisabled(true);
        this.userRepository.save(user);
    }

    public Page<User> findByFilter(UserFilter filter) {
        Pageable pageable = PageGenerator.generate(filter.getSortBy(),filter.getOffset(),filter.getLimit());
        return this.userRepository.findAll(UserSpecification.customUserQuery(filter),pageable);
    }
}
