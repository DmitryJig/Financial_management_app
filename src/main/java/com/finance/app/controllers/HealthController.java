package com.finance.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HealthController implements HealthControllerInterface {
    @Override
    public String getHello() {
        return "This is a financial management app";
    }

    @Override
    public String getSecuredData() {
        return "This is very sensitive information, only for authorized users";
    }
}
