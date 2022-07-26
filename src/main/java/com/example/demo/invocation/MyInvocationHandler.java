package com.example.demo.invocation;

import com.example.demo.mybasemapper.MyBaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.*;

/**
 * 动态代理实现
 *
 * @author lujp
 * 2022/7/22-11:44
 **/
@Slf4j
public class MyInvocationHandler implements InvocationHandler {

    public static Object build(Class<?> type) {
        // 创建代理对象
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MyInvocationHandler());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass == Object.class) {
            return method.invoke(this, args);
        }

        //获取方法参数中的泛型
        log.info("方法参数类型  " + AopUtils.getTargetClass(args[0]));

        //TODO 需要能够获取到返回值泛型类型
        log.info("method.getReturnType()返回值类型   " + method.getReturnType());
        log.info("AopUtils.getTargetClass(method.getReturnType()返回值类型   " + AopUtils.getTargetClass(method.getReturnType()));

        //TODO 怎样获取代理类中的泛型
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                log.info(rawType.getTypeName());
            }
        }

        //获取MyBaseMapper<T>包装的泛型类型
        Class<? extends MyBaseMapper> interfaceType = (Class<? extends MyBaseMapper>)proxy.getClass().getGenericInterfaces()[0];
        Type test = interfaceType.getGenericInterfaces()[0];
        if (test instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) test;
            log.info("MyBaseMapper的参数为：" + parameterizedType.getActualTypeArguments()[0]);
        }
        return null;
    }
}
