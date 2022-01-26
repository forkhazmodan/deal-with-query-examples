package com.example.howtoquery.utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class PaginationMetadata {

  private String first;
  private String next;
  private String prev;
  private String last;

  public static PaginationMetadata generatePaginationLinks(Page<?> page, HttpServletRequest currentRequest) {
    var queryString = currentRequest.getQueryString() == null
            ? ""
            : currentRequest.getQueryString();

    var splittedStrings = queryString
            .replaceAll("^/+", "")
            .replaceAll("/+$", "")
            .split("&");

    var filters = Arrays.stream(splittedStrings)
            .filter(string -> !(string.matches("^limit.*") || string.matches("^offset.*")))
            .collect(Collectors.toList());

    return generatePaginationLinks(
            page,
            currentRequest.getRequestURI(),
            filters,
            page.getPageable().getPageSize()
    );
  }

  /**
   * Calculate value for pagination attributes.
   *
   * @param result      Page result
   * @param filters     Filter parameters strings in the URL
   * @param limit       number of records per request
   * @return LinksPaginationDto
   */
  public static PaginationMetadata generatePaginationLinks(
          Page<?> result,
          String baseUrl,
          List<String> filters,
          Integer limit) {
    String first = null;
    String next = null;
    String prev = null;
    String last = null;
    int pageSize = result.getSize();
    Long totalRecords = result.getTotalElements();
    if (totalRecords > 0) {
      Long lastOffset;
      first = generatePrefixUrl(
              baseUrl,
              String.valueOf(limit),
              String.valueOf(0),
              filters);
      if (totalRecords % pageSize == 0) {
        lastOffset = totalRecords - pageSize;
      } else {
        lastOffset = totalRecords / pageSize * pageSize;
      }
      last = generatePrefixUrl(
              baseUrl,
              String.valueOf(limit),
              String.valueOf(lastOffset),
              filters);
      if (result.hasNext()) {
        next = generatePrefixUrl(
                baseUrl,
                String.valueOf(limit),
                String.valueOf(result.nextPageable().getOffset()),
                filters);
      }
      if (result.hasPrevious()) {
        prev = generatePrefixUrl(
                baseUrl,
                String.valueOf(limit),
                String.valueOf(result.previousPageable().getOffset()),
                filters);
      }
    }

    return PaginationMetadata.builder()
            .first(first)
            .next(next)
            .prev(prev)
            .last(last)
            .build();
  }

  private static String generatePrefixUrl(String baseUrl, String limit, String offset, List<String> filters) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format(
            "%s?%s&%s",
            baseUrl,
            String.format("limit=%s", limit),
            String.format("offset=%s", offset)));

    if (filters != null && filters.size() > 0) {
      sb.append(String.format("&%s", String.join("&", filters)));
    }

    return sb.toString();
  }
}
