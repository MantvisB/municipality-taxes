package org.example.taxes.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2635911347810722470L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
