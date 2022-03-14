package com.example.howtoquery.service;

import com.example.howtoquery.validator.PatchRequestConstraint;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.Validator;

@Service
@Validated
public class ProgrammaticallyValidatingService {

  public void validateJsonNode(@Valid @PatchRequestConstraint JsonNode jsonNode){
    // do something
  }

}