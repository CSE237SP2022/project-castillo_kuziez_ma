package sys;

import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class sys1 {
	public static void main(String args[]) throws FileNotFoundException {
		 
        //creating File instance to reference text file in Java
        File text = new File("C:/temp/test.txt");
     
        //Creating Scanner instance to read File in Java
        Scanner scnr = new Scanner(text);
     
        //Reading each line of the file using Scanner class
        int lineNumber = 1;
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
//            sys1.out.println();
            lineNumber++;
        }      
   
    }  

	
    public LinkedList<Course> courses;
    public LinkedList<Student> students;
    
    
    
    
    
    
}
