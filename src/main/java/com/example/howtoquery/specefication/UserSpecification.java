package com.example.howtoquery.specefication;

import com.example.howtoquery.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class UserSpecification {

    private UserSpecification() {}

    public static Specification<User> userNameLike(String name) {
        //Could be a problem when change name without
        return (root, query, cb) -> name != null
                ? cb.or(
                        cb.like(root.get("firstName"), "%" + name + "%"),
                        cb.like(root.get("lastName"), "%" + name + "%")
                )
                : cb.conjunction(); // and 1=1
//                : cb.disjunction(); // and 0=1
    }

    public static Specification<User> userAgeGreaterThanOrEqualTo(Integer age) {
        return (root, query, cb) -> age != null
            ? cb.greaterThanOrEqualTo(root.get("age"), age)
            : cb.conjunction(); // and 1=1
//            : cb.disjunction(); // and 0=1
    }

    public static Specification<User> createdBetween(Date createdFrom, Date createdTo) {
        return (root, query, cb) -> {
            Path<Date> createdAtPath = root.get("createdAt");
            query.orderBy(cb.desc(createdAtPath));
            return cb.and(
                    createdFrom != null
                            ? cb.greaterThanOrEqualTo(createdAtPath, createdFrom)
                            : cb.conjunction(),
                    createdTo != null
                            ? cb.lessThan(createdAtPath, createdTo)
                            : cb.conjunction()
            );
        };
    }

    public static Specification<User> userAgeLessThan(Integer age) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.lessThan(root.get("age"), age);
            }
        };
    }
}
