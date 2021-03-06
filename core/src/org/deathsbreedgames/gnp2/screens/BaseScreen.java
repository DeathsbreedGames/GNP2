package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Screen;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU Affero GPLv3
 * @year 2014
 * 
 * Description: This class is a template class used to avoid
 * redundancy of methods that are not used. This way they are
 * only written once.
 * 
 */
public class BaseScreen implements Screen {
	// This variable indicates whether or not it is time to switch screens
	private boolean done = false;
	
	@Override
	public void render(float delta) { }

	@Override
	public void resize(int width, int height) { }

	@Override
	public void show() { }

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() { }
	
	// Getter/Setter methods:
	public boolean getDone() { return done; }
	public void setDone(boolean nDone) { this.done = nDone; }
}
