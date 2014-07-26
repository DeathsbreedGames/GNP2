package org.deathsbreedgames.gnp2.screens;

import org.deathsbreedgames.gnp2.Game;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class is in charge of handling the gameplay
 * for the classic mode of the game. Only the gameplay though,
 * the rendering is done by another class.
 * 
 */
public class ClassicModeScreen extends BaseScreen {
	private Game game;
	
	public ClassicModeScreen(Game game, boolean[] aiPlayers) {
		this.game = game;
	}
}
