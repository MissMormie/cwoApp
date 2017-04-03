/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.cwo_app.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Sonja
 */
@ControllerAdvice
public class ExceptionHandlerController {

  @ResponseStatus(value=HttpStatus.CONFLICT, reason="Data integrity violation")  // Error 409
  @ExceptionHandler(DataIntegrityViolationException.class)
  public void MySQLIntegrityConstraintViolationException() {
  }
  
  
  @ResponseStatus(value=HttpStatus.CONFLICT, reason="Empty Result Data Access Exception")  // Error 409
  @ExceptionHandler(EmptyResultDataAccessException.class)
  public void EmptyResultDataAccessException() {
  }  
  
  @ResponseStatus(value=HttpStatus.CONFLICT, reason="Unknown Exception")  // Error 409
  @ExceptionHandler(Exception.class)
  public void Exception() {
  }  
}
