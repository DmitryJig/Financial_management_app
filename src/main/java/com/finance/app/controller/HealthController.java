package com.finance.app.controller;

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
}
