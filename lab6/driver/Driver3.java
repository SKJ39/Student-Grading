//====================================================================
//Anh Truong
//CIS 35A -- 00450
//Lab 6 - Exception Handling, Serialization, and API
//Due: Mar 17, 2024
//====================================================================

package lab6.driver;
import adapter.StudentAPI;
import adapter.SAPI;

public class Driverlab6r3 {

	public static void main(String[] args) {
		StudentAPI a1 = new SAPI();
		
		a1.printStudentScore(9999);
		a1.printStatistics();
	}
}
