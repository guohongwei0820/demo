package com.example.demo.excepion;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NormalAdvice {
	/**
	 * 404
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String entityNotFoundHandler(EntityNotFoundException e) {
		return e.getMessage()+"_EntityNotFoundException ";
	}
	
	/**
	 * 500
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private String internalServerErrorHandler(RuntimeException e) {
		return e.getMessage();
	}
	
	
}
