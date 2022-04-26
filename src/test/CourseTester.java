package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import sys.Course;
import sys.Student;

class CourseTester {
	
	LinkedList<Student> enrollmentList = new LinkedList<Student>();
	LinkedList<Student> peopletoenroll = new LinkedList<Student>();
	
	Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
	
	List<String> natcourses = new ArrayList<String>();
	Student nat = new Student("123000", "nat", "cas", 22, natcourses);
	List<String> joncourses = new ArrayList<String>();
	Student jon = new Student("456000", "jon", "ma", 21, joncourses);
	List<String> fetuscourses = new ArrayList<String>();
	Student fetus = new Student("789000", "fetus", "little", 18, fetuscourses);
	
	@Test
	void testgetEnrolledID() {
		calculus.enroll(nat);
		LinkedList<String> id = calculus.getEnrolledId();
		
		assertTrue((id.getFirst()).equals(nat.getId()));
	}
	
	@Test
	void testEnrollStudents() {
		peopletoenroll.add(nat);
		peopletoenroll.add(jon);
		calculus.enroll(nat);
		calculus.enroll(jon);
		
		assertEquals(enrollmentList, peopletoenroll);
	}
	
	@Test
	void testFindStudentbyID() {
		peopletoenroll.add(nat);
		calculus.enroll(nat);
		
		assertEquals(calculus.findStudentById("123000"), nat);
	}
	
	@Test
	void testRemoveStudnets() {
		peopletoenroll.add(nat);
		peopletoenroll.add(jon);
		calculus.enroll(nat);
		calculus.enroll(jon);
		calculus.remove(nat);
		peopletoenroll.remove();
		
		assertEquals(enrollmentList, peopletoenroll);
	}
	
	@Test
	void testEnrollwithResitriction() {
		Course zymurgy = new Course("Science", "zymurgy", 3, enrollmentList);
		peopletoenroll.add(jon);
		zymurgy.enrollWithRestriction(jon);
		zymurgy.enrollWithRestriction(fetus);
		assertEquals(enrollmentList, peopletoenroll);
	}

}
