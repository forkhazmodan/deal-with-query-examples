package com.example.howtoquery.utils.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class KebabCaseConverter {

  private KebabCaseConverter() {}

  private static final Pattern pattern =
      Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+");

  /**
   * Convert field from camelCase to kebab-case.
   *
   * @param camelCaseField camelCase field name
   * @return kebab-case field name
   */
  public static String convertToKebabCase(String camelCaseField) {
    List<String> matched = new ArrayList<>();
    Matcher matcher = pattern.matcher(camelCaseField);
    while (matcher.find()) {
      matched.add(matcher.group(0));
    }
    return matched.stream()
        .map(String::toLowerCase)
        .collect(Collectors.joining("-"));
  }
}
