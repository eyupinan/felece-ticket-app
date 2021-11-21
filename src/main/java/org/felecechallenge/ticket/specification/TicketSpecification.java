package org.felecechallenge.ticket.specification;

import org.felecechallenge.ticket.enums.TicketState;
import org.felecechallenge.ticket.filter.TicketFilter;
import org.felecechallenge.ticket.model.Destination;
import org.felecechallenge.ticket.model.Route;
import org.felecechallenge.ticket.model.Ticket;
import org.felecechallenge.ticket.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketSpecification {
    public static Specification<Ticket> customTicketQuery(TicketFilter filter) {
        return new Specification<Ticket>() {
            public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<Ticket, User> userJoin = root.join("user");
                Join<Ticket, Route> routeJoin = root.join("route");
                Join<Route, Destination> startDestinationJoin = routeJoin.join("startDestination");
                Join<Route, Destination> endDestinationJoin = routeJoin.join("endDestination");
                List<Predicate> predicates = new ArrayList<>();

                if (filter.getUsername() != null) {
                    predicates.add(cb.equal(userJoin.get("username"), filter.getUsername()));
                }
                if (filter.getState() != null) {
                    predicates.add(cb.equal(root.get("state"), filter.getState()));
                }
                if (filter.getStartDate() != null) {
                    predicates.add(cb.greaterThan(routeJoin.<Date>get("date"), filter.getStartDate()));
                }
                if (filter.getEndDate() != null) {
                    predicates.add(cb.lessThan(routeJoin.<Date>get("date"), filter.getEndDate()));
                }
                if (filter.getStartDestination() != null) {
                    predicates.add(cb.equal(startDestinationJoin.get("name"), filter.getStartDestination()));
                }
                if (filter.getEndDestination() != null) {
                    predicates.add(cb.equal(endDestinationJoin.get("name"), filter.getEndDestination()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
    public static Specification<Ticket> routesActiveTickets(Long routeId) {
        return new Specification<Ticket>() {
            public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<Ticket, Route> routeJoin = root.join("route");
                List<Predicate> predicates = new ArrayList<>();
                if (routeId != null) {
                    predicates.add(cb.equal(routeJoin.get("id"), routeId));
                }
                predicates.add(cb.notEqual(root.get("state"),TicketState.CANCELED));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
}
