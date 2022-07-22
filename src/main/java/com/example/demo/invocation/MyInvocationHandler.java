package com.example.demo.invocation;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现
 * @author lujp
 * 2022/7/22-11:44
 **/
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
        //TODO 怎样获取被代理类中的泛型

        String methodName = method.getName();
        if (StringUtils.pathEquals(methodName, "hello")) {
            System.out.println("hello hello");
        }
        if (StringUtils.pathEquals(methodName, "world")) {
            System.out.println("world world");
        }
        return null;
    }
}