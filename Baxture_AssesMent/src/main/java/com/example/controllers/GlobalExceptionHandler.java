package com.example.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice

public class GlobalExceptionHandler {

	//Method Not Found Or End Point Not Found
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleNotFoundError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The requested resource was not found on this server.");
    }

	// custome message for 500 internal server error
	  @ExceptionHandler(Exception.class) public ResponseEntity<Object>
	  handleInternalServerError(Exception ex) {
	  
	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	  .body("An internal server error occurred. Please try again later."); }
	 
}

