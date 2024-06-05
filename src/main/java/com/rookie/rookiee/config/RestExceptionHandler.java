package com.rookie.rookiee.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.rookie.rookiee.dto.ErrorDto;
import com.rookie.rookiee.exception.AppException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity.status(ex.getCode())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }

    // @ExceptionHandler(value = { RuntimeException.class })
    // @ResponseBody
    // public ResponseEntity<String> handleAllException(AppException ex) {
    // return ResponseEntity.internalServerError().body("Error");

    // }

}
