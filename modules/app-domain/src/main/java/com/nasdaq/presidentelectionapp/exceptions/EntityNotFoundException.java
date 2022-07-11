package com.nasdaq.presidentelectionapp.exceptions;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class EntityNotFoundException extends Exception {
   private final Class<?> entityClass;
   private final Serializable id;

   public EntityNotFoundException(Class<?> entityClass, Serializable id) {
      super("Entity of type " + entityClass.getSimpleName() + " with ID " + id + " not found.");
      this.entityClass = entityClass;
      this.id = id;
   }
}
