package com.mybatis.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handelMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		log.warn("MethodArgumentNotValidException ",request.getRequestURI(), e.getStackTrace());
		
		Map<String, Object> result = new HashMap<>();
		result.put("reason", e.getAllErrors().get(0).getDefaultMessage());
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BindException.class)
	protected ResponseEntity<Object> handBindException(BindException e, HttpServletRequest request) {
		String msg = e.getAllErrors().get(0).getDefaultMessage();
		log.debug("BindException " ,request.getRequestURI(), msg);
		Map<String, Object> result = new HashMap<>();
		result.put("reason", msg);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
