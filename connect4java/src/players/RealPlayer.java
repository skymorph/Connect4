package players;

import javax.swing.JButton;

public class RealPlayer extends Players {

	String color = "Red";

	public String getColor() {
		return color;
	}

	@Override
	public int makeMove(JButton button) {
		int row = Integer.parseInt(button.getName());

		return row % 10;

	}


}
