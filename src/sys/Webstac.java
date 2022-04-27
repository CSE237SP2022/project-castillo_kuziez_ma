package sys;

import java.util.*;
import java.io.*;


//This contains the methods, and the main to actually run the program.
public class Webstac {
	
	//variables to hold values throughout running
    public LinkedList<Course> courses;
    public LinkedList<Student> students;
    
    // could be made better by overriding == operator for the course and student objects
    public List<String> ids;
    public List<String> codes;
    
    public Webstac() {
    	courses = new LinkedList<Course>();
    	students = new LinkedList<Student>();
    	ids = new ArrayList<String>();
    	codes = new ArrayList<String>();
    }
    
    public void addStudent(Student s) {
    	students.add(s);
    }
    
    public void addCourse(Course c) {
    	courses.add(c);
    }
    
    public Student getStudentById(String id) {
    	for (Student s : students) {
    		if (s.getId().equals(id)) {
    			return s;
    		}
    	}
    	// return John Doe
    	return new Student();
    }
    
    public Course getCourseByCode(String code) {
    	for (Course c : courses) {
    		if (c.code.equals(code)) {
    			return c;
    		}
    	}
    	// return 
    	return new Course();
    }
    
    public void updateIds() {
    	for (Student s : students) {
    		if (!ids.contains(s.getId())) {
    			ids.add(s.getId());
    		}
    	}
    }
    
    public void updateCodes() {
    	for (Course c : courses) {
    		if (!codes.contains(c.code)) {
    			codes.add(c.code);
    		}
    	}
    }
    
    public void printStudents() {
    	System.out.println("Students:");
    	System.out.println();
    	for (Student s : students) {
    		System.out.println(s.getId() + " " + s.getFirstName() + " " + s.getLastName());
    	}
    	System.out.println();
    }
    
    
    public void printCourses() {
    	System.out.println("Courses:");
    	System.out.println();
    	for (Course c : courses) {
    		System.out.println(c.code + " " + c.name);
    		System.out.println("Credits: " + c.credits);
    		System.out.println("Enrolled:");
    		for (Student s : c.enrolled) {
    			System.out.println(s.getId() + " " + s.getFirstName() + " " + s.getLastName());
    		}
    		System.out.println();
    	}
    }
    
    public void printAll() {
    	this.printStudents();
    	this.printCourses();
    }
    
    // check if string is positive int -- helper method
    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        int i = 0;
        try {
            i = Integer.parseInt(s);
            if (i < 0) {
            	return false;
            }
            return true;
        } 
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    
    //begin the actual system reading
    
