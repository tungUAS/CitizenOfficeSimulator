package File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Ulti.DateUlti;

/**
 * @author Trinh Thanh Tung 1320718
 * Responsible for printing salary information
 */
public class CreateFile {
	/**
	 * @param email is worker email
	 * @param workinghours is worker working hours in this month
	 * @param workingTime is the art of working time. fulltime or part time
	 * @param salary will be calculated where fulltime or parttime
	 * we create a text file for worker for him with salary information
	 */
	public static void CreateNewFile(String email,int workinghours,String workingTime,long salary) {
	    try {
	    	
	      File myObj = new File("salary.txt");
	     
	     
	        System.out.println("File created: " + myObj.getName());
	        FileWriter myWriter = new FileWriter("salary.txt");
	        myWriter.write("File generated : "+ DateUlti.today()+"\n");
	        myWriter.write("Worker : "+ email+"\n");
	        myWriter.write("Salary for month : " + DateUlti.thismonth()+"\n");
	        myWriter.write("Working Hours : "+ workinghours+"\n");
	        myWriter.write("Your are : " + workingTime+"\n");
	        myWriter.write("Your salaray for this month : " + salary);
	        myWriter.close();

	    
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }
	}
