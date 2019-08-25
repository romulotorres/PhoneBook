package com.torres.phonebook.exceptions;

public class InvalidPhoneCodeException extends Exception {

	public InvalidPhoneCodeException() {
		super("The phone code provided is invalid.");
	}

	private static final long serialVersionUID = 1L;

}
