package com.example.ToDoList;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ToDoListApplication}.
 */
@Generated
public class ToDoListApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'toDoListApplication'.
   */
  public static BeanDefinition getToDoListApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToDoListApplication.class);
    beanDefinition.setInstanceSupplier(ToDoListApplication::new);
    return beanDefinition;
  }
}
