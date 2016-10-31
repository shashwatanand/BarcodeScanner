package com.shashwat.nattable.app.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
		if (person.getGender().equals(Gender.MALE)) {
			person.setFirstName(maleNames[ranGen.nextInt(maleNames.length)]);
		} else {
			person.setFirstName(femaleNames[ranGen.nextInt(femaleNames.length)]);
		}
		
		person.setLastName(lastNames[ranGen.nextInt(lastNames.length)]);
		person.setMarried(ranGen.nextBoolean());
		
		int month = ranGen.nextInt(12);
	    
		int day = 0;
	    if (month == 2) {
	      day = ranGen.nextInt(28);
	    } else {
	      day = ranGen.nextInt(30);
	    }
	    
	    int year = 1920 + ranGen.nextInt(90);
		
		LocalDate date = LocalDate.of(year, month, day);
		person.setBirthday(Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		return person;
	}
}
