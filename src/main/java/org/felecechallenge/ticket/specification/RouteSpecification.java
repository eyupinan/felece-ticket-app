package org.felecechallenge.ticket.specification;

import org.felecechallenge.ticket.filter.RouteFilter;
import org.felecechallenge.ticket.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteSpecification {
    public static Specification<Route> customRouteQuery(RouteFilter filter) {
        return new Specification<Route>() {
            public Predicate toPredicate(Root<Route> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<Route, Destination> startDestinationJoin = root.join("startDestination");
                Join<Route, Destination> endDestinationJoin = root.join("endDestination");
                List<Predicate> predicates = new ArrayList<>();

                if (filter.getStartDestination() != null) {
                    predicates.add(cb.equal(startDestinationJoin.get("name"), filter.getStartDestination()));
                }
                if (filter.getEndDestination() != null) {
                    predicates.add(cb.equal(endDestinationJoin.get("name"), filter.getEndDestination()));
                }
                if (filter.getDate() != null) {
                    predicates.add(cb.greaterThan(root.<Date>get("date"), filter.getDate()));
                }
                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
                if (filter.getFull() != null) {
                    predicates.add(cb.equal(root.get("isFull"), filter.getFull()));
                }
                predicates.add(cb.notEqual(root.get("disabled"), true));

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

}
