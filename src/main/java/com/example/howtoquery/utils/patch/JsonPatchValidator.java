package com.example.howtoquery.utils.patch;

import com.example.howtoquery.utils.validator.KebabCaseConverter;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPatchValidator {

  private JsonPatchValidator(){}

  /**
   * Validate path for containing only available field.
   *
   * @param requestedField requested field
   * @param fieldList      - list of valid fields
   * @return true if field is valid
   */
  public static boolean isValidPathFieldName(String requestedField, List<String> fieldList) {
    return fieldList.stream()
        .map(KebabCaseConverter::convertToKebabCase)
        .collect(Collectors.toList())
        .contains(requestedField);
  }
}
