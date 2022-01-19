package com.example.howtoquery.utils;

import com.example.howtoquery.response_template.GetPaginationResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Generic Success Response.
 * @param <T> A single or a list of resource
 */
@Data
public class SuccessResponse<T> {
  private T data;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PaginationMetadata links;

  /**
   * Generic Success Response Ctor.
   * @param data Generic DTO.
   */
  public SuccessResponse(T data) {
    this.data = data;
  }

  /**
   * Success response for paginated result.
   * @param paginationResponse pagination response.
   * @param <K> response object type.
   */
  public <K> SuccessResponse(GetPaginationResponse<K> paginationResponse) {
    this.data = (T) paginationResponse.getRecords();
    this.links = paginationResponse.getLinks();
  }
}
