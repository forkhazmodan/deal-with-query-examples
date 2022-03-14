package com.example.howtoquery.utils.patch.parser;

import com.example.howtoquery.contracts.Patchable;
import com.example.howtoquery.utils.patch.parsed.PatchContainer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonPatchParser {

  private static final String SLASH = "/";
  public static final String PATH_FIELD = "path";
  public static final String OPERATION_FIELD = "op";
  public static final String VALUE_FIELD = "value";

  private JsonPatchParser() {}

  /**
   * Convert patch request body to list of {@link PatchContainer}
   * Split 'path' field value on elements for creating container
   * If size of elements == 3, then relatedEntityToPatch value exists.
   *
   * @param patchRequest request body
   * @return map with UUID as key and PatchContainer as value
   */
  public static <T extends Patchable> Map<String, PatchContainer<T>> parsePatchRequest(
      JsonNode patchRequest
  ) {
    Map<String, PatchContainer<T>> uuidToObjMap = new HashMap<>();

    for (var patchObject : patchRequest) {
      String[] res = patchObject.get(PATH_FIELD).asText().replaceFirst("^/","").split(SLASH);
      // remove first empty element
//      String[] res = Arrays.copyOfRange(initRes, 1, initRes.length);

      PatchContainer<T> obj = PatchContainer.<T>builder()
          .operation(patchObject.get(OPERATION_FIELD).asText())
          .uuid(res[0])
          .fieldNameToPatch(res[1])
          .value(patchObject.get(VALUE_FIELD).asText())
          .build();
      if (res.length == 3) {
        obj.setFieldNameToPatch(res[2]);
        obj.setRelatedEntityToPatch(res[1]);
      }
      uuidToObjMap.put(res[0], obj);
    }

    return uuidToObjMap;
  }

  /**
   * Convert JsonNode object into dto object.
   *
   * @param mapper         {@link ObjectMapper}
   * @param uuid           entity ID
   * @param jsonNode patched dto
   * @param dtoType        dto class
   * @param <T>            dto type
   * @return converted dto
   */
  public static <T> T mapJsonToDto(ObjectMapper mapper, String uuid, JsonNode jsonNode, Class<T> dtoType) {
    try {
      return mapper.treeToValue(jsonNode, dtoType);
    } catch (JsonProcessingException ex) {
      log.error("Failed to PATCH specified AdTag with ID = {}", uuid, ex);
      throw new IllegalArgumentException("Failed to PATCH specified AdTag with ID = " + uuid, ex);
    }
  }
}
