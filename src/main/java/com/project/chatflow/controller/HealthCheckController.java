package com.project.chatflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.chatflow.service.HealthCheckService;

@RestController
@RequestMapping("/api/status")
public class HealthCheckController {

	@Autowired
    private HealthCheckService healthCheckService;
	
	@GetMapping("/database")
    public String checkDatabaseStatus() {
        boolean isConnected = healthCheckService.checkConnection();
        if (isConnected) {
            return "Conexión a la base de datos: OK";
        } else {
            return "Conexión a la base de datos: ERROR";
        }
    }
}
