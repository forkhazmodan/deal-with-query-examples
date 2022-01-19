package com.example.howtoquery.response_template;

import com.example.howtoquery.utils.PaginationMetadata;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder(toBuilder = true)
@Value
public class GetPaginationResponse<T> {
  @NotNull
  private PaginationMetadata links;

  @NotNull
  private List<T> records;
}
