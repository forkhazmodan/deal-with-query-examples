package com.example.howtoquery.controller;

import com.example.howtoquery.response_template.GetPaginationResponse;
import com.example.howtoquery.service.UserService;
import com.example.howtoquery.utils.PaginationMetadata;
import com.example.howtoquery.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1/test")
public class BadPaginationExampleController {

    private final UserService userService;

    public BadPaginationExampleController(@Qualifier(value = "userServiceV1") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> index(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "age", required = false) Integer age,
                                   @RequestParam(value = "createdFrom", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdFrom,
                                   @RequestParam(value = "createdTo", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdTo,
                                   @PageableDefault(value=3) Pageable pagination) {

        var users = userService.searchUsers(
                name,
                age,
                createdFrom,
                createdTo,
                pagination);

        PaginationMetadata links = PaginationMetadata.generatePaginationLinks(
                users,
                String.format("/v1/test"),
                null,
                pagination.getPageSize());

        return successfulPaginationResponse(users, links);
    }

    protected static <T> ResponseEntity<SuccessResponse<?>> successfulPaginationResponse(
            Page<T> result,
            PaginationMetadata links) {
        var response = GetPaginationResponse.<T>builder()
                .records(result.toList())
                .links(links)
                .build();

        return ResponseEntity.ok(new SuccessResponse<>(response));
    }
}
