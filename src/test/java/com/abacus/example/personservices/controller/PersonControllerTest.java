package com.abacus.example.personservices.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abacus.example.personservices.model.Person;
import com.abacus.example.personservices.service.PersonService;

public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	Person mockPerson = new Person("Sam", 15, "Atlanta");

	String exampleJson = "{\"name\":\"Sam\",\"age\": 15,\"locale\":\"Atlanta\"}";

	@Test
	public void testAddPerson() throws Exception {
		//PUT testing
		personService = new PersonService();
		Person pResult = personService.addPerson(mockPerson);
		System.out.println(pResult.toString());
		String testRegex = ".*, name=Sam, age=15, locale=Atlanta.*";
		assertTrue(pResult.toString().matches(testRegex));
	}
	
	@Test
	public void testGetPerson() throws Exception {
		//setup
		personService = new PersonService();
		Person pResult = personService.addPerson(mockPerson);
		String res = pResult.toString();
		System.out.println(res);
		
		int commaIndex = res.indexOf(',');
		String data_id = res.substring(11, commaIndex);
		System.out.println(data_id);
		
		//GET testing
		Person pGet = personService.retrievePerson(data_id);	
		String testRegex = ".*, name=Sam, age=15, locale=Atlanta.*";
		assertTrue(pGet.toString().matches(testRegex));
	}
	
	@Test
	public void testRemovePerson() throws Exception {
		//setup
		personService = new PersonService();
		Person pResult = personService.addPerson(mockPerson);
		String res = pResult.toString();
		System.out.println(res);
		
		int commaIndex = res.indexOf(',');
		String data_id = res.substring(11, commaIndex);
		System.out.println(data_id);
		
		//DEL testing
		Person pGet = personService.removePerson(data_id);
		String testRegex = ".*, name=Sam, age=15, locale=Atlanta.*";
		assertTrue(pGet.toString().matches(testRegex));
		pGet = personService.removePerson(data_id);
		assertTrue(pGet==null);
	}
	
/*	@Test
	public void integrationTests() throws Exception {

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/resources/data")
				.accept(MediaType.APPLICATION_JSON).content(exampleJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		System.out.println(response);
		JSONObject putJson = new JSONObject(response.toString());
		String data_id = putJson.getString("id");
		Mockito.when(
				personService.retrievePerson(Mockito.anyString())).thenReturn(mockPerson);

		RequestBuilder requestBuilder1 = MockMvcRequestBuilders.get(
				"/resources/data/" + data_id).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result1 = mockMvc.perform(requestBuilder1).andReturn();

		System.out.println(result1.getResponse());
		String expected = "{resourceType:Person,name:Sam,description:10 Steps}";

		// {"resourceType":"Person","id":{data_id},"name":"Sam","age":"15,"locale":"Atlanta"}

		JSONAssert.assertEquals(expected, result1.getResponse()
				.getContentAsString(), false);
	}
*/
}
