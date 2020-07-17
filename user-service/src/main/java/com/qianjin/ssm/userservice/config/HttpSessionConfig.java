package com.qianjin.ssm.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * 解决分布式集群session共享
 * （采由 httpheader 管理 sessionid）
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    /**
     * 替代默认使用的cookie
     * （可以解决浏览器禁用cookie的情况）
     * @return
     */
    @Bean
    public HttpSessionIdResolver httpSessionStrategy() {
        HttpSessionIdResolver httpSessionStrategy = new HeaderHttpSessionIdResolver("X-Auth-Token");
        return httpSessionStrategy;
    }

}
