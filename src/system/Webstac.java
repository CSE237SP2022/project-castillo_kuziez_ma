package system;

import java.util.*;
import java.io.*;

public class Webstac {
	
	
    public LinkedList<Course> courses;
    public LinkedList<Student> students;
    
    public Webstac() {
    	courses = new LinkedList<Course>();
    	students = new LinkedList<Student>();
    }
    
    
    public static void main(String[] args) throws Exception {
    	Webstac sys = new Webstac();
    	
    	// get the existing data (database) from the file in place
    	File file = new File("./FinalProject/src/system/data.txt");
    	Scanner s = new Scanner(file);
    	
    	while(s.hasNext()) {
    		System.out.println(s.nextLine());
    	}
    	
    }
    
}
