package de.age.logtool.exceptions;

public class IllegalLogfileListenerException extends RuntimeException {

	private static final long serialVersionUID = -2856191021641058712L;

	private IllegalLogfileListenerException(String message) {
		super(message);
	}

	public static IllegalLogfileListenerException nullListener() {
		return new IllegalLogfileListenerException("Listener must not be <null>");
	}

}
