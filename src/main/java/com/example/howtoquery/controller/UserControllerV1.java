package com.example.howtoquery.controller;

import com.example.howtoquery.model.User;
import com.example.howtoquery.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1 {

    private final UserService userService;

    public UserControllerV1(@Qualifier(value = "userServiceV1") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "createdFrom", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdFrom,
            @RequestParam(value = "createdTo", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdTo,
            @PageableDefault(value=3)
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
//            @SortDefault.SortDefaults({
//                @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
//            })
            Pageable pagination
    ) {
        return ResponseEntity.ok(userService.searchUsers(
                name,
                age,
                createdFrom,
                createdTo,
                pagination));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
