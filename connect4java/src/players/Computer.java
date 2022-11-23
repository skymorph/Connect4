package players;

import java.util.Random;

import controller.Controller;
import model.Piece;

public class Computer extends Players {

	public Controller cont;

	String color = "Yellow";

	Piece[][] ourBoard;

	@Override
	public int makeMove() {

		return makeMove();

	}

	public String getColor() {
		return color;
	}

	public void initialise() {
		ourBoard = cont.board.getOurBoard();
	}
	
	public int randomMove() {
		return new Random().nextInt(6 - 0 + 1) + 0;
	}

}
