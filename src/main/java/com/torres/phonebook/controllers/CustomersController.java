package com.torres.phonebook.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.torres.phonebook.models.Country;
import com.torres.phonebook.models.Customer;
import com.torres.phonebook.repositories.Customers;

@Controller
public class CustomersController {

	@Autowired
	private Customers repository;

	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request) {

		Example<Customer> example = getExample(request);
		Page<Customer> customers = repository.findAll(example, getPageRequest(request));
		ModelAndView mv = new ModelAndView();

		mv.addObject("customers", customers);
		mv.addObject("countries", Country.values());
		mv.setViewName("customers/index");
		
		return mv;
	}
	
	@PostMapping("/")
	public String save(HttpServletRequest request) {
		Customer c = createCustomerByRequest(request);
		c.setPhone(String.format("(%s) %s", c.getCountry().getCode().substring(1), c.getPhone()));
		c.setState(c.getCountry().check(c.getPhone()) ? 1 : 0);
		repository.save(c);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		repository.deleteById(id);
		return "redirect:/";
	}
	
	@PostMapping("/{id}")
	public String edit(@PathVariable("id") Integer id, HttpServletRequest request) {
		Customer c = repository.getOne(id);
		Customer caux = createCustomerByRequest(request);
		c.setName(caux.getName());
		c.setCountry(caux.getCountry());
		c.setPhone(String.format("(%s) %s", caux.getCountry().getCode().substring(1), caux.getPhone()));
		c.setState(caux.getCountry().isValid(c.getPhone()) ? 1 : 0);
		repository.save(c);
		return "redirect:/";
	}
	
	private PageRequest getPageRequest(HttpServletRequest request) {
		int page = 0, size = 10;
		if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1;
		}
		if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size = Integer.parseInt(request.getParameter("size"));
		}
		return PageRequest.of(page, size);
	}
	
	private Customer createCustomerByRequest(HttpServletRequest request) {
		Customer c = new Customer();
		
		String id = request.getParameter("id");
		if (id != null && !id.equals("")) {
			c.setId(Integer.getInteger(id, null));
		}
		
		String name = request.getParameter("name");
		if (name != null && !name.equals("")) {
			c.setName(name);
		}
		
		c.setCountry(Country.fromString(request.getParameter("country")));
		
		String state = request.getParameter("state");
		if (state != null && !state.isEmpty() && !state.equals("all")) {
			if (state.equals("valid")) {
				c.setState(1);
			} else {
				c.setState(0);
			}
		}
		
		String phone = request.getParameter("phone");
		if (phone != null && !phone.isEmpty()) {
			c.setPhone(phone);
		}
		return c;
	}
	
	private Example<Customer> getExample(HttpServletRequest request) {
		Customer c = createCustomerByRequest(request);
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())				 
				.withIgnoreNullValues();                        
		Example<Customer> example = Example.of(c, matcher);
		
		return example;
	}
}