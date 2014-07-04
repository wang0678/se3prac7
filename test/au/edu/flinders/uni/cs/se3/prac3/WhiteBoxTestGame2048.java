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
    		public void test_resetGame(){
		gameCore.resetGame();
		int[][] board = gameCore.getBoard();
		int emptyNo = GameUtil.snapshotEmptySpots(board).size();
		assertEquals(GameUtil.ROWS*GameUtil.COLS-2,emptyNo);
		assertTrue(gameCore.canMove());
		assertFalse(gameCore.lose());
		assertFalse(gameCore.win());
	}
	public void test_tilt_board_left(){
		int[][] partialExpected = null;
		
		int[][] mockBoards = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_left();
		int[][]after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		 mockBoards = new int[][]{
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_left();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		mockBoards = new int[][]{
					{1,0,2,0},
					{1,0,2,0},
					{1,0,2,0},
					{1,0,2,0}
			};
		 
		partialExpected= new int[][]{
				    {1,2,0,0},
					{1,2,0,0},
					{1,2,0,0},
					{1,2,0,0}
		  };
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_left();
		after = gameCore.getBoard();
		assertEquals(partialExpected[0][0],after[0][0]);
		assertEquals(partialExpected[0][1],after[0][1]);
		assertEquals(partialExpected[1][0],after[1][0]);
		assertEquals(partialExpected[1][1],after[1][1]);
		assertEquals(partialExpected[2][0],after[2][0]);
		assertEquals(partialExpected[2][1],after[2][1]);
		assertEquals(partialExpected[3][0],after[3][0]);
		assertEquals(partialExpected[3][1],after[3][1]);		
		
		 int SumOfRest = after[0][2]+after[0][3]
		              		                + after[1][2]+after[1][3]
		              		                + after[2][2]+after[2][3]
		              		                + after[3][2]+after[3][3];
		 if(SumOfRest==0){
		  	  fail();
		 }
		
	
		mockBoards = new int[][]{
						{1,0,0,2},
						{1,0,0,2},
						{1,0,0,2},
						{1,0,0,2}
				};
			 
		partialExpected= new int[][]{
					    {1,2,0,0},
						{1,2,0,0},
						{1,2,0,0},
						{1,2,0,0}
			  };
		gameCore.setBoard(mockBoards);
	    gameCore.tilt_board_left();
		after = gameCore.getBoard();
		assertEquals(partialExpected[0][0],after[0][0]);
		assertEquals(partialExpected[0][1],after[0][1]);
		assertEquals(partialExpected[1][0],after[1][0]);
		assertEquals(partialExpected[1][1],after[1][1]);
		assertEquals(partialExpected[2][0],after[2][0]);
		assertEquals(partialExpected[2][1],after[2][1]);
		assertEquals(partialExpected[3][0],after[3][0]);
		assertEquals(partialExpected[3][1],after[3][1]);		
		
		 SumOfRest = after[0][2]+after[0][3]
		              		                + after[1][2]+after[1][3]
		              		                + after[2][2]+after[2][3]
		              		                + after[3][2]+after[3][3];
		 if(SumOfRest==0){
		  	  fail();
		 }
		
		
		mockBoards = new int[][]{
					{1,2,3,4},
					{1,2,3,4},
					{1,2,3,4},
					{1,2,3,4}
			};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_left();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
			
			
		 mockBoards = new int[][]{
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1}
				};
		  
		 partialExpected= new int[][]{
				    {2,2,0,0},
					{2,2,0,0},
					{2,2,0,0},
					{2,2,0,0}
		  };
		  gameCore.setBoard(mockBoards);
		  gameCore.tilt_board_left();
		  after = gameCore.getBoard();
		  
		  assertEquals(partialExpected[0][0],after[0][0]);
		  assertEquals(partialExpected[0][1],after[0][1]);
		  assertEquals(partialExpected[1][0],after[1][0]);
		  assertEquals(partialExpected[1][1],after[1][1]);
		  assertEquals(partialExpected[2][0],after[2][0]);
		  assertEquals(partialExpected[2][1],after[2][1]);
		  assertEquals(partialExpected[3][0],after[3][0]);
		  assertEquals(partialExpected[3][1],after[3][1]);		
		  
		  SumOfRest = after[0][2]+after[0][3]
		                + after[1][2]+after[1][3]
		                + after[2][2]+after[2][3]
		                + after[3][2]+after[3][3];
		  if(SumOfRest==0){
			  fail();
		  }
	}
		public void test_tilt_board_up(){
		gameCore.resetGame();
		
		int[][] mockBoards = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_up();
		int[][]after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		 mockBoards = new int[][]{
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_up();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		mockBoards = new int[][]{
					{1,1,1,1},
					{0,0,0,0},
					{2,2,2,2},
					{0,0,0,0}
			};
		 
		
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_up();
		after = gameCore.getBoard();
		assertEquals(1,after[0][0]);
		assertEquals(1,after[0][1]);
		assertEquals(1,after[0][2]);
		assertEquals(1,after[0][3]);
		assertEquals(2,after[1][0]);
		assertEquals(2,after[1][1]);
		assertEquals(2,after[1][2]);
		assertEquals(2,after[1][3]);		
		  
		int SumOfRest = after[2][0]+after[2][1]
		                + after[2][2]+after[2][3]
		                + after[3][0]+after[3][1]
		                + after[3][2]+after[3][3];
		if(SumOfRest==0){
			  fail();
		}
	
		
		
		mockBoards = new int[][]{
						{1,1,1,1},
						{0,0,0,0},
						{0,0,0,0},
						{2,2,2,2}
				};
		
		gameCore.setBoard(mockBoards);
	    gameCore.tilt_board_up();
		after = gameCore.getBoard();
		assertEquals(1,after[0][0]);
		assertEquals(1,after[0][1]);
		assertEquals(1,after[0][2]);
		assertEquals(1,after[0][3]);
		assertEquals(2,after[1][0]);
		assertEquals(2,after[1][1]);
		assertEquals(2,after[1][2]);
		assertEquals(2,after[1][3]);		
		  
		SumOfRest = after[2][0]+after[2][1]
		          + after[2][2]+after[2][3]
		          + after[3][0]+after[3][1]
		          + after[3][2]+after[3][3];
		
		if(SumOfRest==0){
			  fail();
		}	
		
		
		mockBoards = new int[][]{
					{1,1,1,1},
					{2,2,2,2},
					{3,3,3,3},
					{4,4,4,4}
			};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_up();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
			
			
		 mockBoards = new int[][]{
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1}
				};
		  
		
		  gameCore.setBoard(mockBoards);
		  gameCore.tilt_board_up();
		  after = gameCore.getBoard();
		  
		  assertEquals(2,after[0][0]);
		  assertEquals(2,after[0][1]);
		  assertEquals(2,after[0][2]);
		  assertEquals(2,after[0][3]);
		  assertEquals(2,after[1][0]);
		  assertEquals(2,after[1][1]);
		  assertEquals(2,after[1][2]);
		  assertEquals(2,after[1][3]);		
		  
		  SumOfRest = after[2][0]+after[2][1]
		              + after[2][2]+after[2][3]
		              + after[3][0]+after[3][1]
		              + after[3][2]+after[3][3];
		                 		
		  if(SumOfRest==0){
			  fail();
		  }
	}
