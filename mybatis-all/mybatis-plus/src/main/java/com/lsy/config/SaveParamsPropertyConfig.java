package com.lsy.config;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义属性编辑器配置类
 */
@Configuration
public class SaveParamsPropertyConfig {

    @Bean
    public CustomEditorConfigurer CustomEditorConfigurer(){
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        customEditorConfigurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{new SaveParamsEditor()});
        return customEditorConfigurer;
    }
}
