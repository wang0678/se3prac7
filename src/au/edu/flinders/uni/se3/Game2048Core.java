package au.edu.flinders.uni.se3;

import java.util.ArrayList;

import au.edu.flinders.uni.se3.util.GameUtil;
import au.edu.flinders.uni.se3.Game2048ProcesserIf;

public class Game2048Core implements Game2048ProcesserIf {
	private static final int ROWS = GameUtil.ROWS;
	private static final int COLS = GameUtil.COLS;
	private int score = 0;
	private boolean lose = false;

	private int[][] tiles = new int[ROWS][COLS];

	/**
	 * Method to tilt line to left without same number merge.
	 */
	public int[] tilt_line_left(int[] oldLine) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ROWS; i++) {
			if (oldLine[i] != 0) {
				list.add(new Integer(oldLine[i]));
			}
		}
		if (list.size() == 0) {
			return oldLine;
		} else {
			int[] after = new int[COLS];

			for (int i = 0; i < list.size(); i++) {
				after[i] = list.get(i).intValue();
			}
			return after;
		}
	}

	/**
	 * Method to merge same number in adjacent cells. The input line should
	 * comply with all numbers tilt left without merge (return value of
	 * tilt_line_left method).
	 */
	public int[] combine_tiles(int[] oldline) {
		int j = 0;
		int[] after = new int[COLS];
		for (int i = 0; i < COLS && oldline[i] > 0; i++) {
			int value = oldline[i];
			if (i < COLS - 1 && oldline[i] == oldline[i + 1]) {
				value *= 2;
				// Calculate score here.
				score += value;
				after[j] = value;
				i++;
			} else {
				after[j] = oldline[i];

			}
			j++;
		}

		return after;
	}

	/**
	 * Tilt line number to left with merge.
	 */
	public int[] tilt_line_left_combine(int[] oldline) {
		return combine_tiles(tilt_line_left(oldline));
	}

	public int[][] tilt_board_left(int[][] old) {
		int[][] after = new int[ROWS][COLS];
		for (int i = 0; i < COLS; i++) {
			int[] line = tilt_line_left_combine(old[i]);
			System.arraycopy(line, 0, after[i], 0, COLS);
		}
		return after;
	}

	public int[][] tilt_board_up(int[][] old) {
		int[][] temp = rotate_clockWise_270degree(old);
		int[][] temp2 = tilt_board_left(temp);
		return rotate_clockWise_90degree(temp2);
	}

	public int[][] tilt_board_down(int[][] old) {
		int[][] temp = rotate_clockWise_90degree(old);
		int[][] temp2 = tilt_board_left(temp);
		return rotate_clockWise_270degree(temp2);
	}

	public int[][] tilt_board_right(int[][] old) {
		int[][] temp = rotate_clockWise_180degree(old);
		int[][] temp2 = tilt_board_left(temp);
		return rotate_clockWise_180degree(temp2);
	}

	public int[][] rotate_clockWise_90degree(int[][] old) {
		if (old == null) {
			return null;
		}
		int ROWS = old.length;
		int COLS = old[0].length;
		int[][] temp = new int[ROWS][COLS];

		int dst = ROWS - 1;
		// rotate the matrix clockwise 90 degree.
		for (int i = 0; i < ROWS; i++, dst--) {
			for (int j = 0; j < COLS; j++) {
				temp[j][dst] = old[i][j];
			}
		}

		return temp;
	}

	public int[][] rotate_clockWise_180degree(int[][] old) {
		return rotate_clockWise_90degree(rotate_clockWise_90degree(old));
	}

	public int[][] rotate_clockWise_270degree(int[][] old) {
		return rotate_clockWise_90degree(rotate_clockWise_90degree(rotate_clockWise_90degree(old)));
	}

	private boolean isBoardFull() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (tiles[i][i] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean canMove() {
		if (!isBoardFull()) {
			return true;
		}
		for (int x = 0; x < ROWS; x++) {
			for (int y = 0; y < COLS; y++) {
				if ((x < 3 && tiles[x][y] == tiles[x + 1][y])
						|| ((y < 3) && tiles[x][y] == tiles[x][y + 1])) {
					return true;
				}
			}
		}
		return false;
	}

	public int getNumber(int x, int y) {
		if (x < 0 || x >= ROWS || y < 0 || y >= COLS) {
			return -1;
		}
		return tiles[x][y];
	}

	public void resetGame() {
		lose = false;
		score=0;
		tiles = new int[ROWS][COLS];
		addANumber();
		addANumber();
	}

	private void addANumber() {
		if (!isBoardFull()) {
			ArrayList<String> emptySpots = GameUtil.snapshotEmptySpots(tiles);
			int index = (int) (Math.random() * emptySpots.size())
					% emptySpots.size();
			String emptyLocation = emptySpots.get(index);

			int x = Integer.parseInt(emptyLocation.substring(0, 1));
			int y = Integer.parseInt(emptyLocation.substring(2, 3));
			tiles[x][y] = Math.random() < 0.9 ? 2 : 4;
		}
	}

	public void tilt_board_down() {
		int[][] tempTiles = tilt_board_down(tiles);
		boolean needed = !GameUtil.compare(tempTiles, tiles);
		tiles = tempTiles;

		if (needed) {
			addANumber();
		}

	}

	public void tilt_board_left() {
		int[][] tempTiles = tilt_board_left(tiles);
		boolean needed = !GameUtil.compare(tempTiles, tiles);
		tiles = tempTiles;
		if (needed) {
			addANumber();
		}

	}

	public void tilt_board_right() {
		int[][] tempTiles = tilt_board_right(tiles);
		boolean needed = !GameUtil.compare(tempTiles, tiles);
		tiles = tempTiles;
		if (needed) {
			addANumber();
		}

	}

	public void tilt_board_up() {
		int[][] tempTiles = tilt_board_up(tiles);
		boolean needed = !GameUtil.compare(tempTiles, tiles);
		tiles = tempTiles;
		if (needed) {
			addANumber();
		}

	}

	public void markGameLose() {
		lose = true;

	}

	public boolean win() {
		return score() == GameUtil.WINPOINTS_2048;
	}

	public boolean lose() {
		return lose;
	}

	public int[][] getBoard() {
		return tiles;
	}

	public void setBoard(int[][] tiles) {
		this.tiles = tiles;
	}

	public int score() {
		return score;
	}

}
