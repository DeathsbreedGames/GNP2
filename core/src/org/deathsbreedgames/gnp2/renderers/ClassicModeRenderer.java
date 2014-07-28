package org.deathsbreedgames.gnp2.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import org.deathsbreedgames.gnp2.screens.ClassicModeScreen;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class renders all of the classic game mode
 * stuff onto the screen. This makes the code a little easier to
 * read by dividing it into different classes with set jobs.
 * 
 */
public class ClassicModeRenderer {
	private ClassicModeScreen screen;
	private SpriteBatch batch;
	
	private Stage buttonStage;
	
	private BitmapFont scoreFont;
	private BitmapFont pausedFont;
	
	private Texture bg;
	private Sprite pBlue = new Sprite();
	private Sprite pRed = new Sprite();
	private Sprite ball = new Sprite();
	
	public ClassicModeRenderer(ClassicModeScreen screen) {
		this.screen = screen;
		
		buttonStage = new Stage();
		TextureAtlas buttonAtlas = new TextureAtlas("gfx/buttons.pack");
		Skin buttonSkin = new Skin(buttonAtlas);
		
		Gdx.input.setInputProcessor(buttonStage);
		
		BitmapFont buttonFont = new BitmapFont();
		buttonFont.scale(0.1f);
		
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = buttonSkin.getDrawable("button_green");
		buttonStyle.down = buttonSkin.getDrawable("button_light_green");
		buttonStyle.checked = buttonSkin.getDrawable("button_red");
		buttonStyle.font = buttonFont;
		
		TextButton soundButton = new TextButton("S", buttonStyle);
		soundButton.pad(0, 10, 0, 10);
		soundButton.setPosition(Gdx.graphics.getWidth() - soundButton.getWidth(), Gdx.graphics.getHeight() - soundButton.getHeight());
		buttonStage.addActor(soundButton);
		soundButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(getSoundOn()) { setSoundOff(); }
				else { setSoundOn(); }
			}
		});
		
		TextButton musicButton = new TextButton("M", buttonStyle);
		musicButton.setPosition(soundButton.getX() - musicButton.getWidth(), Gdx.graphics.getHeight() - musicButton.getHeight());
		buttonStage.addActor(musicButton);
		musicButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(getMusicOn()) { setMusicOff(); }
				else { setMusicOn(); }
			}
		});
		
		
		// Setup drawing fonts
		scoreFont = new BitmapFont();
		pausedFont = new BitmapFont();
		pausedFont.scale(1.5f);
		
		
		// Setup graphics
		batch = new SpriteBatch();
		bg = new Texture(Gdx.files.internal("gfx/bg.jpg"));
		TextureAtlas spriteAtlas = new TextureAtlas("gfx/sprites.pack");
		pBlue = spriteAtlas.createSprite("Paddle_blue");
		pRed = spriteAtlas.createSprite("Paddle_red");
		ball = spriteAtlas.createSprite("Ball_orange");
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0.0f, 0.0f);
		batch.draw(pBlue, screen.players[0].getX(), screen.players[0].getY());
		batch.draw(pRed, screen.players[1].getX(), screen.players[1].getY());
		batch.draw(ball, screen.ball.getX(), screen.ball.getY());
		
		scoreFont.setColor(0.0f, 0.0f, 1.0f, 1.0f);
		scoreFont.draw(batch, "" + screen.scores[0], 10, 460);
		scoreFont.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		scoreFont.draw(batch, "" + screen.scores[1], 480, 460);
		if(!screen.ball.start) {
			scoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			String timeLeft;
			if(screen.ball.timer > 60) { timeLeft = "3"; }
			else if(screen.ball.timer > 30) { timeLeft = "2"; }
			else { timeLeft = "1"; }
			scoreFont.draw(batch, timeLeft, 245, 255);
		}
		batch.end();
		
		buttonStage.act(delta);
		buttonStage.draw();
	}
	
	public void renderPaused(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		pausedFont.draw(batch, "PAUSED", 175, 250);
		batch.end();
		
		buttonStage.act(delta);
		buttonStage.draw();
	}
	
	private boolean getSoundOn() { return screen.getSoundOn(); }
	private boolean getMusicOn() { return screen.getMusicOn(); }
	
	private void setSoundOn() { screen.setSoundOn(); }
	private void setSoundOff() { screen.setSoundOff(); }
	private void setMusicOn() { screen.setMusicOn(); }
	private void setMusicOff() { screen.setMusicOff(); }
	
	@Override
	public void dispose() {
		batch.dispose()
		buttonStage.dispose();
		scoreFont.dispose();
		pausedFont.dispose();
		bg.dispose();
	}
}
