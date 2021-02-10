package com.example;

public class CursoException extends Exception {
	public CursoException(String message) {
		super(message);
	}
	public CursoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CursoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
