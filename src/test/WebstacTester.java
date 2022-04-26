package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import sys.Course;
import sys.Student;
import sys.Webstac;

class WebstacTester {

	List<String> bearCourses = new ArrayList<String>();
	Student bear = new Student("123450", "Bear", "WUSTL", 21, bearCourses);
	LinkedList<Student> existingStudents = new LinkedList<Student>();
	LinkedList<Course> existingCourses = new LinkedList<Course>();
	LinkedList<Student> enrollmentList = new LinkedList<Student>();
	Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
	Webstac TestWebstac = new Webstac();
	
	@Test
	void testAddStudent() {
		existingStudents.add(bear);  
		TestWebstac.addStudent(bear);
		assertEquals(TestWebstac.students, existingStudents);
	}
	
	@Test
	void testAddCourse(){
		existingCourses.add(calculus);
		TestWebstac.addCourse(calculus);
		assertEquals(TestWebstac.courses, existingCourses);
	}
	
	@Test
	void testGetStudentByID() {
		TestWebstac.addStudent(bear);
		
		assertEquals(TestWebstac.getStudentById("123450"), bear);
	}
	
	@Test
	void testGetCourseByCode() {
		TestWebstac.addCourse(calculus);
		
		assertEquals(TestWebstac.getCourseByCode("Math"), calculus);
	}
	
	@Test
	void testIsNumeric() {
		assertEquals(false, Webstac.isNumeric("f"));
		assertEquals(true, Webstac.isNumeric("1"));
	}

}
