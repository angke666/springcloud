package com.qianjin.ssm.elasticsearch.config;

import com.qianjin.ssm.elasticsearch.interceptor.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class AppWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AppInterceptor appInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor).addPathPatterns("/api/app/**");
        super.addInterceptors(registry);
    }
}
