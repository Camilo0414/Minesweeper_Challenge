package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import gameboardController.GameBoardController;
import gameboardViewer.GameBoardView;

public class Main {

	// Attributes of the Game board
	private static int height;
	private static int width;
	private static int mines;
	private static GameBoardView boardView;
	private static GameBoardController boardController;

	// Attributes of the Main class
	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

	/**
	 * Main function of the application
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param String[] args arguments of the system
	 */
	public static void main(String[] args) {

		boardView = new GameBoardView();
		boardController = new GameBoardController();

		try {
			bufferedWriter.write("HELLO ZEPP, I WANT TO PLAY A GAME \n");

			bufferedWriter.write("\n");
			bufferedWriter.write("You've been walking around your entire life \n"
					+ "without looking the damage you could make to someone else, now is the moment to purify your sins.");
			bufferedWriter.write("\n");
			bufferedWriter.write("This game is Called Minesweeper and it's more difficult than the Windows 98's one \n"
					+ "so, let's put the things straight:");
			bufferedWriter.write("\n");
			bufferedWriter.write("------------------------------------");
			bufferedWriter.write("\n");
			bufferedWriter.write(
					"First, you'll have to give me the size of the game board, separated by comma: height, width "
							+ "and of course, the amount of mines.\nPlease, be generous with the mines. By the way, the size 0,0 is not able for the board.\n");
			bufferedWriter.write("\n");
			bufferedWriter.write(
					"Second, when the board appears you'll be able to give me the coordenades where you want to play. \n"
							+ "These coordinates must be within the size you gave me before, so avoid giving me '5,4' if the size is until 4x4\n"
							+ "also, you'll have to put U (uncover) or M (mark) depending on the action that you want for each cell.\n");
			bufferedWriter.write("\n");
			bufferedWriter.write("The Lannisters send their regards...\n");
			bufferedWriter.flush();

			while (true) {

				bufferedWriter.write("Please enter the size of your Minesweeper and the amount of mines to start: \n");
				bufferedWriter.flush();

				String input = bufferedReader.readLine().toString();
				String[] splitByComma = input.split(",");
				if (input.contains("0,0")) {
					bufferedWriter.write("No zeros available");
					bufferedWriter.flush();
					break;
				}
				if (validationFirstEntry(input) == true) {

					height = Integer.parseInt(splitByComma[0]);
					width = Integer.parseInt(splitByComma[1]);
					mines = Integer.parseInt(splitByComma[2]);

					if (mines <= height * width) {
						String gBString = boardView.startGame(height, width);
						boardController.startGame(height, width, mines);
						bufferedWriter.write(gBString);
						bufferedWriter.flush();
					} else {
						bufferedWriter
								.write("The amount of mines have to be, at least, equal to the quantity of cells");
						bufferedWriter.flush();
					}
					makeMove();

				}
				bufferedWriter.write("\n");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Function that receives a movement from the user and depending on what
	 * movement is, then makes something in the game
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 */
	private static void makeMove() {
		// TODO Auto-generated method stub
		String gameStatus = "";

		while (!gameStatus.equals("YOU'VE SURVIVE")) {
			try {
				bufferedWriter.write("Make your move, wisely:\n ");
				bufferedWriter.flush();

				String input = bufferedReader.readLine().toString();
				String[] split = input.split(",");
				if (validationSecondEntry(input)) {
					gameStatus = boardController.makeMove(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
							split[2]);

					if (gameStatus.equals("REMOVE FLAG")) {
						String gbStatus = boardView.removeFlag(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
								split[2], height, width);
						bufferedWriter.write(gbStatus);
						bufferedWriter.flush();
					}
					if (gameStatus.equals("ON IT")) {
						String gbStatus = boardView.refreshMap(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
								split[2], boardController.getMinesweeperCOPY(), height, width);
						bufferedWriter.write(gbStatus);
						bufferedWriter.flush();
					}

					if (gameStatus.equals("YOU LOSE")) {
						bufferedWriter.write("You lose. Nice try the next time");
						bufferedWriter.flush();
						break;
					}
					if (gameStatus.equals("WHITE CELLS")) {

						String gbStatus = boardView.calculateWhites(Integer.parseInt(split[0]),
								Integer.parseInt(split[1]), boardController.getMinesweeperCOPY(), height, width);
						bufferedWriter.write(gbStatus);
						bufferedWriter.flush();
					}
					if (gameStatus.equals("YOU'VE SURVIVE")) {
						bufferedWriter.write("You won, just this time\n");
						bufferedWriter.flush();
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * Method that validates if the input typed by the User is valid, in the first
	 * entry that the game asks.
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param validated String typed by the user of the software
	 * @return boolean saying TRUE if the String is valid or FALSE in the other case
	 */
	private static boolean validationFirstEntry(String validated) {
		try {
			if (validated == null) {

				bufferedWriter.write("The numbers must NOT be null\n");
				bufferedWriter.flush();
				return false;
			}
			if (validated.trim().isEmpty()) {
				bufferedWriter.write("Please type some numbers\n");
				bufferedWriter.flush();
				return false;
			}

			String[] split = validated.split(",");
			int size = split.length;
			if (size == 0) {
				bufferedWriter.write("Please type some numbers\n");
				bufferedWriter.flush();
				return false;
			}
			if (size < 3) {
				bufferedWriter.write("At least 3 numbers please...\n");
				bufferedWriter.flush();
				return false;
			}
			for (int i = 0; i < split.length; i++) {
				if (!split[i].matches("[0-9]+")) {
					bufferedWriter.write("Nice try, smarty pants. Please put some NUMBERS\n");
					bufferedWriter.flush();
					return false;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Method that validates if the input typed by the User is valid, in the second
	 * or next entry that the game asks.
	 * 
	 * @author Juan Camilo Ibáñez Nieto
	 * @param validated String typed by the user of the software
	 * @return boolean saying TRUE if the String is valid or FALSE in the other case
	 */
	private static boolean validationSecondEntry(String input) {
		// TODO Auto-generated method stub
		try {
			if (input == null) {

				bufferedWriter.write("The numbers must NOT be null\n");

				bufferedWriter.flush();
				return false;
			}
			if (input.trim().isEmpty()) {
				bufferedWriter.write("Please type some numbers\n");
				bufferedWriter.flush();
				return false;
			}

			String[] split = input.split(",");
			int size = split.length;
			if (size == 0) {
				bufferedWriter.write("Please type some numbers\n");
				bufferedWriter.flush();
				return false;
			}
			if (size < 3) {
				bufferedWriter.write("At least 3 chars please...\n");
				bufferedWriter.flush();
				return false;
			}
			if (!split[2].matches("[UMum]")) {
				bufferedWriter.write("This are not the valid chars to play\n");
				bufferedWriter.flush();
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
