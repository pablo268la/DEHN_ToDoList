package org.springframework.shell.boot;

import org.jline.terminal.Terminal;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.shell.context.ShellContext;

/**
 * Bean definitions for {@link ShellContextAutoConfiguration}.
 */
@Generated
public class ShellContextAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'shellContextAutoConfiguration'.
   */
  public static BeanDefinition getShellContextAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ShellContextAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ShellContextAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'shellContext'.
   */
  private static BeanInstanceSupplier<ShellContext> getShellContextInstanceSupplier() {
    return BeanInstanceSupplier.<ShellContext>forFactoryMethod(ShellContextAutoConfiguration.class, "shellContext", Terminal.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.shell.boot.ShellContextAutoConfiguration", ShellContextAutoConfiguration.class).shellContext(args.get(0)));
  }

  /**
   * Get the bean definition for 'shellContext'.
   */
  public static BeanDefinition getShellContextBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ShellContext.class);
    beanDefinition.setFactoryBeanName("org.springframework.shell.boot.ShellContextAutoConfiguration");
    beanDefinition.setInstanceSupplier(getShellContextInstanceSupplier());
    return beanDefinition;
  }
}
