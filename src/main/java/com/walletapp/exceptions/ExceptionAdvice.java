package com.walletapp.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

	@ExceptionHandler(value = {UserNotFoundException.class})
    public void handleUserNotFoundException(UserNotFoundException e, HttpServletResponse response) throws IOException {
        log.error("Not found exception: ", e);
		response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}