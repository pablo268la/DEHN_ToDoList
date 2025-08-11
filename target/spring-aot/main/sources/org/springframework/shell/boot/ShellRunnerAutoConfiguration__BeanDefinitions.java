package org.springframework.shell.boot;

import org.jline.reader.LineReader;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.shell.Shell;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.jline.InteractiveShellRunner;
import org.springframework.shell.jline.NonInteractiveShellRunner;
import org.springframework.shell.jline.PromptProvider;

/**
 * Bean definitions for {@link ShellRunnerAutoConfiguration}.
 */
@Generated
public class ShellRunnerAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'shellRunnerAutoConfiguration'.
   */
  public static BeanDefinition getShellRunnerAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ShellRunnerAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ShellRunnerAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration}.
   */
  @Generated
  public static class NonePrimaryCommandConfiguration {
    /**
     * Get the bean definition for 'nonePrimaryCommandConfiguration'.
     */
    public static BeanDefinition getNonePrimaryCommandConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration.class);
      beanDefinition.setInstanceSupplier(ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'interactiveApplicationRunner'.
     */
    private static BeanInstanceSupplier<InteractiveShellRunner> getInteractiveApplicationRunnerInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<InteractiveShellRunner>forFactoryMethod(ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration.class, "interactiveApplicationRunner", LineReader.class, PromptProvider.class, Shell.class, ShellContext.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.shell.boot.ShellRunnerAutoConfiguration$NonePrimaryCommandConfiguration", ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration.class).interactiveApplicationRunner(args.get(0), args.get(1), args.get(2), args.get(3)));
    }

    /**
     * Get the bean definition for 'interactiveApplicationRunner'.
     */
    public static BeanDefinition getInteractiveApplicationRunnerBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(InteractiveShellRunner.class);
      beanDefinition.setFactoryBeanName("org.springframework.shell.boot.ShellRunnerAutoConfiguration$NonePrimaryCommandConfiguration");
      beanDefinition.setInstanceSupplier(getInteractiveApplicationRunnerInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'nonInteractiveApplicationRunner'.
     */
    private static BeanInstanceSupplier<NonInteractiveShellRunner> getNonInteractiveApplicationRunnerInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<NonInteractiveShellRunner>forFactoryMethod(ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration.class, "nonInteractiveApplicationRunner", Shell.class, ShellContext.class, ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.shell.boot.ShellRunnerAutoConfiguration$NonePrimaryCommandConfiguration", ShellRunnerAutoConfiguration.NonePrimaryCommandConfiguration.class).nonInteractiveApplicationRunner(args.get(0), args.get(1), args.get(2)));
    }

    /**
     * Get the bean definition for 'nonInteractiveApplicationRunner'.
     */
    public static BeanDefinition getNonInteractiveApplicationRunnerBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(NonInteractiveShellRunner.class);
      beanDefinition.setFactoryBeanName("org.springframework.shell.boot.ShellRunnerAutoConfiguration$NonePrimaryCommandConfiguration");
      beanDefinition.setInstanceSupplier(getNonInteractiveApplicationRunnerInstanceSupplier());
      return beanDefinition;
    }
  }
}
