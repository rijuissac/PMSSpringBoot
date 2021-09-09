package com.example.PMS1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class Pms1ApplicationTests {

	@Autowired
	PMSController con;
	
	@MockBean
	PMSRepository repository;
	
	@MockBean
	PMSAddService addService;
	
	@Test
	public void addPizzaValid()
	{
		
		when(addService.validateExistingRecord("VeggieSupreme1")).thenReturn(false);
		ResponseEntity response = con.addPizza(addPizzaPayloadTC1());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		PMSAddResponse adresponse = (PMSAddResponse) response.getBody(); 
		assertEquals("Success: Product is added", adresponse.getMsg());
		
	}
	@Test
	public void addPizzaInvalid()
	{
		
		when(addService.validateExistingRecord("VeggieSupreme1")).thenReturn(true);
		ResponseEntity response = con.addPizza(addPizzaPayloadTC1());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		PMSAddResponse adresponse = (PMSAddResponse) response.getBody(); 
		assertEquals("Product Already exists", adresponse.getMsg());
		
	}
	public PMS addPizzaPayloadTC1()
	{
		PMS pms = new PMS();
		
		pms.setProduct("VeggieSupreme");
		pms.setPrice("500");
		return pms;
	}
	
}
