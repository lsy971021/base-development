package com.lsy.config;

import com.lsy.params.req.SaveParams;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<SaveParams> {
    @Override
    public SaveParams getObject() throws Exception {
        SaveParams saveParams = new SaveParams();
        saveParams.setId("1");
        saveParams.setProvince("zz");
        return saveParams;
    }

    @Override
    public Class<?> getObjectType() {
        return SaveParams.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
