package com.noob.resourceserver.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<Exception> handleAuthenticationException(Exception ex) {
        // debug 用
        ex.printStackTrace();

        return ResponseEntity.ok(ex);
    }
}