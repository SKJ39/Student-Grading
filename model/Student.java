package model;

import java.io.*;

@SuppressWarnings("serial")		//Suppress the warning of the missing variable serialVersionUID
public class Student implements Serializable{
	private int SID;
	private int scores[] = new int[5];

	public Student() {
		//Default Constructor
	}
	
	//Overloaded Constructor
	public Student(int id, int[] s) {
		this.SID = id;
		this.scores = s;
	}
	
	//Setters
	public void setSID(int id) {
		this.SID = id;
	}
	public void setScores(int s[]) {
		this.scores = s;
	}
	public void setIndvScore(int i, int s) {
		this.scores[i] = s;
	}
	
	//Getters
	public int getSID() {
		return this.SID;
	}
	public int[] getScores() {
		return this.scores;
	}
	public int getIndvScore(int i) {
		return this.scores[i];
	}
	
	//Print properties
	public void print() {
		System.out.printf("Student ID: %04d \n", this.SID);
		System.out.printf("Scores: [ ");
		for (int s : scores) {
			System.out.printf("%d ", s);
		}
		System.out.printf("]");
		System.out.printf("\n\n");
	}
}
