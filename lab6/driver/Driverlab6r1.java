//====================================================================
//Anh Truong
//CIS 35A -- 00450
//Lab 6 - Exception Handling, Serialization and API
//Due: Mar 17, 2024
//====================================================================

package lab6.driver;

import model.Student;
import util.UtilCopy;

public class Driverlab6r1 {

	public static void main(String[] args) {
		Student[] lab = new Student[40];
		
		//Populate Student Array
		//UtilCopy u = new UtilCopy("somerandomfilename.txt");
		UtilCopy u = new UtilCopy("/Users/anhtruong/VSC/Java/Exports/CIS 35a - Lab<6>/testdata/scores6.txt");
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
