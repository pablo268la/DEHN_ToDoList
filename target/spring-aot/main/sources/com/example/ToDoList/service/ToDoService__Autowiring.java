package com.example.ToDoList.service;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ToDoService}.
 */
@Generated
public class ToDoService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ToDoService apply(RegisteredBean registeredBean, ToDoService instance) {
    AutowiredFieldValueResolver.forRequiredField("todoFile").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
