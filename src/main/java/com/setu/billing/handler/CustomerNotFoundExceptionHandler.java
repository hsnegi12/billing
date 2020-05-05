package com.setu.billing.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.setu.billing.utils.CustomerNotFoundException;
import com.setu.billing.utils.ErrorCodeFormat;

@ControllerAdvice
@Slf4j
public class CustomerNotFoundExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<ErrorCodeFormat> handleNotFoundException(CustomerNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    private ResponseEntity<ErrorCodeFormat> error(HttpStatus status, CustomerNotFoundException e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getErrorCodeFormat());

    }
}
