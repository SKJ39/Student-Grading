//====================================================================
//Anh Truong
//CIS 35A -- 00450
//Lab 6 - Exception Handling, Serialization and API
//Due: Mar 17, 2024
//====================================================================

package adapter;

import util.UtilCopy;
import model.Student;
import model.Statistics;
import model.StudentGrade;
import lab6.driver.Driverlab6r2;

public abstract class StudentAPIImpl implements StudentAPI{
	private String path;

	//Default Constructor
	public StudentAPIImpl() {
		Student[] lab = new Student[40];
		
		//Populate Student[] Array
		UtilCopy u = new UtilCopy("/Users/anhtruong/VSC/Java/Exports/CIS 35a - Lab<6>/testdata/scores4.txt");
		lab = u.readFile(lab);
		
		//Create a Statistics object
		Statistics statlab = new Statistics();
		statlab.findAll(lab);
		
		//Create and populate StudentGrade[] array
		StudentGrade[] grade = Driverlab6r2.buildStuGradArray(lab, statlab);
		
		//Serialize StudentGrade[]
		this.path = "/Users/anhtruong/VSC/Java/Exports/CIS 35a - Lab<6>/serialize/";
		u.serializeGrades(grade, path);
	}
	
	//Getter and Setter
	public String getPath() {
		return this.path;
	}

	public void setPath(String p) {
		this.path = p;
	}
	
	//Print
	public void printStudentScore(int SID) {
		UtilCopy u = new UtilCopy();
		u.deserializeGrades(SID, path, 1);
	}
	
	public void printStatistics() {
		UtilCopy u = new UtilCopy();
		u.deserializeGrades(1234, path, 2);
	}
}
