package com.torres.phonebook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.torres.phonebook.models.Customer;

public interface Customers extends JpaRepository<Customer, Integer> {}