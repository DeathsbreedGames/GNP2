package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

import org.deathsbreedgames.gnp2.Game;
import org.deathsbreedgames.gnp2.entities.*;
import org.deathsbreedgames.gnp2.renderers.ClassicModeRenderer;

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
	
	private ClassicModeRenderer renderer;
	private boolean paused = false;
	private boolean oldPausePressed = false;
	
	// The array of paddles:
	public Paddle[] players = new Paddle[2];
	// Creating the ball object.
	public Ball ball;
	
	// Audio:
	private Sound hit;
	private Sound score;
	private Music music;
	
	// Scoring system:
	public int[] scores = new int[2];
	
	
	public ClassicModeScreen(Game game, boolean[] aiPlayers) {
		this.game = game;
		renderer = new ClassicModeRenderer(this);
		
		ball = new Ball(225, 225);
		players[0] = new Paddle(10.0f, 175.0f, true, ball);
		players[1] = new Paddle(440.0f, 175.0f, true, ball);
		
		for(int i = 0; i < players.length; i++) { players[i].setAI(aiPlayers[i]); }
		
		hit = Gdx.audio.newSound(Gdx.files.internal("sfx/Pop.ogg"));
		score = Gdx.audio.newSound(Gdx.files.internal("sfx/Score.ogg"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sfx/The_Machines.ogg"));
		if(game.musicOn) {
			music.play();
			music.setLooping(true);
		}
		
		for(int i = 0; i < scores.length; i++) { scores[i] = 0; }
	}
	
	@Override
	public void render(float delta) {
		if(!players[0].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.W)) { players[0].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.S)) { players[0].moveNeg(); }
		}
		if(!players[1].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) { players[1].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) { players[1].moveNeg(); }
		}
		
		if(!paused) {
			ball.update(delta);
			for(int i = 0; i < players.length; i++) {
				players[i].update(delta);
			}
		}
		
		if(ball.getBounds().overlaps(players[0].getBounds())) {
			float newAngle = -((((players[0].getY() + 75) - (ball.getY() + 25) + 60) * 180 / 120) - 90);
			ball.setMoveAngle(newAngle);
			ball.setX(players[0].getX() + 20);
			if(game.soundOn) hit.play(0.6f);
		} else if(ball.getBounds().overlaps(players[1].getBounds())) {
			float newAngle = (((players[1].getY() + 75) - (ball.getY() + 25) + 60) * 180 / 120) + 90;
			ball.setMoveAngle(newAngle);
			ball.setX(players[1].getX() - 26);
			if(game.soundOn) hit.play(0.6f);
		}
		
		if(ball.getX() <= -50) {
			scores[1]++;
			ball.reset(225, 225);
			if(game.soundOn) score.play();
		} else if(ball.getX() >= 500) {
			scores[0]++;
			ball.reset(225, 225);
			if(game.soundOn) score.play();
		} else if(ball.getY() <= 0 || ball.getY() >= 450) {
			ball.setMoveAngle(360 - ball.getMoveAngle());
		}
		
		if(!paused) {
			// Draw everything on the screen
			renderer.render(delta);
		} else {
			renderer.renderPaused(delta);
		}

		boolean newPausePressed = Gdx.input.isKeyPressed(Input.Keys.P);

		if(newPausePressed && !oldPausePressed) {
			if(paused) { paused = false; }
			else { paused = true; }
		}

		oldPausePressed = newPausePressed;

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			if(music.isPlaying()) { music.stop(); }
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
	public boolean getSoundOn() { return game.soundOn; }
	public boolean getMusicOn() { return game.musicOn; }
	
	public void setSoundOff() { game.soundOn = false; }
	public void setSoundOn() { game.soundOn = true; }
	public void setMusicOff() {
		game.musicOn = false;
		this.music.stop();
	}
	public void setMusicOn() {
		game.musicOn = true;
		this.music.play();
	}
}
