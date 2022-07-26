package com.example.demo.mybasemapper;

/**
 * @author lujp
 * 2022/7/22-11:43
 **/
public interface MyBaseMapper<T> {
    /**
     * 测试方法
     * @return String
     */
    T hello(T type);

    /**
     * 测试方法2
     * @return Integer
     */
    T world(T type);
}
