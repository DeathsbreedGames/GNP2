package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

import org.deathsbreedgames.gnp2.GlobalVars;
import org.deathsbreedgames.gnp2.entities.*;
import org.deathsbreedgames.gnp2.renderers.GroupModeRenderer;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU Affero GPLv3
 * @year 2014
 * 
 * Description: This is where the game will be run from (not
 * the menu or anything else, just the game).
 * 
 */
public class GroupModeScreen extends BaseScreen {
	// The renderer class used to draw everything
	private GroupModeRenderer renderer;
	
	// Pause variables
	private boolean paused = false;
	private boolean oldPausePressed = false;

	// Entities:
	public Paddle[] players = new Paddle[4];
	public Ball ball;

	// Audio:
	private Sound hit;
	private Sound score;
	private Music music;

	// Scoring system:
	private int ballLastTouch = -1;
	public int[] scores = new int[4];
	private int scoreUpTo = 10;

	// Constructor
	public GroupModeScreen(boolean[] aiPlayers) {
		// Create the renderer
		renderer = new GroupModeRenderer(this);
		
		// Create the entities:
		ball = new Ball(225, 225);
		players[0] = new Paddle(10.0f, 175.0f, true);
		players[1] = new Paddle(440.0f, 175.0f, true);
		players[2] = new Paddle(175.0f, 440.0f, false);
		players[3] = new Paddle(175.0f, 10.0f, false);
		
		// Set player's AI variable
		for(int i = 0; i < players.length; i++) { players[i].setAI(aiPlayers[i]); }
		
		// Create the audio
		hit = Gdx.audio.newSound(Gdx.files.internal("sfx/Pop.ogg"));
		score = Gdx.audio.newSound(Gdx.files.internal("sfx/Score.ogg"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sfx/The_Machines.ogg"));
		if(GlobalVars.musicOn) { music.play(); }
		music.setLooping(true);
		
		// Set all scores to 0 (just in case)
		for(int i = 0; i < scores.length; i++) { scores[i] = 0; }
	}
	
	// Update
	@Override
	public void render(float delta) {
		// Play or stop music accordingly
		if(GlobalVars.musicOn && !music.isPlaying()) { music.play(); }
		else if(!GlobalVars.musicOn && music.isPlaying()) { music.stop(); }
		
		// If a player is not AI check for user input
		if(!players[0].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.Q)) { players[0].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.A)) { players[0].moveNeg(); }
		}
		if(!players[1].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT_BRACKET)) { players[1].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) { players[1].moveNeg(); }
		}
		if(!players[2].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.V)) { players[2].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.C)) { players[2].moveNeg(); }
		}
		if(!players[3].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.M)) { players[3].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.N)) { players[3].moveNeg(); }
		}
		
		// If a player is AI do AI stuff
		for(int i = 0; i < players.length; i++) {
			if(players[i].getAI()) {
				if(players[i].getVertical()) {
					if(ball.getX() - players[i].getX() > -250 && ball.getX() - players[i].getX() < 250) {
						if(ball.getY() > players[i].getY() + 75) { players[i].setVelY(Paddle.MAXVEL); }
						else if(ball.getY() < players[i].getY() + 25) { players[i].setVelY(-Paddle.MAXVEL); }
					}
				} else {
					if(ball.getY() - players[i].getY() > -250 && ball.getY() - players[i].getY() < 250) {
						if(ball.getX() > players[i].getX() + 75) { players[i].setVelX(Paddle.MAXVEL); }
						else if(ball.getX() < players[i].getX() + 25) { players[i].setVelX(-Paddle.MAXVEL); }
					}
				}
			}
		}
		
		// If the game is not paused update the ball and the players
		if(!paused) {
			ball.update(delta);
			for(int i = 0; i < players.length; i++) {
				players[i].update(delta);
			}
		}
		
		// Paddle Rebound:
		if(ball.getBounds().overlaps(players[0].getBounds())) {
			float newAngle = (((ball.getY() + 25) - (players[0].getY() + 75)) * 180 / 170);
			ball.setMoveAngle(newAngle);
			ball.setX(players[0].getX() + 20);
			ballLastTouch = 0;
			if(GlobalVars.soundOn) hit.play(0.6f);
		} else if(ball.getBounds().overlaps(players[1].getBounds())) {
			float newAngle = (((players[1].getY() + 75) - (ball.getY() + 25)) * 180 / 170) + 180;
			ball.setMoveAngle(newAngle);
			ball.setX(players[1].getX() - 26);
			ballLastTouch = 1;
			if(GlobalVars.soundOn) hit.play(0.6f);
		}
		if(ball.getBounds().overlaps(players[2].getBounds())) {
			float newAngle = -((((players[2].getX() + 75) - (ball.getX() + 25)) * 180 / 170) + 90);
			ball.setMoveAngle(newAngle);
			ball.setY(players[2].getY() - 26);
			ballLastTouch = 2;
			if(GlobalVars.soundOn) hit.play(0.6f);
		} else if(ball.getBounds().overlaps(players[3].getBounds())) {
			float newAngle = (((players[3].getX() + 75) - (ball.getX() + 25)) * 180 / 170) + 90;
			ball.setMoveAngle(newAngle);
			ball.setY(players[3].getY() + 20);
			ballLastTouch = 3;
			if(GlobalVars.soundOn) hit.play(0.6f);
		}
		
		// Scoring:
		if(ball.getX() <= -50) {
			if(ballLastTouch == -1 || ballLastTouch == 0) { scores[0]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball.reset(225, 225, 0);
			if(GlobalVars.soundOn) score.play();
		} else if(ball.getX() >= 500) {
			if(ballLastTouch == -1 || ballLastTouch == 1) { scores[1]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball.reset(225, 225, 1);
			if(GlobalVars.soundOn) score.play();
		} else if(ball.getY() >= 500) {
			if(ballLastTouch == -1 || ballLastTouch == 2) { scores[2]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball.reset(225, 225, 2);
			if(GlobalVars.soundOn) score.play();
		} else if(ball.getY() <= -50) {
			if(ballLastTouch == -1 || ballLastTouch == 3) { scores[3]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball.reset(225, 225, 3);
			if(GlobalVars.soundOn) score.play();
		}
		
		// If someone won
		for(int i = 0; i < scores.length; i++) {
			if(scores[i] == scoreUpTo) {
				// Switch to the WinScreen
				GlobalVars.winner = i;
				setDone(true);
			}
		}

		if(!paused) {
			// Draw everything on the screen
			renderer.render(delta);
		} else {
			// Only draw paused stuff on the screen
			renderer.renderPaused(delta);
		}
		
		/*
		 * Follows is a complicated (not really) system of telling if
		 * the pause key was just pressed (to avoid endless 30 fps
		 * change between paused and unpaused.
		 */
		boolean newPausePressed = Gdx.input.isKeyPressed(Input.Keys.P);

		if(newPausePressed && !oldPausePressed) {
			if(paused) { paused = false; }
			else { paused = true; }
		}
		oldPausePressed = newPausePressed;
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			// Quit to the Main Menu
			if(music.isPlaying()) { music.stop(); }
			GlobalVars.winner = -1;
			setDone(true);
		}
	}
	
	// Dispose
	@Override
	public void dispose() {
		renderer.dispose();
		hit.dispose();
		score.dispose();
		music.dispose();
	}
}
