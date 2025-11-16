package util;

import java.io.*;
import java.util.*;

import model.Student;
import model.StudentGrade;
import exception.StudentGradingException;

public class UtilCopy {
	private String fname;
	private boolean DEBUG = false;

	public UtilCopy() {
		// Default Constructor
	}
	
	public UtilCopy(String file) {
		this.fname = file;
	}
	
	public void setFileName(String file) {
		this.fname = file;
	}
	public String getFileName() {
		return this.fname;
	}
	
	//Has filename as parameter
	public Student[] readFile(Student[] stu) {
		int counter = 0;
		boolean flag = false;
		
		try {
			//test opening file
			do {
				try {
					flag = openFile(fname);
				}
				catch(StudentGradingException s) {
					s.printToFile();
					setFileName(s.fixFileNotFound()); 
				}
			} while(flag == false);
			
			FileReader file = new FileReader(fname);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			while (!eof) {
				String line = buff.readLine();
				counter++;
				
				//Test if file has any record
				try {
					testRecord(line, counter);
				}
				catch(StudentGradingException s) {
					s.printToFile();
				}
				
				if (line == null) {
					eof = true;
				}
				else {
					if (DEBUG)
						System.out.printf("%s \n", line);
					if (counter == 1) {
						//Test if file has header
						try {
							testHeader(line);
						}
						catch(StudentGradingException s) {
							s.printToFile();
							stu[counter-1] = buildStudent(stu, line);
							counter++;
							continue;
						}
					}
					if (counter > 1 && counter < 42) {
						stu[counter-2] = buildStudent(stu, line);
					}
				}
			}
			buff.close();
		}
		catch (IOException e) {
			System.out.printf("Error -- " + e.toString() + "\n");
		}
		return stu;
	}
	
	public Student buildStudent(Student[] stu, String line) {
		Student tstu = new Student();
		int SID, score;
		int counter = 0;
		
		String s1 = "";
		StringTokenizer st = new StringTokenizer(line);
		
		//read the current line of text
		while (st.hasMoreTokens()) {
			s1 = st.nextToken();
			counter++;
			
			//Test if SID or score has letter
			try {
				testFormat(s1);
			}
			catch(StudentGradingException s) {
				s.printToFile();
				s1 = s.fixFormat();
			}
			
			if (counter == 1) {
				SID = Integer.parseInt(s1);			//convert string into int
				//Test if Student has SID
				try {
					testID(SID);
				}
				catch (StudentGradingException s) {
					s.printToFile();
					tstu.setIndvScore(counter-1, SID);
					SID = s.fixMissingSID();
					counter++;
				}
				tstu.setSID(SID);
			} 
			else {
				score = Integer.parseInt(s1);
				tstu.setIndvScore(counter-2, score);
			}
		}
		//Test if there's any missing score
		try {
			testScore(counter);
		}
		catch (StudentGradingException s) {
			s.printToFile();
			s.reportMissingScore();
		}
		return tstu;
	}
	
	//====================================================================
	
	//Test and throw any FileNotFoundException
	public boolean openFile(String filename) throws StudentGradingException, IOException {
		FileInputStream a1 = null;
		boolean flag = false;
		
		try {
			a1 = new FileInputStream(filename);
			flag = true;
		}
		catch(FileNotFoundException f) {
			throw new StudentGradingException(1, "File not found.");
		}
		
		a1.close();
		return flag;
	}
	
	//Test if file has any records
	public void testRecord(String line, int counter) throws StudentGradingException {
		if (line == null && counter == 2)
			throw new StudentGradingException(2, "Missing score record.");
	}
	
	//Test if file has header
	public void testHeader(String line) throws StudentGradingException {
		String s1 = "";
		StringTokenizer st = new StringTokenizer(line);
		
		//Header has no integers
		//If a file have no header but SID and scores have letters in the first line
		//Iterate through line until the last token is either
		//		Integer - throw custom exception
		//		string - catch format exception - don't throw custom exception
		while (st.hasMoreTokens()) {
			s1 = st.nextToken();
			
			try {
				Integer.parseInt(s1);
				throw new StudentGradingException(3, "Missing Header.");
			}
			catch(NumberFormatException n) {
				if (!st.hasMoreTokens())
					return;
				continue;
			}
		}
		
	}
	
	//Test and throw any missing SID exception
	public void testID(int SID) throws StudentGradingException {
		//Check number of digits (ignore default "0000")
		if (String.valueOf(SID).length() < 4 && SID != 0)
			throw new StudentGradingException(4, "Missing Student ID.");
	}
	
	//Test and throw any missing score exception
	public void testScore(int counter) throws StudentGradingException {
		//If all five scores are available, counter should be 6
		//If not, one of the scores is missing
		if (counter < 6)
			throw new StudentGradingException(5, "Missing Score.");
	}
	
	//Test if SID or scores has letter
	public void testFormat(String s1) throws StudentGradingException {
		//Attempt to convert tokens into Integer
		//If checked exception is thrown, either SID or scores contain a letter 
		try {
			Integer.parseInt(s1);
		}
		catch(NumberFormatException n) {
			throw new StudentGradingException(6, "Incorrect format.");
		}
	}
	
	//====================================================================
	
	//Serialization
	public void serializeGrades(StudentGrade[] a, String path) {
		int SID;
		String filename;
		FileOutputStream file;
		
		for(StudentGrade g : a) {
			SID = g.getStudent().getSID();
			filename = path + Integer.toString(SID) + ".dat";
			try {
				file = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(file);
				
				out.writeObject(g);
				file.close();
				out.close();
			} 
			catch (FileNotFoundException f) {
				System.out.printf("Error -- " + f.toString() + "\n");
			}
			catch (IOException e) {
				System.out.printf("Error -- " + e.toString() + "\n");
			}
		}
	}
	
	//Deserialization
	public void deserializeGrades(int SID, String path, int option) {
		String filename = path + Integer.toString(SID) + ".dat";
		boolean flag = false;
		
		//test opening file input stream
		do {
			try {
				flag = openFile(filename);
				
				FileInputStream file = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(file);
				
				StudentGrade newGrade = (StudentGrade) in.readObject();
				newGrade.print(option);
				
				file.close();
				in.close();
			}
			catch(StudentGradingException s) {
				s.printToFile();
				filename = s.fixFileInputStreamNotFound(); 
			} 
			catch (EOFException e) {
				flag = true;
				System.out.printf("Student ID does not exist. \n");
			}
			catch (IOException e) {
				flag = true;
				System.out.printf("Error -- " + e.toString() + "\n");
			}
			catch (ClassNotFoundException c) {
				flag = true;
				System.out.printf("Error -- " + c.toString() + "\n");
			}
		} while(flag == false);
	}
	//====================================================================
}
