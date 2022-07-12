package com.hatelimittest.service;

import com.hatelimittest.config.RateLimiter;
import io.github.bucket4j.redis.redisson.cas.RedissonBasedProxyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MelhorSquadServices {

    private final RedissonBasedProxyManager proxyManager;

    @Autowired
    private RateLimiter rateLimiter;

    public MelhorSquadServices(RedissonBasedProxyManager proxyManager) {
        this.proxyManager = proxyManager;
    }

    public String bordoesDoPedrim() {
        List<String> bordoes = List.of("somente apenas", "quêIssuuuuu", "forget", "não tem como", "ta mec");
        return bordoes.get(new Random().nextInt(bordoes.size()));
    }
}
