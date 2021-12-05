package com.employee.exception;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandlerException extends ResponseEntityExceptionHandler{
	 
   @Override
   	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
   			HttpHeaders headers, HttpStatus status, WebRequest request) {
   		Map< String, Object> errors = new HashMap<String, Object>();
   		errors.put("timeStamp", new java.util.Date());
   		errors.put("status", status.value());
   		List<String>  error = ex.getBindingResult().getFieldErrors().stream().map(x ->
   			x.getDefaultMessage()).collect(Collectors.toList());
   		System.out.println("666666666666666");
   		errors.put("error", error);
   		
   		return new ResponseEntity<>(errors,headers,status);
   	}	
}
