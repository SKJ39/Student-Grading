package model;

import java.io.*;

@SuppressWarnings("serial")		//Suppress the warning of the missing variable serialVersionUID
public class Statistics implements Serializable{
	//5 quizzes
	private Integer[] lowscores = new Integer[5];
	private Integer[] highscores = new Integer[5];
	private Float[] avgscores = new Float[5];

	public Statistics() {
		// Default Constructor
	}
	
	//Overloaded Constructor
	public Statistics(Integer[] l, Integer[] h, Float[] avg) {
		this.lowscores = l;
		this.highscores = h;
		this.avgscores = avg;
	}
	
	//Setters
	//Find low scores
	public void findLow(Student[] stu) {
		int low;
		
		for (int i = 0; i < 5; i++) {
			low = 101;
			
			for (Student s : stu) {
				if (s == null)
					break;
				if (s.getIndvScore(i) < low) {
					low = s.getIndvScore(i);
				}
			}
			if (low != 101)
				this.lowscores[i] = low;
		}
	}
	
	//Find high scores
	public void findHigh(Student[] stu) {
		int high;
		
		for (int i = 0; i < 5; i++) {
			high = -1;
			
			for (Student s : stu) {
				if (s == null)
					break;
				if (s.getIndvScore(i) > high) {
					high = s.getIndvScore(i);
				}
			}
			if (high != -1)
				this.highscores[i] = high;
		}
	}
	
	//Find the average
	public void findAvg(Student[] stu) {
		int count;
		float sum;
		float avg = 0;
		
		for (int i = 0; i < 5; i++) {
			sum = 0;
			count = 0;
			
			for (Student s : stu) {
				if (s == null)
					break;
				sum += s.getIndvScore(i);
				count++;
			}
			
			if (count != 0.0) {
				avg = sum / count;
				this.avgscores[i] = avg;
			}
		}
	}
	
	//Find all statistics
	public void findAll(Student[] stu) {
		findLow(stu);
		findHigh(stu);
		findAvg(stu);
	}
	
	//Getters
	public Integer[] getLow() {
		return this.lowscores;
	}
	public int getLowScore(int i) {
		return this.lowscores[i];
	}
	public Integer[] getHigh() {
		return this.highscores;
	}
	public int getHighScore(int i) {
		return this.highscores[i];
	}
	public Float[] getAvg() {
		return this.avgscores;
	}
	public float getAvgScore(int i) {
		return this.avgscores[i];
	}
	
	//Print scores
	public void print(int option) {
		System.out.printf("\n");
		if (this.lowscores[0] == null && this.highscores[0] == null && this.avgscores[0] == null) {
			System.out.printf("There is no score record available for statistics.");
			return;
		}
		switch (option) {
			case 1: 
				printLow();
				break;
			case 2: 
				printHigh();
				break;
			case 3: 
				printAvg();
				break;
			case 4: 
				printLow(); printHigh(); printAvg();
				break;
			default: 
				System.out.printf("Invalid option! \n");
				break;
		}
	}
	
	//Print low scores
	public void printLow() {
		System.out.printf("%-15s", "Low scores: ");
		for (int l : this.lowscores) {
			System.out.printf("%d ", l);
		}
		System.out.printf("\n");
	}
	
	//Print high scores
	public void printHigh() {
		System.out.printf("%-15s", "High scores: ");
		for (int h : this.highscores) {
			System.out.printf("%d ", h);
		}
		System.out.printf("\n");
	}
	
	//Print average scores
	public void printAvg() {
		System.out.printf("%-15s", "Average: ");
		for (float avg : this.avgscores) {
			System.out.printf("%.2f ", avg);
		}
		System.out.printf("\n");
	}
}
