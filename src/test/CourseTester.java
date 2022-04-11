package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Course.Course;
import Student.Student;

class CourseTester {
	
	@Test
	void testgetEnrolledID() {
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
		LinkedList<Course> natcourses = new LinkedList<Course>();
		Student nat = new Student("123", "nat", "cas", 22, natcourses);
		LinkedList<Student> peopletoenroll = new LinkedList<Student>();
		peopletoenroll.add(nat);
		calculus.enrollStudents(peopletoenroll);
		LinkedList<String> id = calculus.getEnrolledId();
		
		assertTrue((id.getFirst()).equals(nat.getId()));
	}
	
	@Test
	void testEnrollStudents() {
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
		LinkedList<Course> natcourses = new LinkedList<Course>();
		LinkedList<Course> joncourses = new LinkedList<Course>();
		Student nat = new Student("123", "nat", "cas", 22, natcourses);
		Student jon = new Student("456", "jon", "ma", 21, joncourses);
		LinkedList<Student> peopletoenroll = new LinkedList<Student>();
		peopletoenroll.add(nat);
		peopletoenroll.add(jon);
		calculus.enrollStudents(peopletoenroll);
		
		assertEquals(enrollmentList, peopletoenroll);
	}
	
	@Test
	void testFindStudentbyID() {
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
		LinkedList<Course> natcourses = new LinkedList<Course>();
		Student nat = new Student("123", "nat", "cas", 22, natcourses);
		LinkedList<Student> peopletoenroll = new LinkedList<Student>();
		peopletoenroll.add(nat);
		calculus.enrollStudents(peopletoenroll);
		
		assertEquals(calculus.findStudentById("123"), nat);
	}
	
	@Test
	void testRemoveStudnets() {
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course calculus = new Course("Math", "Calculus", 3, enrollmentList);
		LinkedList<Course> natcourses = new LinkedList<Course>();
		LinkedList<Course> joncourses = new LinkedList<Course>();
		Student nat = new Student("123", "nat", "cas", 22, natcourses);
		Student jon = new Student("456", "jon", "ma", 21, joncourses);
		LinkedList<Student> peopletoenroll = new LinkedList<Student>();
		peopletoenroll.add(nat);
		peopletoenroll.add(jon);
		calculus.enrollStudents(peopletoenroll);
		LinkedList<Student> removeList = new LinkedList<Student>();
		removeList.add(nat);
		calculus.removeStudents(removeList);
		peopletoenroll.remove();
		
		assertEquals(enrollmentList, peopletoenroll);
	}
	
	@Test
	void testEnrollwithResitriction() {
		LinkedList<Student> enrollmentList = new LinkedList<Student>();
		Course zymurgy = new Course("Science", "zymurgy", 3, enrollmentList);
		LinkedList<Course> fetuscourses = new LinkedList<Course>();
		LinkedList<Course> joncourses = new LinkedList<Course>();
		Student fetus = new Student("789", "fetus", "little", 18, fetuscourses);
		Student jon = new Student("456", "jon", "ma", 21, joncourses);
		LinkedList<Student> peopletoenroll = new LinkedList<Student>();
		peopletoenroll.add(fetus);
		peopletoenroll.add(jon);
		zymurgy.enrollWithRestriction(peopletoenroll);
		peopletoenroll.remove();
		
		assertEquals(enrollmentList, peopletoenroll);
	}

}
