package com.hatelimittest.enums;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@AllArgsConstructor
@Getter
public enum PricingPlan {

    FREE(2),

    BASIC(3),

    PROFESSIONAL(4);

    private int bucketCapacity;

    public Bandwidth getLimit() {
        return Bandwidth.classic(bucketCapacity, Refill.intervally(1, Duration.ofMinutes(1)));
    }
}
