package adapter;

import util.UtilCopy;
import model.Student;
import model.Statistics;
import model.StudentGrade;
import lab6.driver.Driver2;

public abstract class StudentAPIImpl implements StudentAPI{
	private String path;

	//Default Constructor
	public StudentAPIImpl() {
		Student[] lab = new Student[40];
		String username = "name"
		
		//Populate Student[] Array
		// TODO: Update the string below for the path to any .txt file in testdata
		String score_path = "/Users/{username}/GitHub/Student-Grading/testdata/scores6.txt"
		UtilCopy u = new UtilCopy(score_path);
		lab = u.readFile(lab);
		
		//Create a Statistics object
		Statistics statlab = new Statistics();
		statlab.findAll(lab);
		
		//Create and populate StudentGrade[] array
		StudentGrade[] grade = Driver2.buildStuGradArray(lab, statlab);
		
		//Serialize StudentGrade[]
		// TODO: Update the string below for the path to 'serialize' folder
		this.path = "/Users/{username}/GitHub/Student-Grading/serialize";
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
