package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DeleteTaskCommand}.
 */
@Generated
public class DeleteTaskCommand__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'deleteTaskCommand'.
   */
  private static BeanInstanceSupplier<DeleteTaskCommand> getDeleteTaskCommandInstanceSupplier() {
    return BeanInstanceSupplier.<DeleteTaskCommand>forConstructor(ToDoService.class)
            .withGenerator((registeredBean, args) -> new DeleteTaskCommand(args.get(0)));
  }

  /**
   * Get the bean definition for 'deleteTaskCommand'.
   */
  public static BeanDefinition getDeleteTaskCommandBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DeleteTaskCommand.class);
    beanDefinition.setInstanceSupplier(getDeleteTaskCommandInstanceSupplier());
    return beanDefinition;
  }
}
