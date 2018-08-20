package gameboardViewer;

import gameboardController.Cell;

/**
 * Class viewer package where you can handle all the aspects related with the
 * view of the game
 * 
 * @author Juan Camilo Ibáñez Nieto
 */
public class GameBoardView {

	// Constants from the class

	private final static String DOT = ".";
	private final static String DISABLE_CELL = "_";
	private final static String FLAG = "P";

	// Attribute from the class
	private String gameboard;

	// Methods from the class

	/**
	 * Method that starts the board, as the user will be see it
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param height int of the height of the board
	 * @param width  int of the width of the board
	 * @param mines  int of the mines in the board
	 * @return String String that shows the first view of the board waiting until
	 *         the user wants to play
	 */
	public String startGame(int height, int width) {
		gameboard = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (j == (width - 1)) {
					gameboard += DOT + "\n";

				} else {
					gameboard += DOT.concat(" ");
				}
			}
		}

		return gameboard;
	}

	/**
	 * Method that refreshes the map after a move received from the user
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param row    int of the position in the X axis of the matrix
	 * @param col    int of the position in the X axis of the matrix
	 * @param move   String of the move made by the user
	 * @param board  Cell[][] of the actual condition of the board
	 * @param height int height of the board
	 * @param width  int width of the board
	 * @return String String that shows the updated version of the board
	 */
	public String refreshMap(int row, int col, String move, Cell[][] board, int height, int width) {
		// TODO Auto-generated method stub
		String gameboardNew = "";

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if (i == row - 1 && j == col - 1) {
					if (move.matches("[uU]")) {
						if (board[i][j].getValue().matches("[1-8]")) {
							if (j == width - 1) {
								gameboardNew += board[i][j].getValue() + "\n";

							} else {
								gameboardNew += board[i][j].getValue() + " ";
							}
						}
					} else if (move.matches("[mM]")) {
						if (j == width - 1) {
							gameboardNew += FLAG + ("\n");
						} else {
							gameboardNew += FLAG.concat(" ");
						}
					}
				} else if (j == width - 1) {
					gameboardNew += DOT + "\n";
				} else {
					gameboardNew += DOT.concat(" ");
				}
			}

		}
		char[] oldStatus = gameboard.toCharArray();
		char[] newStatus = gameboardNew.toCharArray();

		for (int i = 0; i < newStatus.length; i++) {
			if (oldStatus[i] != newStatus[i] && newStatus[i] != '.') {
				char temp = newStatus[i];
				oldStatus[i] = temp;
			}
		}

		gameboard = new String(oldStatus);

		return gameboard;

	}

	/**
	 * Method that removes a flag from the map. This only will happend if the flag
	 * is already in the map.
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param row    int of the position in the X axis of the matrix
	 * @param col    int of the position in the X axis of the matrix
	 * @param move   String of the move made by the user
	 * @param height int height of the board
	 * @param width  int width of the board
	 * @return String String that shows the updated version of the board
	 */
	public String removeFlag(int row, int col, String move, int height, int width) {
		// TODO Auto-generated method stub
		String gameboardNew = "";

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if (i == row - 1 && j == col - 1) {
					if (move.matches("[mM]")) {
						if (j == width - 1) {
							gameboardNew += DOT + "\n";
						} else {
							gameboardNew += DOT.concat(" ");
						}
					}
				} else if (j == width - 1) {
					gameboardNew += ",\n";
				} else {
					gameboardNew += "," + " ";
				}
			}

		}
		char[] oldStatus = gameboard.toCharArray();
		char[] newStatus = gameboardNew.toCharArray();

		for (int i = 0; i < newStatus.length; i++) {
			if (oldStatus[i] != newStatus[i] && newStatus[i] == '.') {
				char temp = newStatus[i];
				oldStatus[i] = temp;
			}
		}

		gameboard = new String(oldStatus);

		return gameboard;

	}

	/**
	 * Method that calculates the white spaces (i.e. the spots where is no mine or
	 * number)
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param row    int of the position in the X axis of the matrix
	 * @param col    int of the position in the X axis of the matrix
	 * @param move   String of the move made by the user
	 * @param board  Cell[][] of the actual condition of the board
	 * @param height int height of the board
	 * @param width  int width of the board
	 * @return String String that shows the updated version of the board, after
	 *         being refreshed in the method paintWhites()
	 */
	public String calculateWhites(int row, int col, Cell[][] board, int height, int width) {

		String pos = "";
		String[] split = null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (board[i][j].getValue().equals(DISABLE_CELL)) {
					String pos1 = i + "";
					String pos2 = j + "";
					pos += pos1 + "," + pos2 + ",";
					split = pos.split(",");

				}
			}

		}

		gameboard = paintWhites(row, col, split, height, width);

		return gameboard;
	}

	/**
	 * Method that paints in the map, the white spots or DISABLE_CELL, dependind the
	 * case of the game.
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param row    int of the position in the X axis of the matrix
	 * @param col    int of the position in the X axis of the matrix
	 * @param split  String[] of the actual condition of the board
	 * @param height int height of the board
	 * @param width  int width of the board
	 * @return String String that shows the updated version of the board
	 */
	private String paintWhites(int row, int col, String[] split, int height, int width) {
		String gameboardNew = "";

		int i = 0;
		for (int j = 0; j < height; j++) {

			for (int k = 0; k < width && i < split.length; k++) {
				if (j == Integer.parseInt(split[i]) && k == Integer.parseInt(split[i + 1])) {
					if (k == width - 1) {
						gameboardNew += DISABLE_CELL + "\n";
					} else {
						gameboardNew += DISABLE_CELL.concat(" ");
					}
					i += 2;
				} else if (k == width - 1) {

					gameboardNew += DOT + "\n";
				} else {
					gameboardNew += DOT.concat(" ");
				}

			}
		}

		char[] oldStatus = gameboard.toCharArray();
		char[] newStatus = gameboardNew.toCharArray();

		for (int l = 0; l < newStatus.length; l++) {
			if (oldStatus[l] != newStatus[l] && newStatus[l] != '.') {
				char temp = newStatus[l];
				oldStatus[l] = temp;
			}
		}

		return new String(oldStatus);

	}

	// Getter's and Setter's
	public String getGameboard() {
		return gameboard;
	}

}
