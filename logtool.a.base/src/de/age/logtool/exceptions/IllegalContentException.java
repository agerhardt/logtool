package de.age.logtool.exceptions;

public class IllegalContentException extends RuntimeException {

	private static final long serialVersionUID = -7497386940558271282L;

	private IllegalContentException(String message) {
		super(message);
	}

	public static IllegalContentException nullContent() {
		return new IllegalContentException("Content must not be <null>");
	}

}
