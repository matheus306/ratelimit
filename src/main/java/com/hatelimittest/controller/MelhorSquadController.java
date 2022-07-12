package com.hatelimittest.controller;

import com.hatelimittest.service.MelhorSquadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MelhorSquadController {

    @Autowired
    MelhorSquadServices rateLimiter;

    @GetMapping("/bordoes-pedrim")
    public ResponseEntity getBordoesPedrim(@RequestHeader("login") String login) {
        return ResponseEntity.ok(rateLimiter.bordoesDoPedrim());
    }

    @PostMapping("/bordoes-pedrim")
    public ResponseEntity postBordoesPedrim(@RequestHeader("login") String login) {
        return ResponseEntity.ok(rateLimiter.bordoesDoPedrim());
    }
}
