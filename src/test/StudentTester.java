package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import system.Course;
import system.Student;

class StudentTester {

	@Test
	void testGetID() {
		LinkedList<Course> BearCourses = new LinkedList<Course>();
		Student Bear = new Student("12345", "Bear", "WUSTL", 21, BearCourses);
		String BearID = Bear.getId();
		
		assertTrue(BearID.equals("12345"));
	}

	@Test
	void testGetFirstName() {
		LinkedList<Course> BearCourses = new LinkedList<Course>();
		Student Bear = new Student("12345", "Bear", "WUSTL", 21, BearCourses);
		String BearFirstName = Bear.getFirstName();
		
		assertTrue(BearFirstName.equals("Bear"));
	}
	
	@Test
	void testGetLastName() {
		LinkedList<Course> BearCourses = new LinkedList<Course>();
		Student Bear = new Student("12345", "Bear", "WUSTL", 21, BearCourses);
		String BearLastName = Bear.getLastName();
		
		assertTrue(BearLastName.equals("WUSTL"));
	}
	
	@Test
	void testGetAge() {
		LinkedList<Course> BearCourses = new LinkedList<Course>();
		Student Bear = new Student("12345", "Bear", "WUSTL", 21, BearCourses);
		int BearAge = Bear.getAge();
		
		assertEquals(BearAge, 21);
	}
	
	@Test
	void testGetCourseCode() {
		LinkedList<Course> BearCourses = new LinkedList<Course>();
		Student Bear = new Student("12345", "Bear", "WUSTL", 21, BearCourses);
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
		Bear.addCourse(calculus);
		LinkedList<String> BearCode = Bear.getCourseCodes();
		
		assertTrue((BearCode.getFirst()).equals(calculus.code));
	}

}
