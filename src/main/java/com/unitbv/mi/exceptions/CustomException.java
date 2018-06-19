package com.unitbv.mi.exceptions;

public class CustomException extends Exception {

	private static final long serialVersionUID = 2539329045216990635L;

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}
}
