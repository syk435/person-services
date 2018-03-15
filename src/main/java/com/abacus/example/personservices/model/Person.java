package com.abacus.example.personservices.model;

public class Person {
	
	private String id = "-1";
	private String name;
	private int age;
	private String locale;
	
	public Person(String id, String name, int age, String locale) {
		super();
		this.id = id;
		this.name = name;
		this.locale = locale;
		this.age = age;
		
	}
	
	public Person(String name, int age, String locale) {
		super();
		this.name = name;
		this.locale = locale;
		this.age = age;
		
	}
	
	public Person(Person person) {
		super();
		this.id = person.getId();
		this.name = person.getName();
		this.locale = person.getLocale();
		this.age = person.getAge();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", locale=" + locale + "]";
	}
	
}
