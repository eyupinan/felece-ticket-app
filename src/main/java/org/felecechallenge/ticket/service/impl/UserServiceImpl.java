package org.felecechallenge.ticket.service.impl;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.filter.UserFilter;
import org.felecechallenge.ticket.model.User;
import org.felecechallenge.ticket.facade.dto.user.UserPrincipal;
import org.felecechallenge.ticket.repository.UserRepository;
import org.felecechallenge.ticket.service.UserService;
import org.felecechallenge.ticket.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.userRepository = userRepository;
        List<User> admins = this.userRepository.findByRole(Roles.ROLE_ADMIN);
        if (admins.isEmpty()){
            User defaultAdmin = new User();
            defaultAdmin.setUsername("admin");
            defaultAdmin.setAddress("Gazipaşa mah. nemlioğlu konak sok. Konak Apt. no 14 daire 6");
            defaultAdmin.setPhone(5062624264L);
            defaultAdmin.setEmail("eyupinan0@gmail.com");
            defaultAdmin.setPassword(passwordEncoder.encode("admin"));
            defaultAdmin.setRole(Roles.ROLE_ADMIN);
            this.userRepository.save(defaultAdmin);
        }
        List<User> usr = this.userRepository.findByRole(Roles.ROLE_USER);
        if (usr.isEmpty()){
            User defaultUsr = new User();
            defaultUsr.setUsername("user");
            defaultUsr.setAddress("Gazipaşa mah. nemlioğlu konak sok. Konak Apt. no 15 daire 6");
            defaultUsr.setPhone(5062624265L);
            defaultUsr.setEmail("eyupinan1@gmail.com");
            defaultUsr.setPassword(passwordEncoder.encode("user"));
            defaultUsr.setRole(Roles.ROLE_USER);
            this.userRepository.save(defaultUsr);
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
    public void delete(String username){
        this.userRepository.deleteByUsername(username);
    }

    public Page<User> findByFilter(UserFilter filter) {
        Pageable pageable = PageGenerator.generate(filter.getSortBy(),filter.getOffset(),filter.getLimit());
        return this.userRepository.findAll(UserSpecification.customUserQuery(filter),pageable);
    }
}
