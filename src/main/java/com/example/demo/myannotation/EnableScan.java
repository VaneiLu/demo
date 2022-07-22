package com.example.demo.myannotation;

import com.example.demo.registrar.MyBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动接口扫描的注解
 * @author lujp
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({MyBeanDefinitionRegistrar.class})
public @interface EnableScan {
    /**
     * 扫描包路径
     */
    String[] value() default "";
}
