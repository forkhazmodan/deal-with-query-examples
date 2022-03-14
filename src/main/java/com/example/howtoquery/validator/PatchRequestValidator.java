package com.example.howtoquery.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.flipkart.zjsonpatch.InvalidJsonPatchException;
import com.flipkart.zjsonpatch.JsonPatch;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatchRequestValidator
    implements ConstraintValidator<PatchRequestConstraint, JsonNode> {

  /**
   * Validate RFC-6902 PATCH request body.
   * @param patchRequest request body
   * @param context context
   * @return true if valid, false if invalid
   */
  @Override
  public boolean isValid(JsonNode patchRequest, ConstraintValidatorContext context) {
    try {
      JsonPatch.validate(patchRequest);
      return true;
    } catch (InvalidJsonPatchException ex) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(ex.getMessage()).addConstraintViolation();
      return false;
    }
  }
}
