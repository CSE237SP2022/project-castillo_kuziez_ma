import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import system.Student;
import system.Course;


public class ReadFromFile {
	
	//Reads in a student from the file 
	//to be able to call this method multiple times we need to store the location of the last scan
	public static Student[] read_student() throws FileNotFoundException  {
		File text = new File("Student_list.txt");
        Scanner scnr2 = new Scanner(text);
		
        Student[] allstudents= new Student [1000];
        int i=0;
        
        while(scnr2.hasNextLine()) {
		Student placeholder= new Student();
		placeholder.firstName=scnr2.nextLine();
		placeholder.lastName=scnr2.nextLine();
		placeholder.id=scnr2.nextLine();
		placeholder.age=Integer.parseInt(scnr2.nextLine());
		allstudents[i]=placeholder;
		i++;
        }
		return allstudents;
	}
	
	public static Course[] read_course() throws FileNotFoundException  {
		File text = new File("Course_list.txt");
        Scanner scnr = new Scanner(text);
        Course[] allcourses=new Course[100];
        
        while(scnr.hasNextLine()) {
        	
        			Course placeholder= new Course();
        			placeholder.code=scnr.nextLine();
        			placeholder.name=scnr.nextLine();
        			placeholder.credits=Integer.parseInt(scnr.nextLine());
        
        
        }
		return allcourses;
	}
	
	
	
	public static void main(String args[]) throws FileNotFoundException {
		Student[] allstuds=read_student();
		System.out.println(allstuds[0].firstName);
		System.out.println(allstuds[1].firstName);
		System.out.println(allstuds[2].firstName);
		
    }  
}
