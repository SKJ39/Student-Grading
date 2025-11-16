package lab6.driver;
import adapter.StudentAPI;
import adapter.SAPI;

public class Driver3 {

	public static void main(String[] args) {
		StudentAPI a1 = new SAPI();
		
		a1.printStudentScore(9999);
		a1.printStatistics();
	}
}
