package com.lsy.config;

import com.lsy.params.req.SaveParams;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditorSupport;

/**
 * 自定义属性编辑器
 */
public class SaveParamsEditor extends PropertyEditorSupport implements PropertyEditorRegistrar {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] split = text.split(",");
        SaveParams saveParams = new SaveParams();
        saveParams.setId(split[0]);
        saveParams.setProvince(split[1]);
        this.setValue(saveParams);
    }

    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        propertyEditorRegistry.registerCustomEditor(SaveParams.class,this);
    }
}
