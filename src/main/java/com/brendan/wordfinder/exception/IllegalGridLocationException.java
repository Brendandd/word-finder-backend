package com.brendan.wordfinder.exception;

/**
 * An exception thrown when referencing an illegal grid location.
 * 
 * @author Brendan Douglas
 */
public class IllegalGridLocationException extends Exception {
	private static final long serialVersionUID = -8370276655998876061L;

	public IllegalGridLocationException(String message, int row, int column) {
		super(message + ". Row=" + row + ", Column=" + column);
	}

	public IllegalGridLocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalGridLocationException(Throwable cause) {
		super(cause);
	}
}
