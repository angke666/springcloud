package com.qianjin.ssm.upms.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 配置druid的spring监控，作用：可以在druid的spring监控界面看到请求的调用的类和方法
 * 参考资料地址：https://blog.csdn.net/likaya20/article/details/79047064
 */
@Configuration
public class DruidConfig {

    /**
     * druid提供的拦截器
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
        return dsInterceptor;
    }

    /**
     * 为使用正则表达式配置切点
     * @return
     */
    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern("com.qianjin.ssm.userservice.logic.service.*");
        pointcut.setPattern("com.qianjin.ssm.userservice.logic.dao.*");
        return pointcut;
    }

    /**
     * 定义advice及 pointcut 属性。advice指定使用的通知方式，也就是druid提供的
     * @param druidStatInterceptor pointcut指定切入点
     * @param druidStatPointcut
     * @return
     */
    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }
}
