package controller;

import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import model.Board;
import model.Piece;
import players.Computer;
import players.EasyCPU;
import players.HardCPU;
import players.MediumCPU;
import players.RealPlayer;

public class Controller {
	public Board board;

	RealPlayer player;
	Computer cpuPlayer;

	// true if player's turn
	private boolean is1playing;

	public Controller(RealPlayer player, Computer cpuPlayer, int rows, int columns) {
		super();
		this.player = player;
		this.cpuPlayer = cpuPlayer;

		this.board = new Board(rows, columns);
		is1playing = (new Random()).nextBoolean();

	}

	public boolean isIs1playing() {

		return is1playing;
	}

	Object[] options = { "EASY", "NORMAL", "HARD" };

	int selectedLevel = JOptionPane.showOptionDialog(null, "choose your level ",

			"Choose Difficulty", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
			options[0]);

	public void chooseLevel() {
		if (selectedLevel == JOptionPane.YES_OPTION) {
			cpuPlayer = new EasyCPU();
		} else if (selectedLevel == JOptionPane.NO_OPTION) {
			cpuPlayer = new MediumCPU();
		} else if (selectedLevel == JOptionPane.CANCEL_OPTION) {
			cpuPlayer = new HardCPU();
		} else {
			System.exit(0);
		}
	}

	/**
	 *
	 * @param col which column did the user try to add a piece to
	 * @return -1: if unsuccessful an int of which row it was added to if successful
	 */
	public int round(int col) {

		String color = is1playing ? player.getColor() : cpuPlayer.getColor();

		int row = board.addPiece(col, color);

		if (row != -1)
			is1playing = !is1playing;

		return row;
	}

	public boolean checkForWinnerInGUI(int column) {
		String winningColor;

		// inversion because of late information
		if (!is1playing) {
			winningColor = player.getColor();
		} else {
			winningColor = cpuPlayer.getColor();
		}

		return checkForWinner(column, winningColor);
	}

	private boolean checkDiagonal(int row, int col, String winningColor, boolean rightDiagonal) {
		int winningStreak = 4;
		int reverser = rightDiagonal ? 1 : -1;
		int rows = board.getRows();
		int columns = board.getColumns();
		Piece[][] ourBoard = board.getOurBoard();

		for (int winRow = row - 3, winCol = col - (3 * reverser); winRow <= row + 3; winRow++, winCol += reverser) {
			if (!rightDiagonal) {
				if (winRow < 0 || winCol < 0)
					continue;
				if (winRow >= rows || winCol >= columns)
					continue;
			} else {
				if (winRow < 0 || winCol >= columns)
					continue;
				if (winRow >= rows || winCol < 0)
					continue;
			}

			if (ourBoard[winRow][winCol] != null && ourBoard[winRow][winCol].getColor().equals(winningColor)) {
				winningStreak--;

				if (winningStreak == 0)
					return true;
			} else
				winningStreak = 4;
		}
		return false;
	}

	public boolean checkForWinner(int col, String winningColor) {
		int rows = board.getRows();

		int columns = board.getColumns();
		Piece[][] ourBoard = board.getOurBoard();

		for (int row = 0; row < rows; row++) {
			if (ourBoard[row][col] != null) {
				// if this reaches 0, we have won
				int winningStreak = 3;

				// check downwards
				for (int winRow = row + 1; winRow < rows; winRow++) {
					if (ourBoard[winRow][col].getColor().equals(winningColor)) {
						winningStreak--;
						if (winningStreak == 0)
							return true;
					} else
						winningStreak = 4;
				}

				winningStreak = 4;
				// look at the horizontal
				for (int winCol = col - 3; winCol <= col + 3; winCol++) {
					if (winCol < 0)
						continue;
					if (winCol >= columns)
						break;

					if (ourBoard[row][winCol] != null && ourBoard[row][winCol].getColor().equals(winningColor)) {
						winningStreak--;
						if (winningStreak == 0)
							return true;
					} else
						winningStreak = 4;
				}

				if (checkDiagonal(row, col, winningColor, false))
					return true;
				if (checkDiagonal(row, col, winningColor, true))
					return true;

				return false;
			}
		}
		return false;
	}

	public void reset(int rows, int columns) {
		this.board = new Board(rows, columns);
		is1playing = (new Random()).nextBoolean();
	}
}
