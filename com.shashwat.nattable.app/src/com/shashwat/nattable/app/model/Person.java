package com.shashwat.nattable.app.model;

import java.util.Date;

public class Person {
	private int uid;
	private String firstName;
	private String lastName;
	private Gender gender;
	private boolean isMarried;
	private Date birthday;

	/**
	 * @param uid
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param isMarried
	 * @param birthday
	 */
	public Person(int uid, String firstName, String lastName, Gender gender, boolean isMarried, Date birthday) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.isMarried = isMarried;
		this.birthday = birthday;
	}

	public Person(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the isMarried
	 */
	public boolean isMarried() {
		return isMarried;
	}

	/**
	 * @param isMarried
	 *            the isMarried to set
	 */
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
}
