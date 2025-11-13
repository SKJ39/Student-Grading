//====================================================================
//Anh Truong
//CIS 35A -- 00450
//Lab 5 - Score Statistics
//Due: Mar 5, 2024
//====================================================================

package util;

import java.io.*;
import java.util.*;

import model.Student;

public class Util {
	private String fname;
	private boolean DEBUG = false;

	public Util() {
		// Default Constructor
	}
	
	public Util(String file) {
		this.fname = file;
	}
	
	public void setFileName(String file) {
		this.fname = file;
	}
	public String getFileName() {
		return this.fname;
	}
	
	//Has filename as parameter
	public Student[] readFile(String filename, Student[] stu) {
		int counter = 0;
		
		//open file
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			while (!eof) {
				String line = buff.readLine();
				counter++;
				
				if (line == null)
					eof = true;
				else {
					if (DEBUG)
						System.out.printf("%s \n", line);
					if (counter == 1);						//skip header
					if (counter > 1 && counter < 42)		//ignore anything past the 40th record
						stu[counter-2] = buildStudent(stu, line);
				}
			}
			buff.close();
		}
		catch (IOException e) {
			System.out.printf("Error -- " + e.toString() + "\n");
		}
		return stu;
	}
	
	//Don't have filename as parameter
	public Student[] readFile(Student[] stu) {
		return readFile(this.fname, stu);
	}
	
	public Student buildStudent(Student[] stu, String line) {
		Student tstu = new Student();
		int SID, score;
		
		String s1 = "";
		StringTokenizer st = new StringTokenizer(line);
		
		//read the current line of text
		while (st.hasMoreTokens()) {
			for (int i = 0; i < 6; i++) {
				s1 = st.nextToken();
				if (i == 0) {
					SID = Integer.parseInt(s1);			//convert string into int
					tstu.setSID(SID);
				} else {
					score = Integer.parseInt(s1);
					tstu.setIndvScore(i-1, score);
				}
			}
		}
		return tstu;
	}
}
