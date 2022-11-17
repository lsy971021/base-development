package com.lsy.config;

import com.lsy.model.User;
import com.lsy.params.req.SaveParams;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBFPP implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println(111);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("开始执行 postProcessBeanDefinitionRegistry()～");
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SaveParams.class);
        beanDefinitionBuilder.addPropertyValue("id","100");
        beanDefinitionBuilder.addPropertyValue("province","河南");
        beanDefinitionRegistry.registerBeanDefinition("test",beanDefinitionBuilder.getBeanDefinition());
    }
}
