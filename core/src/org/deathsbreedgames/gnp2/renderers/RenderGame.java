package org.deathsbreedgames.gnp2.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import org.deathsbreedgames.gnp2.screens.GameScreen;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class renders all the graphics for the
 * GameScreen class. This way, the GameScreen class is more
 * clean, and therefore easier to read.
 * 
 */
public class RenderGame {
	// The screen that we get in the constructor
	private GameScreen screen;
	// The drawing SpriteBatch
	private SpriteBatch batch;
	
	private BitmapFont scoreFont;
	private BitmapFont pausedFont;

	// Image variables
	private Texture bg;
	private Texture pBlue;
	private Texture pRed;
	private Texture pGreen;
	private Texture pPurple;
	private Texture ball;

	public RenderGame(GameScreen screen) {
		this.screen = screen;
		batch = new SpriteBatch();
		
		
		
		scoreFont = new BitmapFont();
		pausedFont = new BitmapFont();

		pausedFont.scale(1.5f);

		bg = new Texture(Gdx.files.internal("gfx/bg.jpg"));
		pBlue = new Texture(Gdx.files.internal("gfx/Paddle_blue.png"));
		pRed = new Texture(Gdx.files.internal("gfx/Paddle_red.png"));
		pGreen = new Texture(Gdx.files.internal("gfx/Paddle_green.png"));
		pPurple = new Texture(Gdx.files.internal("gfx/Paddle_purple.png"));
		ball = new Texture(Gdx.files.internal("gfx/Ball_orange.png"));
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0.0f, 0.0f);
		batch.draw(pBlue, screen.players[0].getX(), screen.players[0].getY());
		batch.draw(pRed, screen.players[1].getX(), screen.players[1].getY());
		batch.draw(pGreen, screen.players[2].getX(), screen.players[2].getY());
		batch.draw(pPurple, screen.players[3].getX(), screen.players[3].getY());
		batch.draw(ball, screen.ball.getX(), screen.ball.getY());
		
		scoreFont.setColor(0.0f, 0.0f, 1.0f, 1.0f);
		scoreFont.draw(batch, "" + screen.scores[0], 10, 460);
		scoreFont.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		scoreFont.draw(batch, "" + screen.scores[1], 480, 45);
		scoreFont.setColor(0.0f, 1.0f, 0.0f, 1.0f);
		scoreFont.draw(batch, "" + screen.scores[2], 40, 490);
		scoreFont.setColor(1.0f, 0.0f, 1.0f, 1.0f);
		scoreFont.draw(batch, "" + screen.scores[3], 455, 25);
		if(!screen.ball.start) {
			scoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			String timeLeft;
			if(screen.ball.timer > 60) { timeLeft = "3"; }
			else if(screen.ball.timer > 30) { timeLeft = "2"; }
			else { timeLeft = "1"; }
			scoreFont.draw(batch, timeLeft, 245, 255);
		}
		batch.end();
	}

	public void renderPaused() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		pausedFont.draw(batch, "PAUSED", 175, 250);
		batch.end();
		
		buttonStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
		buttonStage.draw();
	}
	
	private boolean getSoundOn() { return screen.getSoundOn(); }
	private boolean getMusicOn() { return screen.getMusicOn(); }
	
	private void setSoundOn() { screen.setSoundOn(); }
	private void setSoundOff() { screen.setSoundOff(); }
	private void setMusicOn() { screen.setMusicOn(); }
	private void setMusicOff() { screen.setMusicOff(); }

	public void dispose() {
		bg.dispose();
		pBlue.dispose();
		pRed.dispose();
		pGreen.dispose();
		pPurple.dispose();
		ball.dispose();
		batch.dispose();
	}
}
