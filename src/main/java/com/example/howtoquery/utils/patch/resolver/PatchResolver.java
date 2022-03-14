package com.example.howtoquery.utils.patch.resolver;

import static com.example.howtoquery.utils.patch.parser.JsonPatchParser.mapJsonToDto;

import com.example.howtoquery.contracts.Patchable;
import com.example.howtoquery.utils.patch.parsed.PatchContainer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flipkart.zjsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class PatchResolver<T> {

  private T dto;
  private ObjectMapper mapper;

  /**
   * Do core patch operations.
   *
   * @param patchObj object with entity uuid, single patch object and field to update
   * @return patched json
   */
  public <E extends Patchable> T patch(
      PatchContainer<E> patchObj,
      String entityUuid,
      Class<T> dtoType
  ) {
    JsonNode targetNode = mapper.convertValue(dto, JsonNode.class);

    ArrayNode arrayNode = mapper.createArrayNode();

    Object value = patchObj.getValue();
    ObjectNode root = mapper.createObjectNode()
        .put("op", patchObj.getOperation())
        .put("path", "/" + patchObj.getFieldNameToPatch())
        .set("value", mapper.valueToTree(value));

    arrayNode.add(root);

    JsonNode patchedJson = JsonPatch.apply(arrayNode, targetNode);

    return mapJsonToDto(
        mapper,
        entityUuid,
        patchedJson,
        dtoType
    );
  }
}