public void test_tilt_board_right(){
		gameCore.resetGame();
		
		int[][] mockBoards = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_right();
		int[][]after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		 mockBoards = new int[][]{
				{0,0,0,1},
				{0,0,0,1},
				{0,0,0,1},
				{0,0,0,1}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_right();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		mockBoards = new int[][]{
					{0,2,0,1},
					{0,2,0,1},
					{0,2,0,1},
					{0,2,0,1}
			};
		 
		
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_right();
		after = gameCore.getBoard();
		assertEquals(1,after[0][3]);
		assertEquals(1,after[1][3]);
		assertEquals(1,after[2][3]);
		assertEquals(1,after[3][3]);
		assertEquals(2,after[0][2]);
		assertEquals(2,after[1][2]);
		assertEquals(2,after[2][2]);
		assertEquals(2,after[2][2]);		
		  
		int SumOfRest = after[0][0]+after[0][1]
		                + after[1][0]+after[1][1]
		                + after[2][0]+after[2][1]
		                + after[3][0]+after[3][1];
		if(SumOfRest==0){
			  fail();
		}
	
		
		
		mockBoards = new int[][]{
						{2,0,0,1},
						{2,0,0,1},
						{2,0,0,1},
						{2,0,0,1}
				};
		
		gameCore.setBoard(mockBoards);
	    gameCore.tilt_board_right();
		after = gameCore.getBoard();
		assertEquals(1,after[0][3]);
		assertEquals(1,after[1][3]);
		assertEquals(1,after[2][3]);
		assertEquals(1,after[3][3]);
		assertEquals(2,after[0][2]);
		assertEquals(2,after[1][2]);
		assertEquals(2,after[2][2]);
		assertEquals(2,after[2][2]);		
		  
		SumOfRest = after[0][0]+after[0][1]
		                + after[1][0]+after[1][1]
		                + after[2][0]+after[2][1]
		                + after[3][0]+after[3][1];
		if(SumOfRest==0){
			  fail();
		}
		
		
		mockBoards = new int[][]{
					{4,3,2,1},
					{4,3,2,1},
					{4,3,2,1},
					{4,3,2,1}
			};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_right();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
			
			
		 mockBoards = new int[][]{
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1}
				};
		  
		
		  gameCore.setBoard(mockBoards);
		  gameCore.tilt_board_right();
		  after = gameCore.getBoard();
		  
			assertEquals(2,after[0][3]);
			assertEquals(2,after[1][3]);
			assertEquals(2,after[2][3]);
			assertEquals(2,after[3][3]);
			assertEquals(2,after[0][2]);
			assertEquals(2,after[1][2]);
			assertEquals(2,after[2][2]);
			assertEquals(2,after[2][2]);		
			  
			SumOfRest = after[0][0]+after[0][1]
			                + after[1][0]+after[1][1]
			                + after[2][0]+after[2][1]
			                + after[3][0]+after[3][1];
			if(SumOfRest==0){
				  fail();
			}
	}
