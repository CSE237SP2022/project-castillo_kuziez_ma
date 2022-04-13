package system;

import java.util.*;


public class Student {
	
	public String id;
	public String firstName;
	public String lastName;
	public int age;
	public LinkedList<Course> courses;

	
	public Student() {
		id = "000000";
		firstName = "John";
		lastName = "Doe";
		age = 18;
		courses = new LinkedList<Course>();
	}

	public Student(String id, String firstName, String lastName, int age, LinkedList<Course> courses) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.courses = courses;
	}
	
	public Student(String id, String firstName, String lastName, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.courses = new LinkedList<Course>();
	}
	
	
	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public LinkedList<String> getCourseCodes() {
		LinkedList<String> codes = new LinkedList<String>();
    	
    	for (Course c : courses) codes.add(c.code);
    	
    	return codes;
	}
	
	
	public void addCourse(Course course) {
		LinkedList<String> codes = this.getCourseCodes();
		
		if (!codes.contains(course.code)) courses.add(course);
	}
	
}

