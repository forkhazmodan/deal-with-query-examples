package com.example.howtoquery.utils.patch.resolver;


import static com.example.howtoquery.utils.patch.JsonPatchValidator.isValidPathFieldName;

import com.example.howtoquery.contracts.Patchable;
import com.example.howtoquery.utils.patch.PatchFunction;
import com.example.howtoquery.utils.patch.parsed.PatchContainer;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class PatchListResolver<T, E extends Patchable> {

  /**
   * {@link T} is PATCH result and {@link E} is entity to patch.
   *
   * @param fieldsList       list of Entity fields
   * @param objectWithValues map uuid to PatchContainer with entity
   * @param patchFunction    function to patch object
   * @return patched result list
   */
  public List<T> patchDtoList(
      List<String> fieldsList,
      Map<String, PatchContainer<E>> objectWithValues,
      PatchFunction<T, E> patchFunction
  ) {
    List<T> patchedList = new ArrayList<>();

    for (var obj : objectWithValues.entrySet()) {
      if (isValidPathFieldName(obj.getValue().getFieldNameToPatch(), fieldsList)) {
        T patchedObj = null;
        try {
          patchedObj = patchFunction.apply(obj.getValue(), obj.getValue().getEntity());
        } catch (Exception ex) {
          log.error("message: {}", ex.getMessage());
        }

        if (Objects.nonNull(patchedObj)) {
          patchedList.add(patchedObj);
        }
      } else {
        log.error("Failed to PATCH, field name '{}' is not valid", obj.getValue().getFieldNameToPatch());
        throw new IllegalArgumentException(
            String.format("Failed to PATCH, field name '%s' is not valid", obj.getValue().getFieldNameToPatch()));
      }
    }
    return patchedList;
  }
}
