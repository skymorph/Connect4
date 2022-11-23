package players;

public class HardCPU extends Computer {

	@Override
	public int makeMove() {
		
		/**
		 * I did want to make an AI for the hard and medium levels, I looked into minmax,
		 * but ultimately i did not have the time to try and figure it out properly.
		 * i am going to leave this code here even though it doesn't do anything other then show
		 * how was going to look into creating the AI. but i figured there is no point in just deleting it and it just making a random move
		 */

		String message = "";
		int bestMove;

		for (int i = 0; i < ourBoard.length; i++) {
			message = "row " + i + " , ";
			for (int j = 0; j < ourBoard[i].length; j++) {

				message += "col " + j + " = ";
				String result;
				// is spot available
				if (ourBoard[i][j] == null) {
					result = "W | ";
				} else {
					if (ourBoard[i][j].getColor() == "Yellow") {
						result = "Y | ";
					} else {
						result = "R | ";
					}
				}
				message += result;
			}
			System.out.println(message);
		}

		return randomMove();

	}
	
	private void minimax() {
		
	}

}
