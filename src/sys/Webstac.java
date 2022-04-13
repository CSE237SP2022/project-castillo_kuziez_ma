package sys;

import java.util.*;
import java.io.*;

public class Webstac {
	
	
    public LinkedList<Course> courses;
    public LinkedList<Student> students;
    
    public Webstac() {
    	courses = new LinkedList<Course>();
    	students = new LinkedList<Student>();
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
    
    
    public static void main(String[] args) throws Exception {
    	// create system object
    	Webstac sys = new Webstac();
    	
    	// have to run it from src folder ...
    	String sPath = "./system/students.txt";
    	String cPath = "./system/courses.txt";
    	
    	// get the existing students from the file in directory
    	File students = new File(sPath);
    	Scanner sStudents = new Scanner(students);
    	
    	// get the existing courses from the file in directory
    	File courses = new File(cPath);
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
    	
    	// parse students into students list of system
    	for (int i = 0; i < copyStudents.size(); ++i) {
    		if (copyStudents.get(i).equals("student")) {
    			String id = copyStudents.get(++i);
    			String first = copyStudents.get(++i);
    			String last = copyStudents.get(++i);
    			int age = Integer.parseInt(copyStudents.get(++i));
    			sys.addStudent(new Student(id, first, last, age));
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
    				Student s = sys.getStudentById(copyCourses.get(i));
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
    			sys.addCourse(new Course(code, name, credits, enrolled));
    		}
    	}
    	
    	
    	sys.printStudents();
    	sys.printCourses();
    	
    	
    	//initialize student and course arrays:
    	Student[] allstuds=new Student [100];
    	int studspot=0;
    	int coursespot=0;
    	Course[] allcourses= new Course [100];
    	
    	//Begin interface.
    	String command="start";
    	while(!command.equals("shutdown")){
    		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    		System.out.println("Enter A command. [Add student] [Add course] [shutdown]");

    		command = myObj.nextLine();  // Read user input
    		
    		if(command.equals("Add student")) {
    			
    			System.out.println("Please enter First name");
        		String Firstname = myObj.nextLine();  // Read user input
    			System.out.println("Please enter Last name");
        		String Lastname = myObj.nextLine();  // Read user input
    			System.out.println("Please enter ID");
        		String ID = myObj.nextLine();  // Read user input
    			System.out.println("Please enter age ");
        		int age = myObj.nextInt();  // Read user input
        		allstuds[studspot]=new Student(ID,Firstname,Lastname,age);
        		studspot++;
    		}
    		
    		
    		if(command.equals("Add course")) {
    			
    			System.out.println("Please enter code");
        		String code = myObj.nextLine();  // Read user input
    			System.out.println("Please enter name");
        		String name = myObj.nextLine();  // Read user input
    			System.out.println("Please enter credits ");
        		int age = myObj.nextInt();  // Read user input
        		LinkedList<Student> stud= new LinkedList<Student>();
        		allcourses[coursespot]=new Course(code,name,age,stud);
    			
    		}
    			
      }
    }
    	
    	
    	
    	
    	
    }
    
