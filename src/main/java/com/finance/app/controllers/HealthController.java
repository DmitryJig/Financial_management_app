package com.finance.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HealthController {
    @GetMapping
    public String getHello() {
        return "This is a financial management app";
    }
    @GetMapping("/secured")
    public String getSecuredData() {
        return "This is very sensitive information, only for authorized users";
    }
}
