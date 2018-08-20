package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import gameboardController.GameBoardController;
import gameboardViewer.GameBoardView;

public class testMinesweeper {
	private GameBoardController boardController;
	private GameBoardView boardView;

	@Test
	public void testMatrix1x1() throws Exception {
		boardController = new GameBoardController();
		boardController.startGame(1, 1, 1);
		assertEquals(boardController.getRowsBoard(), 1);
		assertEquals(boardController.getColumnsBoard(), 1);
		assertEquals(boardController.getMinesFromUser(), 1);
	}

	@Test
	public void testMatrix10x10() throws Exception {
		boardController = new GameBoardController();
		boardController.startGame(10, 10, 25);
		assertEquals(boardController.getRowsBoard(), 10);
		assertEquals(boardController.getColumnsBoard(), 10);
		assertEquals(boardController.getMinesFromUser(), 25);

	}

	@Test
	public void testMatrix100x100() throws Exception {
		boardController = new GameBoardController();
		boardController.startGame(100, 100, 500);
		assertEquals(boardController.getRowsBoard(), 100);
		assertEquals(boardController.getColumnsBoard(), 100);
		assertEquals(boardController.getMinesFromUser(), 500);

	}
	
	@Test
	public void testMatrix1x1view() throws Exception {
		boardView = new GameBoardView();
		assertEquals(".\n", boardView.startGame(1, 1));
	}

	@Test
	public void testMatrix3x3view() throws Exception {
		boardView = new GameBoardView();

		assertEquals(	". . .\n" +
						". . .\n" +
						". . .\n", boardView.startGame(3, 3));

	}

	
	
	@Test
	public void testMatrix7x6view() throws Exception {
		boardView = new GameBoardView();

		assertEquals(	". . . . . .\n"+
						". . . . . .\n"+
						". . . . . .\n"+
						". . . . . .\n"+
						". . . . . .\n"+
						". . . . . .\n"+
						". . . . . .\n", boardView.startGame(7, 6));

	}
	
	@Test
	public void testMatrix10x20view() throws Exception {
		boardView = new GameBoardView();

		assertEquals(	". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n"+
						". . . . . . . . . . . . . . . . . . . .\n", boardView.startGame(10, 20));

	}

}
