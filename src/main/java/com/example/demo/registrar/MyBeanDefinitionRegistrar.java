package com.example.demo.registrar;

import com.example.demo.myannotation.EnableScan;
import com.example.demo.myclassscan.MyClassScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * @author lujp
 * 2022/7/22-14:40
 **/
@Slf4j
public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 获取类上的EnableDemo注解的属性
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableScan.class.getName());

        if (Objects.isNull(map)) {
            return;
        }

        // 类扫描器
        MyClassScan scan = new MyClassScan(registry);

        // 从注解配置中获取扫描的包路径进行扫描
        int scanCount = scan.scan((String[]) map.get("value"));
        log.info("扫描到{}个mapper", scanCount);
    }
}
