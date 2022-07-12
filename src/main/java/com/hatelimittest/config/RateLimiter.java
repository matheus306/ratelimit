package com.hatelimittest.config;

import com.hatelimittest.enums.PricingPlan;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.redis.redisson.cas.RedissonBasedProxyManager;

public class RateLimiter {

    private final RedissonBasedProxyManager redissonBasedProxyManager;

    public RateLimiter(RedissonBasedProxyManager redissonBasedProxyManager) {
        this.redissonBasedProxyManager = redissonBasedProxyManager;
    }

    public boolean tryAccess(String loginAndMethodAndPath, PricingPlan pricingPlan) {

        BucketConfiguration bucketConfiguration = BucketConfiguration.builder()
                .addLimit(pricingPlan.getLimit()
                .withInitialTokens(pricingPlan.getBucketCapacity()))
                .build();

        return redissonBasedProxyManager
                .builder()
                .build(loginAndMethodAndPath, bucketConfiguration)
                .tryConsume(1);
    }
}
