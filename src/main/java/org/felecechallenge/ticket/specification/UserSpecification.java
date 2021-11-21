package org.felecechallenge.ticket.specification;

import org.felecechallenge.ticket.filter.UserFilter;
import org.felecechallenge.ticket.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSpecification {
    public static Specification<User> customUserQuery(UserFilter filter) {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (filter.getId() != null) {
                    predicates.add(cb.equal(root.get("id"), filter.getId()));
                }
                if (filter.getUsername() != null) {
                    predicates.add(cb.equal(root.get("username"), filter.getUsername()));
                }


                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
}