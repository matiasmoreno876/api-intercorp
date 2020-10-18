package com.intercorp.intercorp.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PingController {

    @ApiOperation(value = "Endpoint de test, check rapido para ver si la api esta arriba")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok().body("pong");
    }
}
