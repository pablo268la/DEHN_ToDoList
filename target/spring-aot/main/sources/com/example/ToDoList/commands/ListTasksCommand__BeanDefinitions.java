package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ListTasksCommand}.
 */
@Generated
public class ListTasksCommand__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'listTasksCommand'.
   */
  private static BeanInstanceSupplier<ListTasksCommand> getListTasksCommandInstanceSupplier() {
    return BeanInstanceSupplier.<ListTasksCommand>forConstructor(ToDoService.class)
            .withGenerator((registeredBean, args) -> new ListTasksCommand(args.get(0)));
  }

  /**
   * Get the bean definition for 'listTasksCommand'.
   */
  public static BeanDefinition getListTasksCommandBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ListTasksCommand.class);
    beanDefinition.setInstanceSupplier(getListTasksCommandInstanceSupplier());
    return beanDefinition;
  }
}
