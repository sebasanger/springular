package com.sanger.springular.error.exceptions;

public class UserCreateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6656042484402959674L;

	public UserCreateException() {
		super("Error al crear un nuevo usuario");
	}

	public UserCreateException(String err) {
		super("Error al crear un nuevo usuario " + err);
	}

}
