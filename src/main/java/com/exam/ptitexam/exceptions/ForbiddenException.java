package com.exam.ptitexam.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeException {

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

  public ForbiddenException(String message) {
    super(message);
    this.status = HttpStatus.FORBIDDEN;
    this.message = message;
  }

  public ForbiddenException(HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

  public ForbiddenException(String message, String[] params) {
    super(message);
    this.status = HttpStatus.FORBIDDEN;
    this.message = message;
    this.params = params;
  }

  public ForbiddenException(HttpStatus status, String message, String[] params) {
    super(message);
    this.status = status;
    this.message = message;
    this.params = params;
  }

}