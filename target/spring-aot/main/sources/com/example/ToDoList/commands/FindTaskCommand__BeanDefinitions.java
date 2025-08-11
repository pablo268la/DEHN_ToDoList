package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FindTaskCommand}.
 */
@Generated
public class FindTaskCommand__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'findTaskCommand'.
   */
  private static BeanInstanceSupplier<FindTaskCommand> getFindTaskCommandInstanceSupplier() {
    return BeanInstanceSupplier.<FindTaskCommand>forConstructor(ToDoService.class)
            .withGenerator((registeredBean, args) -> new FindTaskCommand(args.get(0)));
  }

  /**
   * Get the bean definition for 'findTaskCommand'.
   */
  public static BeanDefinition getFindTaskCommandBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FindTaskCommand.class);
    beanDefinition.setInstanceSupplier(getFindTaskCommandInstanceSupplier());
    return beanDefinition;
  }
}
