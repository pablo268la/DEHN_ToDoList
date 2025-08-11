package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link UpdateTaskCommand}.
 */
@Generated
public class UpdateTaskCommand__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'updateTaskCommand'.
   */
  private static BeanInstanceSupplier<UpdateTaskCommand> getUpdateTaskCommandInstanceSupplier() {
    return BeanInstanceSupplier.<UpdateTaskCommand>forConstructor(ToDoService.class)
            .withGenerator((registeredBean, args) -> new UpdateTaskCommand(args.get(0)));
  }

  /**
   * Get the bean definition for 'updateTaskCommand'.
   */
  public static BeanDefinition getUpdateTaskCommandBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UpdateTaskCommand.class);
    beanDefinition.setInstanceSupplier(getUpdateTaskCommandInstanceSupplier());
    return beanDefinition;
  }
}
