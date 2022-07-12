package com.hatelimittest.config;

import com.hatelimittest.interceptor.RateLimiterInterceptor;
import com.hatelimittest.repository.ParceiroRepository;
import io.github.bucket4j.distributed.proxy.ClientSideConfig;
import io.github.bucket4j.redis.redisson.cas.RedissonBasedProxyManager;
import org.redisson.api.RedissonClient;
import org.redisson.command.CommandSyncService;
import org.redisson.config.Config;
import org.redisson.config.ConfigSupport;
import org.redisson.connection.ConnectionManager;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@EnableWebMvc
@Configuration
@EnableCaching
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    ParceiroRepository repository;

    @Bean
    public RateLimiter rateLimiter() throws IOException {
        return new RateLimiter(proxyManager());
    }

    public void addInterceptors(InterceptorRegistry registry) {
        try {
            registry.addInterceptor(RateLimiterInterceptor.builder()
                    .rateLimiter(rateLimiter())
                    .repository(repository)
                    .build()).addPathPatterns("/**");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean(destroyMethod = "shutdown")
    public ConnectionManager redissonConnectionManager() throws IOException {
        File resourceURL = ResourceUtils.getFile("classpath:redis.yml");
        Config config = Config.fromYAML(resourceURL);
        return ConfigSupport.createConnectionManager(config);
    }

    @Bean
    public RedissonBasedProxyManager proxyManager() throws IOException {
        CommandSyncService commandSyncService = new CommandSyncService(redissonConnectionManager());
        return new RedissonBasedProxyManager(commandSyncService,
                ClientSideConfig.getDefault(),
                Duration.ofMinutes(10));
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) throws IOException {
        String configFileName = "cache-config.yml";
        return new RedissonSpringCacheManager(redissonClient, "classpath:" + configFileName);
    }
}
