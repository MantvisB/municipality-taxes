package org.example.taxes.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ExceptionResponse<T> implements Serializable {
	private static final long serialVersionUID = -6548287505343689503L;

	private int status;
	private String error;

	public ExceptionResponse() {
		this.status = HttpStatus.OK.value();
	}

	public ExceptionResponse(int statusCode, String error) {
		this();
		this.status = statusCode;
		this.error = error;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int statusCode) {
		this.status = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
