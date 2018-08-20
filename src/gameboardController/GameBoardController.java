package gameboardController;

import java.util.Random;

/**
 * Class controller package where you can handle all the logic to the game
 * 
 * @author Juan Camilo Ibáñez Nieto
 */

public class GameBoardController {

	// board of the game with his own copy for the user's view
	private Cell[][] minesweeper;
	private Cell[][] minesweeperCOPY;

	// constants used in the class
	private final static String DOT = ".";
	private final static String DISABLE_CELL = "_";
	private final static String MINE = "*";
	private final static String FLAG = "P";
	private final static String WON = "YOU'VE SURVIVE";
	private final static String LOSE = "YOU LOSE";
	private final static String TRYING = "ON IT";
	private final static String REMOVE = "REMOVE FLAG";
	private final static String WHITE = "WHITE CELLS";

	// Attributes of the class
	private int minesFromUser;
	private int correctFlags;
	private int wrongFlags;
	private int rowsBoard;
	private int colsBoard;

	// Methods from the class

	/**
	 * Method that starts the board
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param height int of the height of the board
	 * @param width  int of the width of the board
	 * @param mines  int of the mines in the board
	 */
	public void startGame(int height, int width, int mines) {
		this.minesweeper = new Cell[height][width];
		this.minesweeperCOPY = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				minesweeper[i][j] = new Cell();
				minesweeper[i][j].setValue(DOT);
				minesweeperCOPY[i][j] = new Cell();
				minesweeperCOPY[i][j].setValue(DOT);

				minesFromUser = mines;
				correctFlags = 0;
				rowsBoard = height;
				colsBoard = width;
				wrongFlags = 0;
			}
		}
		mining(height, width, mines);
		enumerate(height, width);

	}

	/**
	 * Method that starts mining the board in random places
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param height int of the height of the board
	 * @param width  int of the width of the board
	 * @param mines  int of the mines in the board
	 */
	private void mining(int height, int width, int mines) {
		Random rn = new Random();

		while (mines != 0) {

			int randomRow = rn.nextInt(height);
			int randomCol = rn.nextInt(width);
			minesweeper[randomRow][randomCol].setValue(MINE);

			minesweeperCOPY[randomRow][randomCol].setValue(MINE);
			mines--;
		}
	}

	/**
	 * Method that starts putting the number of near mines that a cell could have.
	 * This is happening while the Disable_cell is being setted when the cell
	 * doesn't have near mines.
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param height int of the height of the board
	 * @param width  int of the width of the board
	 */
	private void enumerate(int height, int width) {
		// TODO Auto-generated method stub

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int amount = calculateMines(i, j, height - 1, width - 1);
				if (amount > 0) {
					minesweeper[i][j].setValue(amount + "");

					minesweeperCOPY[i][j].setValue(amount + "");
				} else if (amount == 0) {
					minesweeper[i][j].setValue(DISABLE_CELL);

					minesweeperCOPY[i][j].setValue(DISABLE_CELL);
				}

			}

		}

	}

	/**
	 * It calculates the mines that are at 1 cell of distance. One of the largest
	 * methods, because it's more difficult to automate
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param i      int of the position in X axis from the matrix
	 * @param j      int of the position in Y axis from the matrix
	 * @param height int of the height of the board
	 * @param width  int of the width of the board
	 */

	private int calculateMines(int i, int j, int height, int width) {
		// TODO Auto-generated method stub
		int amount = 0;

		// we will start analyzing the critical zones of the matrix, that is, the
		// corners

		if (i == 0 && j == 0) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {
				String valueOne = minesweeper[i][j + 1].getValue();
				String valueTwo = minesweeper[i + 1][j + 1].getValue();
				String valueThree = minesweeper[i + 1][j].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
			}
		} else if (i == 0 && j == width) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {
				String valueOne = minesweeper[i + 1][j].getValue();
				String valueTwo = minesweeper[i + 1][j - 1].getValue();
				String valueThree = minesweeper[i][j - 1].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
			}
		} else if (i == height && j == width) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i - 1][j].getValue();
				String valueTwo = minesweeper[i - 1][j - 1].getValue();
				String valueThree = minesweeper[i][j - 1].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
			}
		} else if (i == height && j == 0) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i - 1][j].getValue();
				String valueTwo = minesweeper[i - 1][j + 1].getValue();
				String valueThree = minesweeper[i][j + 1].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
			}
		}
		// Then, we will analyze the zones which are not the corners neither the middle
		// of the matrix
		// and by the center of the matrix I refer to those zones that have at least a
		// 3x3 adjacency matrix for each cell
		// .   .   .   .
		//   _________
		// . | .   . | .
		// . | .   . | .
		// . | .   . | .
		//   ---------  ----->This is the center of this matrix of 4x5 or in other words
		// .   .   .   .       the zone where doesn't appear the out of bound exception :)
		//

		else if (i == 0 && (j != 0 && j != width)) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i][j - 1].getValue();
				String valueTwo = minesweeper[i + 1][j - 1].getValue();
				String valueThree = minesweeper[i + 1][j].getValue();
				String valueFour = minesweeper[i + 1][j + 1].getValue();
				String valueFive = minesweeper[i][j + 1].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
				if (valueFour.equals(MINE)) {
					amount++;

				}
				if (valueFive.equals(MINE)) {
					amount++;

				}
			}
		} else if (i == height && (j != 0 && j != width)) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i][j - 1].getValue();
				String valueTwo = minesweeper[i - 1][j - 1].getValue();
				String valueThree = minesweeper[i - 1][j].getValue();
				String valueFour = minesweeper[i - 1][j + 1].getValue();
				String valueFive = minesweeper[i][j + 1].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
				if (valueFour.equals(MINE)) {
					amount++;

				}
				if (valueFive.equals(MINE)) {
					amount++;

				}
			}
		} else if ((i != 0 && i != height) && j == 0) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i - 1][j].getValue();
				String valueTwo = minesweeper[i - 1][j + 1].getValue();
				String valueThree = minesweeper[i][j + 1].getValue();
				String valueFour = minesweeper[i + 1][j + 1].getValue();
				String valueFive = minesweeper[i + 1][j].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
				if (valueFour.equals(MINE)) {
					amount++;

				}
				if (valueFive.equals(MINE)) {
					amount++;

				}
			}
		} else if ((i != 0 && i != height) && j == width) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i - 1][j].getValue();
				String valueTwo = minesweeper[i - 1][j - 1].getValue();
				String valueThree = minesweeper[i][j - 1].getValue();
				String valueFour = minesweeper[i + 1][j - 1].getValue();
				String valueFive = minesweeper[i + 1][j].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
				if (valueFour.equals(MINE)) {
					amount++;

				}
				if (valueFive.equals(MINE)) {
					amount++;

				}
			}
		}
		// Finally we analyze the rest of zones

		else if ((i != 0 && i != height) && (j != 0 && j != width)) {
			if (minesweeper[i][j].getValue().equals(MINE)) {
				amount = -1;
			} else {

				String valueOne = minesweeper[i - 1][j - 1].getValue();
				String valueTwo = minesweeper[i - 1][j].getValue();
				String valueThree = minesweeper[i - 1][j + 1].getValue();
				String valueFour = minesweeper[i][j - 1].getValue();
				String valueFive = minesweeper[i][j + 1].getValue();
				String valueSix = minesweeper[i + 1][j - 1].getValue();
				String valueSeven = minesweeper[i + 1][j].getValue();
				String valueEight = minesweeper[i + 1][j + 1].getValue();

				if (valueOne.equals(MINE)) {
					amount++;

				}
				if (valueTwo.equals(MINE)) {
					amount++;

				}
				if (valueThree.equals(MINE)) {
					amount++;

				}
				if (valueFour.equals(MINE)) {
					amount++;

				}
				if (valueFive.equals(MINE)) {
					amount++;

				}
				if (valueSix.equals(MINE)) {
					amount++;

				}
				if (valueSeven.equals(MINE)) {
					amount++;

				}
				if (valueEight.equals(MINE)) {
					amount++;

				}
			}
		}
		return amount;
	}

	/**
	 * This is the logical representation of the movement made by the user, received
	 * by the view, handled by the main and managed by the controller. Whatever
	 * movement the user would made, this method will receive it and do what the
	 * user wants to do.
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param row  int of the position in X axis where the user wants to do
	 *             something
	 * @param col  int of the position in Y axis where the user wants to do
	 *             something
	 * @param move String of the movement in particular. It allows only "u or U" for
	 *             Uncover and "m or M" for mark
	 */
	public String makeMove(int row, int col, String move) {
		// TODO Auto-generated method stub

		String status = "";

		if (row <= rowsBoard || col <= colsBoard) {
			if (correctFlags == minesFromUser && wrongFlags == 0) {
				status = WON;
			} else {

				if (move.matches("[uU]")) {
					if (minesweeper[row - 1][col - 1].getValue().equals(MINE)) {
						status = LOSE;
					} else if (minesweeper[row - 1][col - 1].getValue().matches("[1-8]")) {
						String value = minesweeper[row - 1][col - 1].getValue();
						minesweeperCOPY[row - 1][col - 1].setValue(value);
						status = TRYING;
					} else if (minesweeper[row - 1][col - 1].getValue().equals(DISABLE_CELL)) {
						String value = minesweeper[row - 1][col - 1].getValue();
						minesweeperCOPY[row - 1][col - 1].setValue(value);
						status = WHITE;
					}

				} else if (move.matches("[mM]")) {
					if (minesweeper[row - 1][col - 1].getValue().equals(MINE)) {
						if (!minesweeperCOPY[row - 1][col - 1].getValue().equals(FLAG)) {
							minesweeperCOPY[row - 1][col - 1].setValue(FLAG);
							correctFlags++;
							if (validateWinning()) {
								status = WON;
							} else {
								status = TRYING;
							}
						} else {
							correctFlags--;
							minesweeperCOPY[row - 1][col - 1].setValue(DOT);
							status = REMOVE;
						}

					} else if (!minesweeper[row - 1][col - 1].getValue().equals(MINE)) {
						if (!minesweeperCOPY[row - 1][col - 1].getValue().equals(FLAG)) {
							minesweeperCOPY[row - 1][col - 1].setValue(FLAG);
							wrongFlags++;
							status = TRYING;
						} else {
							wrongFlags--;
							minesweeperCOPY[row - 1][col - 1].setValue(DOT);
							status = REMOVE;
						}
					}
				}

			}
		}
		return status;

	}

	/**
	 * This method validates if the user has completed the game successfully. (i.e.
	 * same amount of flags in the correct position and same amount of mines marked)
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 */
	private boolean validateWinning() {
		if (minesFromUser == correctFlags && wrongFlags == 0) {
			return true;
		} else {
			return false;
		}
	}

	// Getter's and setter's

	public int getMinesFromUser() {
		return minesFromUser;
	}

	public int getCorrectFlags() {
		return correctFlags;
	}

	public void setFlagsPerUser(int correctFlags) {
		this.correctFlags = correctFlags;
	}

	public int getRowsBoard() {
		return rowsBoard;
	}

	public int getColumnsBoard() {
		return colsBoard;
	}

	public Cell[][] getMinesweeper() {
		return minesweeper;
	}

	public Cell[][] getMinesweeperCOPY() {
		return minesweeperCOPY;
	}

}
