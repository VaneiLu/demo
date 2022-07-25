package com.example.demo.invocation;

import org.springframework.aop.support.AopUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.*;

/**
 * 动态代理实现
 *
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
        //TODO 怎样获取代理类中的泛型
        //实现了获取被代理类对象中的泛型
        System.out.println(AopUtils.getTargetClass(args[0]));
        Type[] types = method.getGenericParameterTypes();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                System.out.println(rawType);
            }
        }
        String methodName = method.getName();
        if (StringUtils.pathEquals(methodName, "hello")) {
            System.out.println("hello hello");
        }
        if (StringUtils.pathEquals(methodName, "world")) {
            System.out.println("world world");
        }

        //获取方法参数type
        //TODO 获取不到继承MyBaseMapper<T>的AMapper、BMapper包装的泛型类型
        System.out.println(method.getDeclaringClass());
        return null;
    }
}
