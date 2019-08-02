package com.stackroute.muzix.exceptions;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        //For handling track not found exception
        @ExceptionHandler(TrackNotFoundException.class)
        public ResponseEntity <Object> notFoundException(Exception e, WebRequest request) {
          String response="Track not found";
            return handleExceptionInternal(e,response,new HttpHeaders(),HttpStatus.CONFLICT,request);
        }

        //For handling track already exists exception
        @ExceptionHandler(TrackAlreadyExistsException.class)
        public ResponseEntity <Object> alreadyExistsException(Exception e, WebRequest request) {
          String response="Track already exists";
          return handleExceptionInternal(e,response,new HttpHeaders(),HttpStatus.CONFLICT,request);
        }
    }

