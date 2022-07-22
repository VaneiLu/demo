package com.example.demo.myannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类似@Mapper
 * 标记需要代理的接口
 * @author lujp
 * 2022/7/22-11:39
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyMapper {
}
