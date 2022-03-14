package com.example.howtoquery.utils.patch;

import com.example.howtoquery.contracts.Patchable;
import com.example.howtoquery.utils.patch.parsed.PatchContainer;

@FunctionalInterface
public interface PatchFunction<T, E extends Patchable> {

  /**
   * Patch DTO.
   * @param obj {@link PatchContainer} with parsed PATCH elements
   * @param entity entity object
   * @return patched DTO
   */
  T apply(PatchContainer<E> obj, E entity);

}
