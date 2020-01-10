package com.macro.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import com.macro.mall.util.CacheEngine;
import com.macro.mall.util.DefaultCacheEngine;

/**
 * 应用启动入口
 * Created by macro on 2018/4/26.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching

public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }
    @Bean(name = { "cacheEngine" })
    public CacheEngine cacheEngine() {
        return new DefaultCacheEngine();
    }
}
