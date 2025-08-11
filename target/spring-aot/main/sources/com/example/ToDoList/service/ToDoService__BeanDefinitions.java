package com.example.ToDoList.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ToDoService}.
 */
@Generated
public class ToDoService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'toDoService'.
   */
  private static BeanInstanceSupplier<ToDoService> getToDoServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ToDoService>forConstructor(ObjectMapper.class)
            .withGenerator((registeredBean, args) -> new ToDoService(args.get(0)));
  }

  /**
   * Get the bean definition for 'toDoService'.
   */
  public static BeanDefinition getToDoServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToDoService.class);
    beanDefinition.setInitMethodNames("init");
    InstanceSupplier<ToDoService> instanceSupplier = getToDoServiceInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(ToDoService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
