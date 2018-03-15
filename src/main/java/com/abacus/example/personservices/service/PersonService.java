package com.abacus.example.personservices.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.abacus.example.personservices.model.Person;

@Component
public class PersonService {
	
	//using arraylist as temporary datastorage for example rest api
	private static List<Person> peopleStore = new ArrayList<>();
	
	private SecureRandom random = new SecureRandom();

	public Person retrievePerson(String data_id) {
		for (Person person : peopleStore) {
			if (person.getId().equals(data_id)) {
				return person;
			}
		}
		return null;
	}
	
	public Person removePerson(String data_id) {
		for (Person person : peopleStore) {
			if (person.getId().equals(data_id)) {
				Person tempPerson = new Person(person);
				peopleStore.remove(person);
				return tempPerson;
			}
		}
		return null;
	}
	
	public Person addPerson(Person person) {
		if (person == null) {
			return null;
		}
		
		for (Person person1 : peopleStore) {
			if (person1.getName().equals(person.getName()) && person1.getAge()==person.getAge() && person1.getLocale().equals(person.getLocale())) {
				return person1;
			}
		}
		
		String randomId = new BigInteger(130, random).toString(32);
		person.setId(randomId);
		peopleStore.add(person);
		return person;
	}
	
}
