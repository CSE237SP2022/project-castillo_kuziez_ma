package sys;

import java.util.*;

public class Course {
	
	public String code;
	public String name;
    public int credits;
    public LinkedList<Student> enrolled;
    

    public Course() {
    	code = "wip100";
    	name = "Placeholder";
    	credits = 3;
    	enrolled = new LinkedList<Student>();
    }
    
    public Course(String code, String name, int credits, LinkedList<Student> enrolled) {
    	this.code = code;
    	this.name = name;
    	this.credits = credits;
    	this.enrolled = enrolled;
    }
    
    /*
     * getEnrolledId: gets list of id's of students enrolled
     */
    public LinkedList<String> getEnrolledId() {
    	LinkedList<String> ids = new LinkedList<String>();
    	
    	for (Student s : enrolled) ids.add(s.getId());
    	
    	return ids;
    }
    
    
    /*
     * findStudentById: finds a student in enrolled by a given id, returns null if not found
     */
    public Student findStudentById(String id) {
    	for (Student s : enrolled) {
    		if (s.getId().equals(id)) return s;
    	}
    	
    	return null;
    }
    
    /*
     * enroll: enrolls student
     */
    public boolean enroll(Student s) {
    	LinkedList<String> ids = this.getEnrolledId();
    	
    	if (ids.contains(s.getId())) {
    		return false;
    	}
    	else {
    		enrolled.add(s);
    		return true;
    	}
    }
    
    /*
     * remove: removes student
     */
    public boolean remove(Student s) {
    	LinkedList<String> ids = this.getEnrolledId();
    	
    	if (ids.contains(s.getId())) {
    		enrolled.remove(s);
    		return true;
    	}
    	return false;
    }
    
    /*
     * enrollWithRestriction: some classes like zymurgy (fermentation science) require 21+ legal
     * 					      drinking age so only allow students that are 21+ (idk)
     */
    public void enrollWithRestriction(Student s) {
    	if (s.getAge() > 20) {
    		enrolled.add(s);
    	}
    }
    
    
    
    
    
    
    
    
}