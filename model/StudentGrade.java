//====================================================================
//Anh Truong
//CIS 35A -- 00450
//Lab 6 - Exception Handling and API
//Due: Mar 17, 2024
//====================================================================

package model;

import java.io.*;

@SuppressWarnings("serial")		//Suppress the warning of the missing variable serialVersionUID
public class StudentGrade implements Serializable {
	private Student stu;
	private Statistics stat;

	//Default Constructor
	public StudentGrade() {}
	
	//Overloaded Constructor
	public StudentGrade(Student s, Statistics l) {
		this.stu = s;
		this.stat = l;
	}
	
	//Setters
	public void setStudent(Student s) {
		this.stu = s;
	}
	
	public void setStats(Statistics l) {
		this.stat = l;
	}

	//Getters
	public Student getStudent() {
		return stu;
	}

	public Statistics getStats() {
		return this.stat;
	}
	
	//Print
	public void print(int option) {
		switch (option) {
			case 1:
				stu.print();
				break;
			case 2:
				stat.print(4);
				break;
			case 3:
				stu.print();
				stat.print(4);
				break;
			default:
				System.out.printf("Invalid option. ");
		}
		System.out.printf("================================================\n\n");
	}
}
