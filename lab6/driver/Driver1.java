package lab6.driver;

import model.Student;
import util.UtilCopy;

public class Driver1 {

	public static void main(String[] args) {
		Student[] lab = new Student[40];
		
		//Populate Student Array
		// TODO: Update the string below for the path to any .txt file in testdata
		String score_path = "/Users/{username}/GitHub/Student-Grading/testdata/scores6.txt"
		UtilCopy u = new UtilCopy(score_path);
		lab = u.readFile(lab);
		
		//Print SID and scores for each Student object
		for (Student s : lab) {
			if (s == null) {
				break;
			}
			s.print();
		}
	}
}
