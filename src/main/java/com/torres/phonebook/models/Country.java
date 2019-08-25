package com.torres.phonebook.models;

import com.torres.phonebook.exceptions.InvalidPhoneCodeException;

public enum Country {
	
	CAMEROON("Cameroon", "+237", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
	ETHIOPIA("Ethiopia", "+251", "\\(251\\)\\ ?[1-59]\\d{8}$"),
	MOROCCO("Morocco", "+212", "\\(212\\)\\ ?[5-9]\\d{8}$"),
	MOZAMBIQUE("Mozambique", "+258", "\\(258\\)\\ ?[28]\\d{7,8}$"),
	UGANDA("Uganda", "+256", "\\(256\\)\\ ?\\d{9}$");

	private String name;
	private String code;
	private String regexValidate;

	Country(String name, String code, String regexValidate) {
		this.name = name;
		this.code = code;
		this.regexValidate = regexValidate;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getRegexValidate() {
		return regexValidate;
	}
	
	public boolean check(String check) {
		return this.toString().equals(check);
	}
	
	public boolean isValid(String check) {
		return check.matches(regexValidate);
	}
	
	
	public static Country byCode(String code) throws InvalidPhoneCodeException {
		for (Country c : Country.values()) {
			if (c.getCode().equals(code))
				return c;
		}
		throw new InvalidPhoneCodeException();
	}
	
	public static Country fromString(String test) {
		try {
			return Country.valueOf(test);
		} catch (Exception e) {
			return null;
		}
	}
}
