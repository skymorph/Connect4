package controller;

import gui.GUI;
import players.Computer;
import players.RealPlayer;

public class GameLoop {

	private Controller game;
	RealPlayer player;
	Computer cpuPlayer;

	public GameLoop() {

		this.player = new RealPlayer();
		this.cpuPlayer = new Computer();

		game = new Controller(player, cpuPlayer, 6, 7);
		game.chooseLevel();
		cpuPlayer = game.cpuPlayer;
		cpuPlayer.cont = game;
		cpuPlayer.initialise();
		new GUI(player, cpuPlayer, game.isIs1playing(), game, 6, 7);
	}

}