public void test_tilt_board_down(){
		gameCore.resetGame();
		
		int[][] mockBoards = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_down();
		int[][]after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		 mockBoards = new int[][]{
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{1,1,1,1}
		};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_down();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
		
		
		mockBoards = new int[][]{
					{1,1,1,1},
					{0,0,0,0},
					{2,2,2,2},
					{0,0,0,0}
			};
		 
		
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_down();
		after = gameCore.getBoard();
		assertEquals(1,after[2][0]);
		assertEquals(1,after[2][1]);
		assertEquals(1,after[2][2]);
		assertEquals(1,after[2][3]);
		assertEquals(2,after[3][0]);
		assertEquals(2,after[3][1]);
		assertEquals(2,after[3][2]);
		assertEquals(2,after[3][3]);		
		  
		int SumOfRest = after[0][0]+after[0][1]
		                + after[0][2]+after[0][3]
		                + after[1][0]+after[1][1]
		                + after[1][2]+after[1][3];
		if(SumOfRest==0){
			  fail();
		}
	
		
		
		mockBoards = new int[][]{
						{1,1,1,1},
						{0,0,0,0},
						{0,0,0,0},
						{2,2,2,2}
				};
		
		gameCore.setBoard(mockBoards);
	    gameCore.tilt_board_down();
		after = gameCore.getBoard();
		assertEquals(1,after[2][0]);
		assertEquals(1,after[2][1]);
		assertEquals(1,after[2][2]);
		assertEquals(1,after[2][3]);
		assertEquals(2,after[3][0]);
		assertEquals(2,after[3][1]);
		assertEquals(2,after[3][2]);
		assertEquals(2,after[3][3]);		
		  
		SumOfRest = after[0][0]+after[0][1]
		                + after[0][2]+after[0][3]
		                + after[1][0]+after[1][1]
		                + after[1][2]+after[1][3];
		
		if(SumOfRest==0){
			  fail();
		}	
		
		
		mockBoards = new int[][]{
					{1,1,1,1},
					{2,2,2,2},
					{3,3,3,3},
					{4,4,4,4}
			};
		gameCore.setBoard(mockBoards);
		gameCore.tilt_board_down();
		after = gameCore.getBoard();
		assertTrue(GameUtil.compareExpectToActualBoard(mockBoards, after));
			
			
		 mockBoards = new int[][]{
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1},
						{1,1,1,1}
				};
		  
		
		  gameCore.setBoard(mockBoards);
		  gameCore.tilt_board_down();
		  after = gameCore.getBoard();
		  
			assertEquals(2,after[2][0]);
			assertEquals(2,after[2][1]);
			assertEquals(2,after[2][2]);
			assertEquals(2,after[2][3]);
			assertEquals(2,after[3][0]);
			assertEquals(2,after[3][1]);
			assertEquals(2,after[3][2]);
			assertEquals(2,after[3][3]);	
			  
			SumOfRest = after[0][0]+after[0][1]
			                + after[0][2]+after[0][3]
			                + after[1][0]+after[1][1]
			                + after[1][2]+after[1][3];
		                 		
		  if(SumOfRest==0){
			  fail();
		  }
	}

    public void test_markGameLose(){
    	
    	gameCore.markGameLose();
    	assertFalse(gameCore.win());
    	assertTrue(gameCore.lose());
    } public void test_canMove(){
    	
    	int[][] mockBoards = {
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};
		gameCore.setBoard(mockBoards);
		assertTrue(gameCore.canMove());
    	
		
		mockBoards = new int[][]{
				{0,1,1,0},
				{0,1,1,0},
				{0,1,0,0},
				{0,1,0,0}
		};
		gameCore.setBoard(mockBoards);
		assertTrue(gameCore.canMove());
    	mockBoards = new int[][]{
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,1}
		};
		gameCore.setBoard(mockBoards);
		assertTrue(gameCore.canMove());
		
		mockBoards = new int[][]{
				{2,2,1,1},
				{2,2,1,1},
				{2,2,1,1},
				{2,2,1,1}
		};
		gameCore.setBoard(mockBoards);
		assertTrue(gameCore.canMove());
		
		
		mockBoards = new int[][]{
				{2,3,1,0},
				{7,6,5,4},
				{2,3,1,6},
				{6,5,4,1}
		};
		gameCore.setBoard(mockBoards);
		assertFalse(gameCore.canMove());
		}
    
    public void  test_lose(){
    	gameCore.resetGame();
    	assertFalse(gameCore.lose());
    	
    	gameCore.markGameLose();
    	assertTrue(gameCore.lose());
    	
    }
}
}