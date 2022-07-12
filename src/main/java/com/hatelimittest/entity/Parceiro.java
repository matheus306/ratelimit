package com.hatelimittest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_parceiros")
@Getter
@Setter
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String login;

    @Column(name = "rate_limit")
    private Boolean rateLimit;

    @OneToMany(mappedBy = "parceiro", fetch = FetchType.EAGER)
    private List<ParceiroRateLimit> parceiroRateLimits;
}
