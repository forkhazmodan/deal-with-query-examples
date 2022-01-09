package com.example.howtoquery.specefication;

import com.example.howtoquery.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification {

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

    public static Specification<User> userAgeLessThan(Integer age) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.lessThan(root.get("age"), age);
            }
        };
    }
}
