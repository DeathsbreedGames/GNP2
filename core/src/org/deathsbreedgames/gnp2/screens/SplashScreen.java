package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.deathsbreedgames.gnp2.Game;

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
	private Game game;
	private SpriteBatch batch;
	private Texture splashTexture;
	private BitmapFont font;
	
	private int a;
	private boolean fadeUp = true;
	
	public SplashScreen(Game game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		splashTexture = new Texture("gfx/DeathsbreedGames_Production.png");
		a = 1;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.setColor(1.0f, 1.0f, 1.0f, ((float)a)/100);
		font.setColor(1.0f, 1.0f, 1.0f, ((float)a)/100);
		font.draw(batch, "A", 30, 300);
		batch.draw(splashTexture, 12.5f, 200, 475, 100);
		font.draw(batch, "Production", 300, 200);
		batch.end();
		
		if(fadeUp) { a++; }
		else { a--; }
		
		if(a == 100) { fadeUp = false; }
		if(a == 0) { game.setScreen(new MainMenuScreen(game)); }
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		splashTexture.dispose();
		font.dispose();
	}
}
