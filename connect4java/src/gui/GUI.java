package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import model.Board;
import players.Computer;
import players.RealPlayer;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private Container con;

	private Controller game;

	RealPlayer player;
	Computer cpuPlayer;

	int rows;
	int columns;

	int windowWidth = 750;
	int windowHeight = 650;

	// Prepare ImageIcons to be used with JComponents
	private ImageIcon iconEmpty = null;
	private ImageIcon iconRed = null;
	private ImageIcon iconYellow = null;

	private final String title = "Connect 4 - ";

	private void gameUpdater(JButton button) {

		int col;

		boolean realPlayerTurn = game.isIs1playing();
		// inversion
		if (realPlayerTurn) {
			setTitle(title + "Yellow");
			col = player.makeMove(button);
		}

		else {
			setTitle(title + "Red");

			col = cpuPlayer.makeMove();
		}

		int addedRow = game.round(col);

		if (addedRow != -1) {
			JButton buttonToUpdate = ((JButton) (con.getComponent(columns * addedRow + col)));

			if (!realPlayerTurn)
				buttonToUpdate.setIcon(iconYellow);

			else
				buttonToUpdate.setIcon(iconRed);

			// check for winner
			if (game.checkForWinnerInGUI(col)) {
				if (realPlayerTurn) {
					JOptionPane.showMessageDialog(null, "You have won!");
				} else
					JOptionPane.showMessageDialog(null, "You have lost!");

				int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null,
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					System.out.println("Trying to play again...");
					game.reset(6, 7);
					resetBoard();
				} else {
					System.exit(0);
				}
			}
		} else {

			JOptionPane.showMessageDialog(null, "Please select a valid position.");

		}
	}

	public void resetBoard() {
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < columns; col++)
				((JButton) (con.getComponent(columns * row + col))).setIcon(iconEmpty);
	}

	/** Constructor to setup the GUI */
	public GUI(RealPlayer player, Computer cpuPlayer, boolean player1turn, Controller game, int rows, int columns) {
		this.game = game;
		this.rows = rows;
		this.columns = columns;
		this.player = player;
		this.cpuPlayer = cpuPlayer;

		// Prepare Icons
		// Image path relative to the project root (i.e., bin)
		String imgEmptyFilename = "images/empty.png";
		URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
		if (imgURL != null)
			iconEmpty = new ImageIcon(imgURL);
		else
			System.err.println("Couldn't find file: " + imgEmptyFilename);

		String imgRedFilename = "images/red.png";
		imgURL = getClass().getClassLoader().getResource(imgRedFilename);
		if (imgURL != null)
			iconRed = new ImageIcon(imgURL);
		else
			System.err.println("Couldn't find file: " + imgRedFilename);

		String imgYellowFilename = "images/yellow.png";
		imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
		if (imgURL != null)
			iconYellow = new ImageIcon(imgURL);
		else
			System.err.println("Couldn't find file: " + imgYellowFilename);

		con = getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				JButton button = new JButton();
				button.setIcon(iconEmpty);
				button.setPreferredSize(new Dimension(100, 100));

				button.setName(Integer.toString((row * 10 + col)));

				button.addActionListener(actionEvent -> {

					gameUpdater(((JButton) (actionEvent.getSource())));

				});
				con.add(button);
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (!player1turn)
			setTitle(title + "Yellow");
		else
			setTitle(title + "Red");
		setLocationRelativeTo(null); // center window on the screen
		setSize(windowWidth, windowHeight);
		setVisible(true);
	}
}