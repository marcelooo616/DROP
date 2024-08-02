package io.drop.drop_api.exeption;

import org.springframework.http.HttpStatus;

public class BusinessRule extends  RuntimeException{
    public BusinessRule(HttpStatus httpStatus, String message) {
        super(message);
    }
}
