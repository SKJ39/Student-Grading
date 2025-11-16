package lab6.driver;
import model.StudentGrade;
import util.UtilCopy;
import model.Student;
import model.Statistics;

public class Driver2 {

	public static void main(String[] args) {
		Student[] lab = new Student[40];
		
		//Populate Student[] Array
		// TODO: Update the string below for the path to any .txt file in testdata
		String score_path = "/Users/{username}/GitHub/Student-Grading/testdata/scores6.txt"
		UtilCopy u = new UtilCopy(score_path);
		lab = u.readFile(lab);
		
		//Create a Statistics object
		Statistics statlab = new Statistics();
		statlab.findAll(lab);
		
		//Create and populate StudentGrade[] array
		StudentGrade[] grade = buildStuGradArray(lab, statlab);
		
		//Serialize and Deserialize StudentGrade
		// TODO: Update the string below for the path to 'serialize' folder
		String serialize_path ="/Users/{username}/GitHub/Student-Grading/serialize" 
		String path = serialize_path;
		
		u.serializeGrades(grade, path);
		for (Student s : lab) {
			if (s == null)
				break;
			u.deserializeGrades(s.getSID(), path, 3);
		}
		
		System.out.printf("================================================\n");
		System.out.printf("================================================\n");
		System.out.printf("================================================\n\n");
	}
	
	public static StudentGrade[] buildStuGradArray(Student[] stu, Statistics lab) {
		int counter = 0;
		
		//Iterate through Student[] and check how many students have records 
		for (Student s : stu) {
			if(s == null)
				break;
			counter++;
		}
		StudentGrade[] grade = new StudentGrade[counter];
		
		//Iterate through Student[] and assign each Student object to StudentGrade[]
		counter = 0;
		for (Student s : stu) {
			if(s == null)
				break;
			StudentGrade record = new StudentGrade(s, lab);
			grade[counter] = record;
			counter++;
		}
		
		return grade;
	}

}
