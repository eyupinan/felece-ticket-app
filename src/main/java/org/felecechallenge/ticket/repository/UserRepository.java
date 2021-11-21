package org.felecechallenge.ticket.repository;

import org.felecechallenge.ticket.enums.Roles;
import org.felecechallenge.ticket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String name);
    Long deleteByUsername(String name);
    List<User> findByRole(Roles role);
}
