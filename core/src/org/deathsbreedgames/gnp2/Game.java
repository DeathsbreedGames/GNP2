package org.deathsbreedgames.gnp2;

import com.badlogic.gdx.Screen;


import org.deathsbreedgames.gnp2.screens.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This is the starting class. All it's good for
 * is starting the game.
 * 
 */
public class Game extends com.badlogic.gdx.Game {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static boolean soundOn = true;
	public static boolean musicOn = true;

	@Override
	public void create() { setScreen(new SplashScreen(this)); }
	
	@Override
	public void dispose() { super.dispose(); }
}
