package com.abacus.example.ingestionservices.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.abacus.example.ingestionservices.model.Payload;

@Component
public class IngestionServices {
	
	//using arraylist as temporary datastorage for example rest api
	private static List<Payload> dataStore = new ArrayList<>();
	
	private SecureRandom random = new SecureRandom();

	public Payload runBedrockAuthService(String data_id) {
		//
		return null;
	}
	
	public Payload runBedrockWorkflowService(String data_id) {
		return null;
	}
	
	public Payload retrievePerson(String data_id) {
		for (Payload payload : dataStore) {
			if (payload.getId().equals(data_id)) {
				return payload;
			}
		}
		return null;
	}
	
	public Payload removePerson(String data_id) {
		for (Payload payload : dataStore) {
			if (payload.getId().equals(data_id)) {
				Payload tempPerson = new Payload(payload);
				dataStore.remove(payload);
				return tempPerson;
			}
		}
		return null;
	}
	
	public Payload addPerson(Payload payload) {
		if (payload == null) {
			return null;
		}
		
		for (Payload person1 : dataStore) {
			if (person1.getName().equals(payload.getName()) && person1.getAge()==payload.getAge() && person1.getLocale().equals(payload.getLocale())) {
				return person1;
			}
		}
		
		String randomId = new BigInteger(130, random).toString(32);
		payload.setId(randomId);
		dataStore.add(payload);
		return payload;
	}
	
}
