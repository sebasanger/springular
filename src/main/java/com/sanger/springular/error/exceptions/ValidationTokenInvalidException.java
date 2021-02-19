package com.sanger.springular.error.exceptions;

public class ValidationTokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = -7978601526802035152L;

	public ValidationTokenInvalidException() {
		super("El token no es valido");
	}

}
