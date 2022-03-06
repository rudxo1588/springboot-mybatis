package com.mybatis.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {BindException.class})
	protected ResponseEntity<String> handelBindException(BindingResult bindingResult) {
		String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
		
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}
}
