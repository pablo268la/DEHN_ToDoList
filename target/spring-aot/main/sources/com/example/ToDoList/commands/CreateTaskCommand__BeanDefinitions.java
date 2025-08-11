package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CreateTaskCommand}.
 */
@Generated
public class CreateTaskCommand__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'createTaskCommand'.
   */
  private static BeanInstanceSupplier<CreateTaskCommand> getCreateTaskCommandInstanceSupplier() {
    return BeanInstanceSupplier.<CreateTaskCommand>forConstructor(ToDoService.class)
            .withGenerator((registeredBean, args) -> new CreateTaskCommand(args.get(0)));
  }

  /**
   * Get the bean definition for 'createTaskCommand'.
   */
  public static BeanDefinition getCreateTaskCommandBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CreateTaskCommand.class);
    beanDefinition.setInstanceSupplier(getCreateTaskCommandInstanceSupplier());
    return beanDefinition;
  }
}
