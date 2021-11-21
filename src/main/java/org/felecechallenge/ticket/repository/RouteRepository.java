package org.felecechallenge.ticket.repository;

import org.felecechallenge.ticket.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long>, JpaSpecificationExecutor<Route> {
}
