package au.edu.flinders.uni.cs.se3.prac3;

import au.edu.flinders.uni.se3.Game2048Core;
import au.edu.flinders.uni.se3.Game2048ProcesserIf;
import au.edu.flinders.uni.se3.util.GameUtil;
import junit.framework.TestCase;

public class WhiteBoxTestGame2048 extends TestCase {
	Game2048ProcesserIf gameCore = new Game2048Core();

	public void test_getNumber() {
			// x, y is the first element.
		int x = 0, y = 0;
		gameCore.resetGame();
		int value = gameCore.getNumber(x, y);
		if (value < 0) {
			fail();
		}
		// y out of boundary
		x = 0;
		y = 5;
		value = gameCore.getNumber(x, y);
		assertEquals(-1, value);
		// x out of boundary
		x = 5;
		y = 0;
		value = gameCore.getNumber(x, y);
		assertEquals(-1, value);

		// x and y within ROWS and COLS range
		x = 2;
		y = 3;
		value = gameCore.getNumber(x, y);
		if (value < 0) {
			fail();
		}
	}
    	
    }
}
