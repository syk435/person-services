package com.abacus.example.personservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abacus.example.personservices.model.Person;
import com.abacus.example.personservices.service.PersonService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PersonController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/resources/data/{data_id}")
	public ResponseEntity<String> retrievePerson(@PathVariable String data_id) {
		Person person = personService.retrievePerson(data_id);
		try {
		if (person == null) {
			logger.info("[GET]: " + data_id + " not found");
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		} else {
			logger.info("[GET]: " + data_id + " found");
			return new ResponseEntity<String>(person.toString(), HttpStatus.OK);
		}
		} catch(Exception e) {}
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/resources/data/{data_id}")
	public ResponseEntity<String> removePerson(@PathVariable String data_id) {
		Person person = personService.removePerson(data_id);
		if (person == null) {
			logger.info("[DELETE]: " + data_id + " not found");
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		} else {
			logger.info("[DELETE]: " + data_id + " deleted");
			return new ResponseEntity<String>(person.toString(), HttpStatus.OK);
		}
	}
	
	@PutMapping("/resources/data")
	public Person addPerson(HttpEntity<String> person) {
		try {
			JSONObject json = new JSONObject(person.getBody());
			logger.info("[PUT]: " + json.getString("resourceType"));
			
			if (json.getString("resourceType").equals("Person")) {
				Person p = new Person(json.getString("name"), json.getInt("age"), json.getString("locale"));
				logger.info("[PUT]: " + p.toString());
				return personService.addPerson(p);
			} else { 
				return null;
			}
		} catch(Exception e) {
			//
		}
		return null;
	}
	
}
