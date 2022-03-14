package com.example.howtoquery.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PatchRequestValidator.class)
public @interface PatchRequestConstraint {

  /**
   * Error message.
   *
   * @return String
   */
  String message() default "Invalid Patch payload";


  /**
   * Groups.
   *
   * @return Class array
   */
  Class<?>[] groups() default {};

  /**
   * Payload.
   *
   * @return Class array
   */
  Class<? extends Payload>[] payload() default {};
}
