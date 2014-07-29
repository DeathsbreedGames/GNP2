package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

import org.deathsbreedgames.gnp2.GlobalVars;
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
	private int scoreUpTo = 10;
	
	
	public ClassicModeScreen(boolean[] aiPlayers) {
		renderer = new ClassicModeRenderer(this);
		
		ball = new Ball(225, 225);
		players[0] = new Paddle(10.0f, 175.0f, true, ball);
		players[1] = new Paddle(440.0f, 175.0f, true, ball);
		
		for(int i = 0; i < players.length; i++) { players[i].setAI(aiPlayers[i]); }
		
		hit = Gdx.audio.newSound(Gdx.files.internal("sfx/Pop.ogg"));
		score = Gdx.audio.newSound(Gdx.files.internal("sfx/Score.ogg"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sfx/The_Machines.ogg"));
		if(GlobalVars.soundOn) { music.play(); }
		music.setLooping(true);
		
		for(int i = 0; i < scores.length; i++) { scores[i] = 0; }
	}
	
	@Override
	public void render(float delta) {
		if(GlobalVars.musicOn && !music.isPlaying()) { music.play(); }
		else if(!GlobalVars.musicOn && music.isPlaying()) { music.stop(); }
		
		if(!players[0].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.W)) { players[0].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.S)) { players[0].moveNeg(); }
		}
		if(!players[1].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.APOSTROPHE)) { players[1].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.SLASH)) { players[1].moveNeg(); }
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
			if(GlobalVars.soundOn) hit.play(0.6f);
		} else if(ball.getBounds().overlaps(players[1].getBounds())) {
			float newAngle = (((players[1].getY() + 75) - (ball.getY() + 25) + 60) * 180 / 120) + 90;
			ball.setMoveAngle(newAngle);
			ball.setX(players[1].getX() - 26);
			if(GlobalVars.soundOn) hit.play(0.6f);
		}
		
		if(ball.getX() <= -50) {
			scores[1]++;
			ball.reset(225, 225);
			if(GlobalVars.soundOn) score.play();
		} else if(ball.getX() >= 500) {
			scores[0]++;
			ball.reset(225, 225);
			if(GlobalVars.soundOn) score.play();
		} else if(ball.getY() <= 0 || ball.getY() >= 450) {
			ball.setMoveAngle(360 - ball.getMoveAngle());
		}
		
		for(int i = 0; i < scores.length; i++) {
			if(scores[i] == scoreUpTo) {
				GlobalVars.winner = i;
				setDone(true);
			}
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
			GlobalVars.winner = -1;
			setDone(true);
		}
	}
	
	@Override
	public void dispose() {
		renderer.dispose();
		hit.dispose();
		score.dispose();
		music.dispose();
	}
}
