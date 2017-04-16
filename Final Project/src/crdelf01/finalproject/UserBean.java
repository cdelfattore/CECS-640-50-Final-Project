package crdelf01.finalproject;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String first;
	private String last;
	private String username;
	/*private String password;*/
	private String email;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zip;
	private int userid;
	
	/*Blank constructor*/
	public UserBean(){
		
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "UserBean [first=" + first + ", last=" + last + ", username=" + username + ", email=" + email
				+ ", phone=" + phone + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", userid=" + userid + "]";
	}

	
}
