package com.example.howtoquery.repository;

import com.example.howtoquery.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT * FROM users WHERE 1=1 " +
            "AND ((first_name LIKE CONCAT('%',:name,'%') OR last_name LIKE CONCAT('%',:name,'%')) OR :name IS NULL) " +
            "AND (age >= :age OR :age IS NULL) " +
            "AND (created_at >= :createdFrom OR :createdFrom IS NULL) " +
            "AND (created_at < :createdTo OR :createdTo IS NULL) ", nativeQuery = true)
    Page<User> searchUsers(@Param("name") String name,
                                  @Param("age") Integer age,
                                  @Param("createdFrom") Date createdFrom,
                                  @Param("createdTo") Date createdTo,
                                  Pageable pageable);

    @EntityGraph(value = "users.allJoins", type = EntityGraph.EntityGraphType.FETCH)
    Page<User> findAll(@Nullable Specification<User> specification, Pageable pageable);
}
