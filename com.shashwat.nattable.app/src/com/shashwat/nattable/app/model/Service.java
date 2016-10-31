package com.shashwat.nattable.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {
	static String[] maleNames = { "Shashwat", "Manish", 
			"Felix", "Obama", 
			"Rishi", "Jaysukh", 
			"Santosh", "Prabhat"};

	static String[] femaleNames = { "Shubhangi", "Shirolly", 
			"Jingle", "Neha", 
			"Shruti", "Hena", 
			"Shraddha", "Cori"};
	
	static String[] lastNames = {"Singh", "Sinha", 
			"Varma", "Kannav", 
			"Kumar", "Pawar",
			"Sharma", "Biller"};
	
	public static List<Person> getPersons(int noOfPersons) {
		List<Person> personList = new ArrayList<>();
		
		for (int index = 0; index < noOfPersons; index++) {
			personList.add(createPerson(index));
		}
		
		return personList;
	}

	private static Person createPerson(int index) {
		Random ranGen = new Random();
		Person person = new Person(index);
		
		person.setGender(Gender.values()[ranGen.nextInt(Gender.values().length)]);
		return person;
	}
}
