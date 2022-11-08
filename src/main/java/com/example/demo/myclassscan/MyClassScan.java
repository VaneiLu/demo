package com.example.demo.myclassscan;

import com.example.demo.myfactorybean.MyFactoryBean;
import com.example.demo.myannotation.MyMapper;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Objects;
import java.util.Set;

/**
 * @author lujp
 * 2022/7/22-14:32
 **/
public class MyClassScan extends ClassPathBeanDefinitionScanner {
    public MyClassScan(BeanDefinitionRegistry registry) {
        super(registry);
        registerFilters();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {

        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        // 循环替换beanDefinition中的beanClass为FactoryBean,不进行替换的话,beanClass是接口,Spring无法通过接口得到Bean对象
        for (BeanDefinitionHolder definitionHolder : beanDefinitionHolders) {

            // 获取beanDefinition
            AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) definitionHolder.getBeanDefinition();
            // 获取扫描出的接口的beanClassName
            String beanClassName = beanDefinition.getBeanClassName();
            // 设置BeanClass为MyFactoryBean
            beanDefinition.setBeanClass(MyFactoryBean.class);
            // 添加构造方法的参数,MyFactoryBean的构造方法需要Class对象,这里设置的是string的参数,spring会使用Class.forName加载
            // 成MyFactoryBean中构造方法需要的类参数
            if (Objects.isNull(beanClassName)) {
                continue;
            }

            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
        }
        return beanDefinitionHolders;
    }

    public void registerFilters() {
        // 筛选只有包含MyMapper注解的接口
        addIncludeFilter(new AnnotationTypeFilter(MyMapper.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 接口且是独立的bean
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
