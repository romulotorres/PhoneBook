package com.torres.phonebook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.torres.phonebook.exceptions.InvalidPhoneCodeException;

@Entity
public class Customer {
	
	public Customer() {}
	
	private Customer(String name, String phone) throws InvalidPhoneCodeException {
        this.name = name;
        this.phone = phone;
        this.country = Country.byCode(String.format("+%s", phone.substring(1, 4)));
        this.state = this.phone.matches(this.country.getRegexValidate()) ? 1 : 0;
    }

	public static Customer from(String name, String phone) throws InvalidPhoneCodeException {
        return new Customer(name, phone);
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String name;

	@Column(length = 50)
	private String phone;

	@Enumerated(EnumType.STRING)
	private Country country;

	private Integer state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}