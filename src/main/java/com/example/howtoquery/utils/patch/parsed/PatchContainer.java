package com.example.howtoquery.utils.patch.parsed;

import com.example.howtoquery.contracts.Patchable;
import java.util.Objects;

public class PatchContainer<T extends Patchable> {

  private String operation;
  private String uuid;
  private String fieldNameToPatch;
  private Object value;
  private String relatedEntityToPatch;
  private T entity;

  public PatchContainer() {
  }

  /**
   * Ctor to create PatchContainer.
   *
   * @param operation            PATCH operation
   * @param uuid                 entity uuid to PATCH
   * @param fieldNameToPatch     field to PATCH
   * @param value                new value
   * @param relatedEntityToPatch indicator to PATCH related entity(optional)
   * @param entity               to PATCH
   */
  public PatchContainer(
      String operation,
      String uuid,
      String fieldNameToPatch,
      Object value,
      String relatedEntityToPatch,
      T entity
  ) {
    this.operation = operation;
    this.uuid = uuid;
    this.fieldNameToPatch = fieldNameToPatch;
    this.value = value;
    this.relatedEntityToPatch = relatedEntityToPatch;
    this.entity = entity;
  }

  public static <T extends Patchable> PatchContainerBuilder<T> builder() {
    return new PatchContainerBuilder<T>();
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getFieldNameToPatch() {
    return fieldNameToPatch;
  }

  public void setFieldNameToPatch(String fieldNameToPatch) {
    this.fieldNameToPatch = fieldNameToPatch;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getRelatedEntityToPatch() {
    return relatedEntityToPatch;
  }

  public void setRelatedEntityToPatch(String relatedEntityToPatch) {
    this.relatedEntityToPatch = relatedEntityToPatch;
  }

  public T getEntity() {
    return entity;
  }

  public void setEntity(T entity) {
    this.entity = entity;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    PatchContainer<?> that = (PatchContainer<?>) obj;
    return Objects.equals(operation, that.operation) && Objects.equals(uuid, that.uuid)
        && Objects.equals(fieldNameToPatch, that.fieldNameToPatch) && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operation, uuid, fieldNameToPatch, value);
  }

  @Override
  public String toString() {
    return "PatchContainer{"
        + "operation='" + operation + '\''
        + ", uuid='" + uuid + '\''
        + ", fieldNameToPatch='" + fieldNameToPatch + '\''
        + ", value=" + value
        + ", relatedEntityToPatch='" + relatedEntityToPatch + '\''
        + ", entity=" + entity
        + '}';
  }

  public static class PatchContainerBuilder<T extends Patchable> {
    private String operation;
    private String uuid;
    private String fieldNameToPatch;
    private Object value;
    private String relatedEntityToPatch;
    private T entity;

    PatchContainerBuilder() {
    }

    public PatchContainerBuilder<T> operation(String operation) {
      this.operation = operation;
      return this;
    }

    public PatchContainerBuilder<T> uuid(String uuid) {
      this.uuid = uuid;
      return this;
    }

    public PatchContainerBuilder<T> fieldNameToPatch(String fieldNameToPatch) {
      this.fieldNameToPatch = fieldNameToPatch;
      return this;
    }

    public PatchContainerBuilder<T> value(Object value) {
      this.value = value;
      return this;
    }

    public PatchContainerBuilder<T> relatedEntityToPatch(String relatedEntityToPatch) {
      this.relatedEntityToPatch = relatedEntityToPatch;
      return this;
    }

    public PatchContainerBuilder<T> entity(T entity) {
      this.entity = entity;
      return this;
    }

    public PatchContainer<T> build() {
      return new PatchContainer<T>(operation, uuid, fieldNameToPatch, value, relatedEntityToPatch, entity);
    }

    /**
     * ToString for builder.
     * @return string result
     */
    public String toString() {
      return "PatchContainer.PatchContainerBuilder(operation=" + this.operation + ", uuid=" + this.uuid
          + ", fieldNameToPatch=" + this.fieldNameToPatch + ", value=" + this.value + ", relatedEntityToPatch="
          + this.relatedEntityToPatch + ", entity=" + this.entity + ")";
    }
  }
}
