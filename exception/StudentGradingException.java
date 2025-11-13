//====================================================================
//Anh Truong
//CIS 35A -- 00450
//Lab 6 - Exception Handling, Serialization and API
//Due: Mar 17, 2024
//====================================================================

package exception;

import java.io.*;

public class StudentGradingException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorno;
	private String errormsg;
	private String path = "/Users/anhtruong/VSC/Java/Exports/CIS 35a - Lab<6>/";
	
	//Default Constructor
	public StudentGradingException() {
		super();
		print();
	}
		
	//Overloaded Constructor (1)
	public StudentGradingException(String msg) {
		super();
		this.errormsg = msg;
		print();
	}
		
	//Overloaded Constructor (2)
	public StudentGradingException(int no) {
		super();
		this.errorno = no;
		print();
	}
		
	//Overloaded Constructor (3)
	public StudentGradingException(int no, String msg) {
		super();
		this.errorno = no;
		this.errormsg = msg;
		print();
	}
	
	//Setters
	public void setErrorno(int n) {
		this.errorno = n;
	}
	public void setErrormsg(String m) {
		this.errormsg = m;
	}
	public void setPath(String p) {
		this.path = p;
	}
	
	//Getters
	public int getErrorno() {
		return this.errorno;
	}
	public String getErrormsg() {
		return this.errormsg;
	}
	public String getPath() {
		return this.path;
	}
	
	//Print properties
	public void print() {
		System.out.printf("\n");
		System.out.printf("================================================\n");
		System.out.printf("StudentGradingException [errorno= " + errorno + ", errormsg= " + errormsg);
		System.out.printf("\n");
		System.out.printf("================================================\n\n");
	}
	
	//Logging exceptions to a text file
	public void printToFile() {
		FileWriter file = null;
		String filename = this.path + "logs.txt";
		String log = "StudentGradingException [errorno= " + Integer.toString(errorno) + ", errormsg= " + errormsg;
		
		try {
			file = new FileWriter(filename, true);
			BufferedWriter buff = new BufferedWriter(file);
			PrintWriter pw = new PrintWriter (file);
		
			buff.write("\n");
			buff.write(log);
			printStackTrace(pw);
			buff.write("\n================================================\n\n");
			
			buff.close();
			pw.close();
		} 
		catch (IOException e) {
			System.out.printf("Error -- " + e.toString() + "\n");
		}
	}
	
	//Handling methods
	public String fixFileNotFound() {
		String a = path + "testdata/scores4.txt";
		
		System.out.printf("File not found. \n");
		System.out.printf("Switching to default file (\"score4.txt\") \n");
		System.out.printf("================================================\n\n");
		
		return a;
	}
	
	public int fixMissingSID() {
		int SID = 0000;
		
		System.out.printf("One Student is missing an ID number. \n");
		System.out.printf("Assigning SID with a default number of 0000. \n");
		System.out.printf("================================================\n\n");
		
		return SID;
	}
	
	public String fixFormat() {
		String a = "0000";
		
		System.out.printf("SID or score has a letter. \n");
		System.out.printf("Assigning token with a default value of \"0000\" \n");
		System.out.printf("================================================\n\n");
		
		return a;
	}
	
	public void reportMissingScore() {
		System.out.printf("One Student is missing a score. \n");
		System.out.printf("Assigning the last quiz with a default score of 0. \n");
		System.out.printf("================================================\n\n");
	}
	
	public String fixFileInputStreamNotFound() {
		String a = "/Users/anhtruong/VSC/Java/Exports/CIS 35a - Lab<6>/serialize/placeholder.dat";
		
		System.out.printf("File Input Stream not found. \n");
		System.out.printf("Switching to default file (\"placeholder.dat\") \n");
		System.out.printf("================================================\n\n");
		
		return a;
	}
}
