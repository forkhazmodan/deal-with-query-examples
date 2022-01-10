package com.example.howtoquery.specefication;

import com.example.howtoquery.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class UserSpecification {

    public static Specification<User> ageLessThan(Integer age) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        };
    }

    public static Specification<User> nameLike(String name) {
        return (root, query, cb) -> {
            if(name == null || "".equals(name)) {
                return null;
            }

            return cb.or(
                    cb.like(root.get("firstName" /*TODO implement static metamodel*/), "%" + name + "%"),
                    cb.like(root.get("lastName" /*TODO implement static metamodel*/), "%" + name + "%")
            );
        };
    }

    public static Specification<User> ageGreaterThanOrEqualTo(Integer age) {
        return (root, query, cb) -> age != null
            ? cb.greaterThanOrEqualTo(root.get("age"/*TODO implement static metamodel*/), age)
            : cb.conjunction();
    }

//    public static Specification<User> createdBetween(Date createdFrom, Date createdTo) {
//        return (root, query, cb) -> {
//            Path<Date> createdAtPath = root.get("createdAt"/*TODO implement static metamodel*/);
//            query.orderBy(cb.desc(createdAtPath));
//            return cb.and(
//                    createdFrom != null
//                            ? cb.greaterThanOrEqualTo(createdAtPath, createdFrom)
//                            : cb.conjunction(),
//                    createdTo != null
//                            ? cb.lessThan(createdAtPath, createdTo)
//                            : cb.conjunction()
//            );
//        };
//    }

    public static Specification<User> createdBetween(Date createdFrom, Date createdTo) {
        return (root, query, cb) -> {
            Path<Date> createdAtPath = root.get("createdAt"/*TODO implement static metamodel*/);
            if(createdFrom != null && createdTo != null) {
                return cb.and(
                        cb.greaterThanOrEqualTo(createdAtPath, createdFrom),
                        cb.lessThan(createdAtPath, createdTo)
                );
            } else if(createdFrom != null) {
                return cb.greaterThanOrEqualTo(createdAtPath, createdFrom);
            } else if(createdTo != null) {
                return cb.lessThan(createdAtPath, createdTo);
            } else {
                return null;
            }
        };
    }

    public static Specification<User> hasActiveStatus() {
        return (root, query, cb) -> cb.isTrue(root.get("isActive"/*TODO implement static metamodel*/).as(Boolean.class));
    }

    public static Specification<User> orderByCreatedAt(String order) {
        return (root, query, cb) -> {
            if(order.equals("ASC")) {
                query.orderBy(cb.asc(root.get("createdAt"/*TODO implement static metamodel*/)));
            } else {
                query.orderBy(cb.desc(root.get("createdAt"/*TODO implement static metamodel*/)));
            }
            return null;
        };
    }
}
