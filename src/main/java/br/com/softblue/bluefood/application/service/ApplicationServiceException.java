package br.com.softblue.bluefood.application.service;

public class ApplicationServiceException extends RuntimeException {

	public ApplicationServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ApplicationServiceException(String message) {
		super(message);
		
	}

	public ApplicationServiceException(Throwable cause) {
		super(cause);
		
	}

	
}
