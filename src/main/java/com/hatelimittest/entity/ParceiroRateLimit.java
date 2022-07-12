package com.hatelimittest.entity;

import com.hatelimittest.enums.PricingPlan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "tb_parceiros_rate_limit")
@Getter
@Setter
public class ParceiroRateLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_parceiro")
    private Parceiro parceiro;

    private String metodo;

    private String path;

    @Enumerated(EnumType.STRING)
    private PricingPlan plan;
}
