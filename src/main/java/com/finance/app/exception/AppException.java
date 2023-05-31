package com.finance.app.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppException {
    private int statusCode;
    private String message;

    public AppException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
