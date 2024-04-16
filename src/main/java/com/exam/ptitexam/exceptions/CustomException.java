package com.exam.ptitexam.exceptions;

import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException {

  private Object errMessage;

  private HttpStatus status;

  private String[] params;

  public Object getErrMessage() {
    return errMessage;
  }

  public void setErrMessage(Object errMessage) {
    this.errMessage = errMessage;
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

  public CustomException(String errMessage) {
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.errMessage = errMessage;
  }

  public CustomException(HttpStatus status, Object errMessage) {
    this.errMessage = errMessage;
    this.status = status;
  }

  public CustomException(String errMessage, String[] params) {
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.errMessage = errMessage;
    this.params = params;
  }

  public CustomException(HttpStatus status, String errMessage, String[] params) {
    this.status = status;
    this.errMessage = errMessage;
    this.params = params;
  }

}