    public static void main(String[] args) throws Exception {
    	// create system object
    	Webstac webstac = new Webstac();
    	
    	// have to run it from src folder ...
    	String studentsPath = "./sys/students.txt";
    	String coursesPath = "./sys/courses.txt";
    	
    	// get the existing students from the file in directory
    	File students = new File(studentsPath);
    	Scanner sStudents = new Scanner(students);
    	
    	// get the existing courses from the file in directory
    	File courses = new File(coursesPath);
    	Scanner sCourses = new Scanner(courses);
    	
    	// make String list copy of the students to write to it
    	List<String> copyStudents = new ArrayList<String>();
    	while (sStudents.hasNext()) {
    		copyStudents.add(sStudents.nextLine());
    	}
    	
    	// make String list copy of the file to write to it
    	List<String> copyCourses = new ArrayList<String>();
    	while (sCourses.hasNext()) {
    		copyCourses.add(sCourses.nextLine());
    	}
    	
    	// close scanners
    	sStudents.close();
    	sCourses.close();
    	
    	// parse students into students list of system
    	for (int i = 0; i < copyStudents.size(); ++i) {
    		if (copyStudents.get(i).equals("student")) {
    			String id = copyStudents.get(++i);
    			String first = copyStudents.get(++i);
    			String last = copyStudents.get(++i);
    			int age = Integer.parseInt(copyStudents.get(++i));
    			webstac.addStudent(new Student(id, first, last, age));
    		}
    	}
    	
    	// parse courses into courses list of system
    	for (int i = 0; i < copyCourses.size(); ++i) {
    		if (copyCourses.get(i).equals("course")) {
    			String code = copyCourses.get(++i);
    			String name = copyCourses.get(++i);
    			int credits = Integer.parseInt(copyCourses.get(++i));
    			LinkedList<Student> enrolled = new LinkedList<Student>();
    			++i;
    			while(!copyCourses.get(i).equals("course") && !copyCourses.get(i).equals("end")) {
    				Student s = webstac.getStudentById(copyCourses.get(i));
    				// if John Doe
    				if (s.getId().equals("000000")) {
    					// do not add student
    					continue;
    				}
    				else {
    					enrolled.add(s);
    				}
    				++i;
    			}
    			--i;
    			webstac.addCourse(new Course(code, name, credits, enrolled));
    		}
    	}
    	
    	// update ids and codes
    	webstac.updateIds();
    	webstac.updateCodes();
    	
    	// print initial data
    	System.out.println();
    	webstac.printStudents();
    	webstac.printCourses();
    
    	
    	// begin interface
    	// this section makes main function very long but there is no way to shorten this
    	// due to the fact that the wrapper exists in main and this is interacting with
    	// user inputs -- some code is redundant but have specialized if/else so cannot be
    	// shortened into functions either
    	String cmd = "start";
    	while (!cmd.equals("quit")) {
    		// create Scanner object
    		Scanner sWebstac = new Scanner(System.in);
    		
    		System.out.println("Enter a command: [help] [quit]");

    		// read user input
    		cmd = sWebstac.nextLine();
    		
    		// [help]
    		if (cmd.equals("help")) {
    			System.out.println();
    			System.out.println("[add student] - input student info to add student to course or system");
    			System.out.println();
    			System.out.println("[add course]  - input course info to add course to system");
    			System.out.println();
    			System.out.println("[rm student]  - input student id to remove student from course or system");
    			System.out.println();
    			System.out.println("[rm course]   - input course code to remove course from system");
    			System.out.println();
    			System.out.println("[print]       - prints existing roster and course info");
    			System.out.println();
    			System.out.println("[quit]        - exits and saves changes made");
    			System.out.println();
    		}
    		// [add student]
    		else if (cmd.equals("add student")) {
    			System.out.println();
    			System.out.println("[course] or [system]:");
    			String next = sWebstac.nextLine();
    			// add to course
    			if (next.equals("course")) {
    				System.out.println();
        			System.out.println("course code:");
        			String code = sWebstac.nextLine();
        			Course c = webstac.getCourseByCode(code);
    				// if wip course
    				if (c.code.equals("wip100")) {
    					// course does not exist
    					System.out.println();
    					System.out.println("Course does not exist");
    					System.out.println();
    				}
    				// ask for student to add to course
    				else {
    					System.out.println();
            			System.out.println("student id:");
            			String id = sWebstac.nextLine();
            			Student s = webstac.getStudentById(id);
            			// if John Doe
        				if (s.getId().equals("000000")) {
        					// do not add student
        					System.out.println();
        					System.out.println("Student does not exist");
        					System.out.println();
        				}
        				// student found to add
        				else {
        					if (c.enroll(s)) {
        						System.out.println();
            					System.out.println(s.getFirstName() + " " + s.getLastName() + " has been added to " + c.code + " " + c.name);
            					System.out.println();
            					
            					// update txt file
                				int index = copyCourses.indexOf(c.code);
                				copyCourses.add(index+3, s.getId());
        					}
        					else {
        						System.out.println();
            					System.out.println(s.getFirstName() + " " + s.getLastName() + " is already in " + c.code + " " + c.name);
            					System.out.println();
        					}
        				}
    				}
    			}
    			// add to system
    			else if (next.equals("system")) {
    				System.out.println();
        			System.out.println("first name:");
            		String first = sWebstac.nextLine();
            		System.out.println();
            		
        			System.out.println("last name:");
            		String last = sWebstac.nextLine();
            		System.out.println();
            		
        			System.out.println("ID:");
            		String id = sWebstac.nextLine();
            		System.out.println();
            		while (true) {
            			if (id.length() != 6) {
                			System.out.println("ID must be 6 characters or numbers:");
                			id = sWebstac.nextLine();
                			System.out.println();
                		}
            			else if (webstac.ids.contains(id)) {
            				System.out.println("ID already exists:");
            				id = sWebstac.nextLine();
            				System.out.println();
            			}
            			else if (id.equals("000000")) {
            				System.out.println("ID cannot be 000000:");
            				id = sWebstac.nextLine();
            				System.out.println();
            			}
            			else {
            				break;
            			}
            		}
            		
        			System.out.println("age:");
        			String tempAge = sWebstac.nextLine();
        			System.out.println();
        			int age = 0;
        			while (!isNumeric(tempAge)) {
        				System.out.println("age must be a positive number:");
        				tempAge = sWebstac.nextLine();
        				System.out.println();
        			}
        			age = Integer.parseInt(tempAge);
            		
        			webstac.addStudent(new Student(id, first, last, age));
        			System.out.println("student successfully added");
        			System.out.println();
        			
        			// update ids
        			webstac.updateIds();
        			
        			// update txt file
        			copyStudents.add(0, "student");
        			copyStudents.add(1, id);
        			copyStudents.add(2, first);
        			copyStudents.add(3, last);
        			copyStudents.add(4, Integer.toString(age));
    			}
    			else {
    				System.out.println();
    				System.out.println("nothing added");
    				System.out.println();
    			}
    		}
    		// [add course]
    		else if (cmd.equals("add course")) {
    			System.out.println();
    			System.out.println("course code:");
        		String code = sWebstac.nextLine();
        		System.out.println();
        		while (webstac.codes.contains(code)) {
        			System.out.println("code already exists:");
    				code = sWebstac.nextLine();
    				System.out.println();
        		}
        		
    			System.out.println("course name:");
        		String name = sWebstac.nextLine();
        		System.out.println();
        		
    			System.out.println("number of credits:");
    			String tempCredits = sWebstac.nextLine();
    			System.out.println();
    			int credits = 0;
    			while (!isNumeric(tempCredits)) {
    				System.out.println("credits must be a positive number:");
    				tempCredits = sWebstac.nextLine();
    				System.out.println();
    			}
    			credits = Integer.parseInt(tempCredits);
        		
    			LinkedList<Student> enrolled = new LinkedList<Student>();
    			webstac.addCourse(new Course(code, name, credits, enrolled));
    			
    			System.out.println("course successfully added");
    			System.out.println();
    			
    			// update codes
    			webstac.updateCodes();
    			
    			// update txt file
    			copyCourses.add(0, "course");
    	    	copyCourses.add(1, code);
    	    	copyCourses.add(2, name);
    	    	copyCourses.add(3, Integer.toString(credits));
    		}
    		// [rm student]
    		else if (cmd.equals("rm student")) {
    			System.out.println();
    			System.out.println("[course] or [system]:");
    			String next = sWebstac.nextLine();
    			// remove from course
    			if (next.equals("course")) {
    				System.out.println();
        			System.out.println("course code:");
        			String code = sWebstac.nextLine();
        			Course c = webstac.getCourseByCode(code);
    				// if wip course
    				if (c.code.equals("wip100")) {
    					// course does not exist
    					System.out.println();
    					System.out.println("Course does not exist");
    					System.out.println();
    				}
    				// ask for student to remove from course
    				else {
    					System.out.println();
            			System.out.println("student id:");
            			String id = sWebstac.nextLine();
            			Student s = webstac.getStudentById(id);
            			// if John Doe
        				if (s.getId().equals("000000")) {
        					// do not remove student
        					System.out.println();
        					System.out.println("Student does not exist");
        					System.out.println();
        				}
        				// found student to remove
        				else {
        					if (c.remove(s)) {
        						System.out.println();
            					System.out.println(s.getFirstName() + " " + s.getLastName() + " has been removed from " + c.code + " " + c.name);
            					System.out.println();
            					
            					// update txt file (useless while condition but safe in case of txt file corruption)
            					int i = copyCourses.indexOf(c.code);
            					while (!copyCourses.get(++i).equals("course")) {
            						if (copyCourses.get(i).equals(s.getId())) {
            							copyCourses.remove(i);
            							break;
            						}
            					}
        					}
        					else {
        						System.out.println();
            					System.out.println(s.getFirstName() + " " + s.getLastName() + " is not in " + c.code + " " + c.name);
            					System.out.println();
        					}
        				}
    				}
    			}
    			// remove from system
    			else if (next.equals("system")) {
    				System.out.println();
        			System.out.println("ID of student:");
        			String id = sWebstac.nextLine();
            		System.out.println();
            		Student s = webstac.getStudentById(id);
    				// if John Doe
    				if (s.getId().equals("000000")) {
    					// do not remove student
    					System.out.println("Student does not exist");
    					System.out.println();
    				}
    				else {
    					webstac.students.remove(s);
    					for (Course c : webstac.courses) {
    						c.remove(s);
    					}
    					System.out.println(s.getFirstName() + " " + s.getLastName() + " has been removed");
    					System.out.println();
    					
    					// update ids
    					webstac.updateIds();
    					
    					// update txt file
    					int index = copyStudents.indexOf(s.getId());
    					for (int i = 0; i < 5; ++i) {
    						copyStudents.remove(index-1);
    					}
    					copyCourses.removeIf(n -> n.equals(s.getId()));
    				}
    			}
    			else {
    				System.out.println();
        			System.out.println("nothing removed");
        			System.out.println();
    			}
    		}
    		// [rm course]
    		else if (cmd.equals("rm course")) {
    			System.out.println();
    			System.out.println("code of course:");
    			String code = sWebstac.nextLine();
        		System.out.println();
        		Course c = webstac.getCourseByCode(code);
				// if wip course
				if (c.code.equals("wip100")) {
					// do not remove course
					System.out.println("Course does not exist");
					System.out.println();
				}
				else {
					webstac.courses.remove(c);
					System.out.println(c.code + " " + c.name + " has been removed");
					System.out.println();
					
					// update codes
					webstac.updateCodes();
					
					// update txt file
					int index = copyCourses.indexOf(c.code);
					// supposed class limit of 100 students -- not implemented
					for (int i = 0; i < 100; ++i) {
						if (copyCourses.get(index).equals("course") || copyCourses.get(index).equals("end")) {
							copyCourses.remove(index-1);
							break;
						}
						copyCourses.remove(index-1);
					}
				}
    		}
    		// [print]
    		else if (cmd.equals("print")) {
    			System.out.println();
    			webstac.printAll();
    		}
    		// [raw] for test purposes
    		else if (cmd.equals("raw")) {
    			System.out.println();
    			for (String s : copyStudents) {
    	    		System.out.println(s);
    	    	}
    			System.out.println();
    			for (String s : copyCourses) {
    	    		System.out.println(s);
    	    	}
    			System.out.println();
    		}
    		else if (cmd.equals("quit")) {
    			System.out.println();
    			System.out.println("save changes [y] or [n]:");
    			String next = sWebstac.nextLine();
    			while (!next.equals("y") && !next.equals("n")) {
    				System.out.println();
        			System.out.println("save changes [y] or [n]:");
        			next = sWebstac.nextLine();
    			}
    			if (next.equals("y")) {
    				// write to txt files
    				// delete current files
    				students.delete();
    				courses.delete();
    				// create new files
    				File newStudents = new File(studentsPath);
    				File newCourses = new File(coursesPath);
    				// check if new student file created
    				if (newStudents.createNewFile()) {
    					// create file writer
    					FileWriter wStudents = new FileWriter(studentsPath);
    					for (String s : copyStudents) {
    						wStudents.write(s + "\n");
    					}
    					wStudents.close();
    				}
    				// check if new courses file created
    				if (newCourses.createNewFile()) {
    					// create file writer
    					FileWriter wCourses = new FileWriter(coursesPath);
    					for (String s : copyCourses) {
    						wCourses.write(s + "\n");
    					}
    					wCourses.close();
    				}
    				System.out.println();
        			System.out.println("changes saved");
        			System.out.println();
        			
        			// close scanner
        			sWebstac.close();
    			}
    			else if (next.equals("n")) {
    				System.out.println();
        			System.out.println("changes not saved");
        			System.out.println();
        			
        			// close scanner
        			sWebstac.close();
    			}
    		}
    		else {
    			System.out.println();
    		}
    	}
    }	
}
