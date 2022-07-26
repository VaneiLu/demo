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

        //实现了获取方法参数中的泛型
        System.out.println(AopUtils.getTargetClass("方法参数类型  " + args[0]));

        //TODO 怎样获取代理类中的泛型
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


        //TODO 获取不到继承MyBaseMapper<T>包装的泛型类型
        System.out.println("方法声明类   " + method.getDeclaringClass());

        return null;
    }
}
