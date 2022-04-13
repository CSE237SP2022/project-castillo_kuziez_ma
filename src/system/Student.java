package system;

import java.util.*;


public class Student {
	
	private String id;
	private String firstName;
	private String lastName;
	private int age;
	private List<String> courses;

	
	public Student() {
		id = "000000";
		firstName = "John";
		lastName = "Doe";
		age = 18;
		courses = new ArrayList<String>();
	}

	public Student(String id, String firstName, String lastName, int age, List<String> courses) {
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
		this.courses = new ArrayList<String>();
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
	
	public List<String> getCourses() {
		return courses;
	}
	
	
	public boolean addCourse(Course course) {
		if (!courses.contains(course.code)) {
			courses.add(course.code);
			return true;
		}
		return false;
	}
	
}

