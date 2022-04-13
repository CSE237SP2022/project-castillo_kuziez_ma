package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import system.Course;
import system.Student;

class StudentTester {

	LinkedList<Course> BearCourses = new LinkedList<Course>();
	Student Bear = new Student("12345", "Bear", "WUSTL", 21, BearCourses);
	
	@Test
	void testGetID() {
		String BearID = Bear.getId();
		assertTrue(BearID.equals("12345"));
	}

	@Test
	void testGetFirstName() {
		String BearFirstName = Bear.getFirstName();
		assertTrue(BearFirstName.equals("Bear"));
	}
	
	@Test
	void testGetLastName() {
		String BearLastName = Bear.getLastName();
		assertTrue(BearLastName.equals("WUSTL"));
	}
	
	@Test
	void testGetAge() {
		int BearAge = Bear.getAge();
		assertEquals(BearAge, 21);
	}
	
	@Test
	void testGetCourseCode() {
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
		Bear.addCourse(calculus);
		LinkedList<String> BearCode = Bear.getCourseCodes();
		assertTrue((BearCode.getFirst()).equals(calculus.code));
	}

}
