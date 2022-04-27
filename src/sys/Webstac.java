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
    
    
    //Reads in the files and instantiates the student and courses linked lists
    public static List<Object> initsystem() throws Exception{
    	// create system object
    	Webstac webstac = new Webstac();
    	
    	// have to run it from src folder, relative paths
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
    	List<Object> webstacitems=new LinkedList<Object>(); 
    	webstacitems.add(webstac);
    	webstacitems.add(copyCourses);
    	webstacitems.add(copyStudents);
    	
    	return webstacitems;
    }
    
    
    //simply prints out help message
    public static void help_msg() {
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
		return;
    }
    
    //adds a student to either the system or a specific course
    public static void add_student(Scanner sWebstac,Webstac webstac, List<String> copyCourses, List<String> copyStudents) {
		//tree of options offered via print statements and scanning for a reply
    	
    	System.out.println();
		System.out.println("[course] or [system]:");
		String next = sWebstac.nextLine();
		
		
		// add to course-OPTION 1
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
		
		
		
		// add to system-OPTION 2
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
    		//errors for ID's
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
    		//error on age
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
    
    
    
    //Adds a course
    public static void add_course(Scanner sWebstac,Webstac webstac, List<String> copyCourses, List<String> copyStudents) {

    	// prompt for code and check if already used
		System.out.println();
		System.out.println("course code:");
		String code = sWebstac.nextLine();
		System.out.println();
		while (webstac.codes.contains(code)) {
			System.out.println("code already exists:");
			code = sWebstac.nextLine();
			System.out.println();
		}
		
		//collect other information for course
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
		
		
		//initialize the course
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
    
    // removes the student from either a course or the system
    public static void remove_student(Scanner sWebstac,Webstac webstac, List<String> copyCourses, List<String> copyStudents) {

    	
    	
		System.out.println();
		System.out.println("[course] or [system]:");
		String next = sWebstac.nextLine();
		// remove from course-OPTION 1
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
		// remove from system-OPTION 2
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
    
    
    //removes a course from the system found via course code
    public static void remove_course(Scanner sWebstac,Webstac webstac, List<String> copyCourses, List<String> copyStudents) {

// get code    	
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
		//remove course and update system
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
    
    //shuts down the program
    public static void quit(Scanner sWebstac,Webstac webstac, List<String> copyCourses,
    		List<String> copyStudents, String studentsPath, String coursesPath, File students, File courses)
    throws Exception{

    	//determine if we need to save changes
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
    
    
    //This method prints out the raw data stored in the system
    //useful for debugging by seeing the raw data
    public static void raw(List<String> copyCourses,
    		List<String> copyStudents) {

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
    
    
    
    
    
   //_____________________________________________________________________________________________
    
    
    
    
    
    //begin main method. this reads user inputs and responds to them
    
    public static void main(String[] args) throws Exception {
    	//opens file to allow for writing at the end of the system.
    	String studentsPath = "./sys/students.txt";
    	String coursesPath = "./sys/courses.txt";
    	File students = new File(studentsPath);
    	File courses = new File(coursesPath);   	
    	
    	//See above method, this reads in the files and passes the constructed student
    	//and course objects to the main.
    	List<Object> carrier=initsystem();
    	//type casting from object because the items are stored in an object list
    	//to allow passing multiple return values from a function
    	Webstac webstac=(Webstac) carrier.get(0);
    	List<String> copyCourses = (List<String>) carrier.get(1);
    	List<String> copyStudents = (List<String>) carrier.get(2);
    	
    	// print initial data
    	System.out.println();
    	webstac.printStudents();
    	webstac.printCourses();
    
    	
    	// begin interface
    	//note that this relies on the methods above to operate. The while loop simply
    	//checks for which command is given and calls above functions
    	String cmd = "start";
    	while (!cmd.equals("quit")) {
    		// create Scanner object
    		Scanner sWebstac = new Scanner(System.in);
    		
    		System.out.println("Enter a command: [help] [quit]");

    		// read user input
    		cmd = sWebstac.nextLine();
    		
    		// [help]
    		if (cmd.equals("help")) {
    			help_msg();
    		}
    		// [add student] see function above for operation
    		else if (cmd.equals("add student")) {
    			add_student(sWebstac,webstac, copyCourses, copyStudents);
    		}
    		// [add course] see function above for operation
    		else if (cmd.equals("add course")) {
    			add_course(sWebstac,webstac, copyCourses, copyStudents);
    		}
    		// [rm student]
    		else if (cmd.equals("rm student")) {
    			remove_student(sWebstac,webstac, copyCourses, copyStudents);
    		}
    		// [rm course]
    		else if (cmd.equals("rm course")) {
    			remove_course(sWebstac,webstac, copyCourses, copyStudents);
    		}
    		// [print]
    		else if (cmd.equals("print")) {
    			System.out.println();
    			webstac.printAll();
    		}
    		// [raw] for test purposes
    		else if (cmd.equals("raw")) {
    			raw(copyCourses, copyStudents);
    		}
    		else if (cmd.equals("quit")) {
    			quit(sWebstac,webstac, copyCourses, copyStudents, studentsPath, coursesPath, students, courses);
    		}
    		else {
    			System.out.println();
    		}
    	}
    }	
}
