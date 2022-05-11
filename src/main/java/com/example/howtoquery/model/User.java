package com.example.howtoquery.model;

import com.example.howtoquery.contracts.Patchable;
import com.example.howtoquery.contracts.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User implements Patchable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="age")
    private Integer age;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;
}
