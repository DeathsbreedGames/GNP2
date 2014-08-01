package org.deathsbreedgames.gnp2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import org.deathsbreedgames.gnp2.screens.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU Affero GPLv3
 * @year 2014
 * 
 * Description: This is the starting class. All it's good for
 * is starting the game.
 * 
 */
public class Game extends com.badlogic.gdx.Game {
	// Set the screen
	@Override
	public void create() { setScreen(new SplashScreen()); }
	
	@Override
	public void render() {
		BaseScreen currentScreen = (BaseScreen) super.getScreen();
		
		// Run the render method of the current screen
		currentScreen.render(Gdx.graphics.getDeltaTime());
		
		// This is used so as to properly dispose of the screens
		if(currentScreen.getDone()) {
			if(currentScreen instanceof SplashScreen) { setScreen(new MainMenuScreen()); }
			else if(currentScreen instanceof MainMenuScreen) { setScreen(new SelectScreen()); }
			else if(currentScreen instanceof SelectScreen) {
				if(GlobalVars.currentGameMode == -1) { setScreen(new MainMenuScreen()); }
				else if(GlobalVars.currentGameMode == 0) { setScreen(new GroupModeScreen(GlobalVars.ai)); }
				else if(GlobalVars.currentGameMode == 1) { setScreen(new ClassicModeScreen(GlobalVars.ai)); }
			} else if(currentScreen instanceof GroupModeScreen || currentScreen instanceof ClassicModeScreen) {
				if(GlobalVars.winner == -1) { setScreen(new MainMenuScreen()); }
				else { setScreen(new WinScreen(GlobalVars.winner)); }
			} else if(currentScreen instanceof WinScreen) { setScreen(new MainMenuScreen()); }
		}
	}
	
	@Override
	public void dispose() {
		super.getScreen().dispose();
		super.dispose();
	}
}
