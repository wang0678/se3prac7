package au.edu.flinders.uni.se3;

public interface Game2048ProcesserIf {

	/**
	 * tilt the board left all numbers should moved to left with same numbers in
	 * adjacent cells merged and add a new number in empty cells randomly
	 * 
	 */
	public void tilt_board_left();

	/**
	 * To get the total score that played so far
	 */
	public int score();

	/**
	 * Reset the gameboard to initial state, including setting the score to
	 * zero, reset all win/lose flag and empty all the numbers
	 */
	public void resetGame();

	/**
	 * Tilt the board to right, all numbers with same value in adjacent cells
	 * should be merged and add a new number in empty cells randomly
	 */
	public void tilt_board_right();

	/**
	 * Tilt the board to down side, all numbers with same value in adjacent
	 * cells should be merged and add a new number in empty cells randomly
	 */
	public void tilt_board_down();

	/**
	 * Tilt the board to up side, all numbers with same value in adjacent cells
	 * should be merged and add a new number in empty cells randomly
	 */
	public void tilt_board_up();

	/**
	 * To retrieve a number from the board by passing the given x, and y.
	 */
	public int getNumber(int x, int y);

	/**
	 * Set the game status to lose
	 */
	public void markGameLose();

	/**
	 * To check whether the game lose or not
	 */
	public boolean lose();

	/**
	 * To check whether the game win or not
	 */
	public boolean win();

	/**
	 * Check whether the board is full or not return false if the board is full
	 * and no merge would happen.
	 */
	public boolean canMove();

	
	
	public int[][] getBoard();
	
	
	public void setBoard(int[][] board);
}
