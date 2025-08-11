package com.example.ToDoList.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link JacksonConfig}.
 */
@Generated
public class JacksonConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'jacksonConfig'.
   */
  public static BeanDefinition getJacksonConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonConfig.class);
    beanDefinition.setTargetType(JacksonConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(JacksonConfig.class);
    beanDefinition.setInstanceSupplier(JacksonConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'objectMapper'.
   */
  private static BeanInstanceSupplier<ObjectMapper> getObjectMapperInstanceSupplier() {
    return BeanInstanceSupplier.<ObjectMapper>forFactoryMethod(JacksonConfig$$SpringCGLIB$$0.class, "objectMapper")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("jacksonConfig", JacksonConfig.class).objectMapper());
  }

  /**
   * Get the bean definition for 'objectMapper'.
   */
  public static BeanDefinition getObjectMapperBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObjectMapper.class);
    beanDefinition.setFactoryBeanName("jacksonConfig");
    beanDefinition.setInstanceSupplier(getObjectMapperInstanceSupplier());
    return beanDefinition;
  }
}
