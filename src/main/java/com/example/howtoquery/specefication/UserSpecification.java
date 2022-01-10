package com.example.howtoquery.specefication;

import com.example.howtoquery.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class UserSpecification {

    private UserSpecification() {}

    public static Specification<User> userNameLike(String name) {
        return (root, query, cb) -> name != null
                ? cb.or(
                        cb.like(root.get("firstName" /*TODO implement static metamodel*/), "%" + name + "%"),
                        cb.like(root.get("lastName" /*TODO implement static metamodel*/), "%" + name + "%")
                )
                : cb.conjunction();
    }

    public static Specification<User> userAgeGreaterThanOrEqualTo(Integer age) {
        return (root, query, cb) -> age != null
            ? cb.greaterThanOrEqualTo(root.get("age"/*TODO implement static metamodel*/), age)
            : cb.conjunction();
    }

    public static Specification<User> createdBetween(Date createdFrom, Date createdTo) {
        return (root, query, cb) -> {
            Path<Date> createdAtPath = root.get("createdAt"/*TODO implement static metamodel*/);
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
                return cb.lessThan(root.get("age"/*TODO implement static metamodel*/), age);
            }
        };
    }

    public static Specification<User> userHasActiveStatus() {
        return (root, query, cb) -> cb.isTrue(root.get("isActive").as(Boolean.class));
    }
}
