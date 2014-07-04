package au.edu.flinders.uni.cs.se3.prac3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test Runner class to invoke all the test cases.
 */
public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestGame2048.class,WhiteBoxTestGame2048.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}

}
