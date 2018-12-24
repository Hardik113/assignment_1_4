package assignment.two;

import java.io.Serializable;
import java.util.*;

public class User implements Comparable<User>, Serializable {

	private String name;
	private int age;
	private String address;
	private int rollNumber;
	private HashSet<String> courses;
	
	public User() {
		
	}
	
	public User(String name, int age, String address, int rollNumber, Set<String> courses) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.rollNumber = rollNumber;
		this.courses = (HashSet<String>) courses;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public Set<String> getCourses() {
		return courses;
	}

	public void setCourses(HashSet<String> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return name + " \t " + rollNumber + " \t\t " + age + " \t " + address + " \t\t " + courses + "\n";
	}

	@Override
	public int compareTo(User user) {
		
		int result = this.name.compareTo(user.getName());
		
		 if (result != 0) {
			 return result;
		 } else {
			 return this.rollNumber <  user.getRollNumber() ? -1 :this.rollNumber == user.getRollNumber() ? 0 : 1;
		 }
	}

}
