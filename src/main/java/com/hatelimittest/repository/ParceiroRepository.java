package com.hatelimittest.repository;

import com.hatelimittest.entity.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

    Parceiro findByLogin(String login);
}
