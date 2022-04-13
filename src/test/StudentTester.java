package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import sys.Course;
import sys.Student;

class StudentTester {

	List<String> bearCourses = new ArrayList<String>();
	Student bear = new Student("12345", "Bear", "WUSTL", 21, bearCourses);
	Course alreadyIn = new Course("cse000", "in", 3, new LinkedList<Student>());
	Course notIn = new Course("cse237", "Programming Tools and Techniques", 3, new LinkedList<Student>());
	
	@Test
	void testGetId() {
		String bearId = bear.getId();
		assertTrue(bearId.equals("12345"));
	}

	@Test
	void testGetFirstName() {
		String bearFirstName = bear.getFirstName();
		assertTrue(bearFirstName.equals("Bear"));
	}
	
	@Test
	void testGetLastName() {
		String bearLastName = bear.getLastName();
		assertTrue(bearLastName.equals("WUSTL"));
	}
	
	@Test
	void testGetAge() {
		int bearAge = bear.getAge();
		assertEquals(bearAge, 21);
	}
	
	@Test
	void testGetCourses() {
		List<String> courses = bear.getCourses();
		assertEquals(courses, bearCourses);
	}
	
	@Test
	void testAddCourse() {
		bearCourses.add(alreadyIn.code);
		assertTrue(!bear.addCourse(alreadyIn));
		assertTrue(bear.addCourse(notIn));
	}

}
