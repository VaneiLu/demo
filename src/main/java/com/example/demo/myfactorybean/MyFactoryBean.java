package com.example.demo.myfactorybean;

import com.example.demo.invocation.MyInvocationHandler;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author lujp
 * 2022/7/22-14:25
 **/
public class MyFactoryBean<T> implements FactoryBean<T> {

    /**
     * 代理的接口类型
     */
    public final Class<T> type;

    public MyFactoryBean(Class<T> type) {
        this.type = type;
    }

    @Override
    public T getObject() throws Exception {
        return (T) MyInvocationHandler.build(type);
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }
}
