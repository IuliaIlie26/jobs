package com.unitbv.mi.classes;

public class PersonalData {

	private String lastname;
	private String email;
	private String phoneNumber;
	private String city;
	private String country, gender; 
		
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public PersonalData(String name, String lastname,  String gender, String email, String phoneNumber,  String city, String country) {
		super();
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.country = country;
		this.name = name;
		this.gender=gender;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
