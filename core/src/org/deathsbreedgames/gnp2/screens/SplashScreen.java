package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This is simply the splash screen that appears
 * when the program is launched.
 * 
 */
public class SplashScreen extends BaseScreen {
	private SpriteBatch batch;
	private Texture splashTexture;
	private BitmapFont font;
	
	// 'a' stands for alpha, which means it is used for the fade effect
	private int a;
	private boolean fadeUp = true;
	
	// Constructor
	public SplashScreen() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		splashTexture = new Texture("gfx/DeathsbreedGames_Production.png");
		a = 1;
	}
	
	// Update
	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		/*
		 * The variable 'a' is an int because float variables are too
		 * inaccurate for the 'if' statements at the required to change
		 * screens.
		 */
		batch.setColor(1.0f, 1.0f, 1.0f, ((float)a)/100);
		font.setColor(1.0f, 1.0f, 1.0f, ((float)a)/100);
		font.draw(batch, "A", 30, 300);
		batch.draw(splashTexture, 12.5f, 200, 475, 100);
		font.draw(batch, "Production", 300, 200);
		batch.end();
		
		if(fadeUp) { a++; }
		else { a--; }
		
		if(a == 100) { fadeUp = false; }
		if(a == 0) { setDone(true); }
	}
	
	// Dispose
	@Override
	public void dispose() {
		batch.dispose();
		splashTexture.dispose();
		font.dispose();
	}
}
