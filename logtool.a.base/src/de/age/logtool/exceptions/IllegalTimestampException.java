package de.age.logtool.exceptions;

public class IllegalTimestampException extends RuntimeException {
	
	private static final long serialVersionUID = -4175830745601756223L;

	private IllegalTimestampException(String message) {
		super(message);
	}

	public static IllegalTimestampException nullTimestamp() {
		return new IllegalTimestampException("Timestamp must not be <null>");
	}

}
