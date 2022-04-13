import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class
import system.Student;
import system.Course;


//This class handles the writing of the file at the end of the session

public class WriteToFile {
	
	//initializes a course file that is BLANK
	public static void make_course_file() {
		
		  try {
		      File myObj = new File("Course_list.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		
	}
	//Erases all the data in the course file, needed if we courses drop between sessions
	public static void wipe_course_file() {
	    File myObj = new File("Course_list.txt"); 
	    if (myObj.delete()) { 
	      System.out.println("Deleted the file: " + myObj.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    }
	    make_course_file();
	}
	
	

	
	//Writes in a course to the course file
	public static void write_course_in(Course course) {
		try {
			  String space=System.lineSeparator();
		      FileWriter myWriter = new FileWriter("Course_list.txt");
		      myWriter.write(course.code+space);
		      myWriter.write(course.name+space);
		      myWriter.write(course.credits+space);
		      
		      //Write the student ID's separated by hyphens on one line
		      int class_size=course.enrolled.size();
		      for( int i=0; i<class_size; i++) {
		    	  myWriter.write(course.enrolled.get(i).id+"-");
		      }
		      myWriter.write(space);

		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
	}
	
	public static void make_student_file() {
		
		  try {
		      File myObj = new File("Student_list.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		
	}
	//Erases all the data in the course file, needed if we courses drop between sessions
	public static void wipe_student_file() {
	    File myObj = new File("Student_list.txt"); 
	    if (myObj.delete()) { 
	      System.out.println("Deleted the file: " + myObj.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    }
	    make_student_file();
	}
	
	
	//note the structure of try_catch is to handle io errors if they occur
	public static void write_student(Student person) {
		try {
		      	    	
		      FileWriter myWriter = new FileWriter("Student_list.txt");
		      
		      String space=System.lineSeparator();
		      myWriter.write(person.firstName+space);

		      myWriter.write(person.lastName+space);
		      myWriter.write(person.id+space);
		      myWriter.write(Integer.toString(person.age)+space);
		     
//	Courses are not written in because they can be recovered by 1 way linkages (i.e. restored from the course side)
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
  public static void main(String[] args) {
	  wipe_student_file();
	  wipe_course_file();
	  Student james=new Student();
	  write_student(james);

  
  }
  

}