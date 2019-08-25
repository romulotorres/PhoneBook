package com.torres.phonebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.torres.phonebook.models.Country;
import com.torres.phonebook.models.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonebookApplicationTests {

	@Test
	public void countryCheck() throws Exception {
	   assertTrue("Country MARROCO not recognizes a valid phone.", Country.MOROCCO.isValid("(212) 698054317"));
	   assertFalse("Country MARROCO not recognizes a invalid phone.", Country.MOROCCO.isValid("(213) 698054317"));
	   
	   assertTrue("Country CAMEROON not recognizes a valid phone.", Country.CAMEROON.isValid("(237) 673122155"));
	   assertFalse("Country CAMEROON not recognizes a invalid phone.", Country.CAMEROON.isValid("(238) 673122155"));
	   
	   assertTrue("Country ETHIOPIA not recognizes a valid phone.", Country.ETHIOPIA.isValid("(251) 911168450"));
	   assertFalse("Country ETHIOPIA not recognizes a invalid phone.", Country.ETHIOPIA.isValid("911168450"));
	   
	   assertTrue("Country MOZAMBIQUE not recognizes a valid phone.", Country.MOZAMBIQUE.isValid("(258) 849181828"));
	   assertFalse("Country MOZAMBIQUE not recognizes a invalid phone.", Country.MOZAMBIQUE.isValid("(258) 111849181828"));
	   
	   assertTrue("Country UGANDA not recognizes a valid phone.", Country.UGANDA.isValid("(256) 714660221"));
	   assertFalse("Country UGANDA not recognizes a invalid phone.", Country.UGANDA.isValid("(259) 714660221"));
	}
	
	@Test
	public void customerStaticConstructorCheck() throws Exception {
		Customer c = Customer.from("QWERTY UIOP", "(256) 714660221");
		assertTrue("Customer contructor not recognize a valid phone.", c.getState() == 1);
		assertEquals("Customer contructor not recognize a country.", Country.UGANDA, c.getCountry());
		assertEquals("Customer contructor not register a name.", "QWERTY UIOP", c.getName());
		assertEquals("Customer contructor not register a phone.", "(256) 714660221", c.getPhone());
	}
	
}
