package com.intercorp.intercorp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok().body("pong");
    }
}
