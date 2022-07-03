package com.nasdaq.presidentelectionapp.exceptions;

import lombok.Getter;

@Getter
public class DomainValidationException extends Exception {

   private final String propertyName;

   public DomainValidationException(String message, String propertyName) {
      super(message);
      this.propertyName = propertyName;
   }
}
