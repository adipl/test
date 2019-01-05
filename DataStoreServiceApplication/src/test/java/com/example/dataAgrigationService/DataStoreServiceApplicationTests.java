package com.example.dataAgrigationService;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataStoreServiceApplicationTests {

	
	private MockMvc mvc;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	WebApplicationContext webApplicationContext;


	protected void setUp() 
	{
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void agregateFileWithValidFile() throws Exception 
	{
		setUp();
		
		String expected="[{\"name\":\"inbar\",\"ammountOfMoney\":14400,\"creditCard\":\"123124134\",\"id\":\"122\"},{\"name\":\"adi\",\"ammountOfMoney\":29900,\"creditCard\":\"123124134\",\"id\":\"122\"},{\"name\":\"ori\",\"ammountOfMoney\":29450,\"creditCard\":\"123124134\",\"id\":\"122\"},{\"name\":\"shiri\",\"ammountOfMoney\":9485,\"creditCard\":\"123124134\",\"id\":\"122\"}]";
		
		
		String requestJson = "{\"mrequestType\":null,\"mrequestRowData\":{\"inbar\":14400,\"adi\":29900,\"ori\":29450,\"shiri\":9485},\"merrorDescription\":null}";
		
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/store").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String actual = mvcResult.getResponse().getContentAsString();
		
		Assert.assertEquals(expected, actual);
				 
	}
}

