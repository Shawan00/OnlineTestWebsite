package com.exam.ptitexam.exceptions;


import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RuntimeException {

  private String message;

  private HttpStatus status;

  private String[] params;


  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String[] getParams() {
    return params;
  }

  public void setParams(String[] params) {
    this.params = params;
  }

  public UnauthorizedException(String message) {
    super(message);
    this.status = HttpStatus.UNAUTHORIZED;
    this.message = message;
  }

  public UnauthorizedException(HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

  public UnauthorizedException(String message, String[] params) {
    super(message);
    this.status = HttpStatus.UNAUTHORIZED;
    this.message = message;
    this.params = params;
  }

  public UnauthorizedException(HttpStatus status, String message, String[] params) {
    super(message);
    this.status = status;
    this.message = message;
    this.params = params;
  }

}