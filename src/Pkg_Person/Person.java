package Pkg_Person;

import java.io.Serializable;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
abstract public class Person implements Serializable  {       //Base Class
	protected String name;
	protected String emailid;
	protected String phoneNO;
	protected String address;
	protected String dob;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		boolean isValidName = Pattern.matches("[a-zA-Z]+", name);
		if(isValidName) {
			this.name = name;
		}
		else {
			this.name = "default Name";
		}
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		boolean isValidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		if(isValidDob) {
			this.dob = dob;
		}
		else {
			this.dob="01-06-2005";
		}
	}
	public Person(String name, String emailid, String phoneNO, String address, String dob) {
		super();
		this.setName(name);
		this.emailid = emailid;
		this.phoneNO = phoneNO;
		this.address = address;
		this.setDob(dob);
	}
	public Person() {
		super();
	}
	
}
