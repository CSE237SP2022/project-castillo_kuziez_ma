
import java.util.*;


public class Course {
	
	String code;
	String name;
    int credits;
    LinkedList<Student> enrolled;
    
    
    public Course() {
    	code = "WIP";
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
     * THEY HAVE TO IMPLEMENT THE REST, HAVE TO WRITE EVERYTHING WITH FIXME
     */
    
    /*
     * findStudentById: FIXME finds a student in enrolled by a given id, returns null if not found
     */
    public Student findStudentById(String id) {
    	for (Student s : enrolled) {
    		if (s.getId().equals(id)) return s;
    	}
    	
    	return null;
    }
    
    /*
     * enrollStudents: FIXME enrolls list of students, must also add this course to their listing
     */
    public void enrollStudents(LinkedList<Student> students) {
    	LinkedList<String> ids = this.getEnrolledId();
    	
    	for (Student s : students) {
    		String potentialId = s.getId();
    		if (potentialId.equals("000000")) continue; // John Doe student
    		
    		if (!ids.contains(potentialId)) {
    			enrolled.add(s);
    			// not sure actually how global this is, might have to change
    			s.addCourse(this);
    		}
    	}
    }
    
    /*
     * removeStudents: FIXME removes list of students, must check that student's ids and last names match
     * 					     before removing them, do not need to remove this course from their listing
     */
    public void removeStudents(LinkedList<Student> students) {
    	LinkedList<String> ids = this.getEnrolledId();
    	
    	for (Student s : students) {
    		String potentialId = s.getId();
    		if (ids.contains(potentialId)) {
    			Student potentialStudent = this.findStudentById(potentialId);
    			if (potentialStudent == null) continue;
    			
    			if (s.getLastName().equals(potentialStudent.getLastName())) {
    				enrolled.remove(potentialStudent);
    			}
    		}
    	}
    }
    
    /*
     * enrollWithRestriction: FIXME some classes like zymurgy (fermentation science) require 21+ legal
     * 								drinking age so only allow students that are 21+ (idk)
     */
    public void enrollWithRestriction(LinkedList<Student> students) {
    	LinkedList<Student> allowedStudents = new LinkedList<Student>();
    	
    	for (Student s : students) {
    		if (s.getAge() > 20) {
    			allowedStudents.add(s);
    		}
    	}
    	
    	this.enrollStudents(allowedStudents);
    }
    
    
    
    
    
    
    
    
}
