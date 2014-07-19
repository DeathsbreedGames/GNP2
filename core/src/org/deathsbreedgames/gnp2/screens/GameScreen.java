package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;

import org.deathsbreedgames.gnp2.renderers.RenderGame;
import org.deathsbreedgames.gnp2.entities.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This is where the game will be run from (not
 * the menu or anything else, just the game).
 * 
 */
public class GameScreen extends BaseScreen {
	// The renderer class
	private RenderGame renderer;
	private Game game;
	private boolean paused = false;

	// The array of players
	public Paddle[] players = new Paddle[4];
	// The ball
	public Ball ball;

	// Scoring system
	private int ballLastTouch = -1;
	public int[] scores = new int[4];

	// Constructor
	public GameScreen(Game game) {
		this.game = game;
		renderer = new RenderGame(this);

		players[0] = new Paddle(10.0f, 175.0f, true, false);
		players[1] = new Paddle(440.0f, 175.0f, true, false);
		players[2] = new Paddle(175.0f, 440.0f, false, false);
		players[3] = new Paddle(175.0f, 10.0f, false, false);
		ball = new Ball(225, 225);

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
		if(!players[2].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.B)) { players[2].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.V)) { players[2].moveNeg(); }
		}
		if(!players[3].getAI()) {
			if(Gdx.input.isKeyPressed(Input.Keys.PERIOD)) { players[3].movePos(); }
			if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) { players[3].moveNeg(); }
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
			ballLastTouch = 0;
		} else if(ball.getBounds().overlaps(players[1].getBounds())) {
			float newAngle = (((players[1].getY() + 75) - (ball.getY() + 25) + 60) * 180 / 120) + 90;
			ball.setMoveAngle(newAngle);
			ball.setX(players[1].getX() - 26);
			ballLastTouch = 1;
		}

		if(ball.getBounds().overlaps(players[2].getBounds())) {
			float newAngle = (((ball.getX() + 25) - (players[2].getX() + 75) + 60) * 180 / 120) + 180;
			ball.setMoveAngle(newAngle);
			ball.setY(players[2].getY() - 26);
			ballLastTouch = 2;
		} else if(ball.getBounds().overlaps(players[3].getBounds())) {
			float newAngle = (((players[3].getX() + 75) - (ball.getX() + 25) + 60) * 180 / 120);
			ball.setMoveAngle(newAngle);
			ball.setY(players[3].getY() + 20);
			ballLastTouch = 3;
		}

		if(ball.getX() <= -50) {
			if(ballLastTouch == -1 || ballLastTouch == 0) { scores[0]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball = new Ball(225, 225);
		} else if(ball.getX() >= 500) {
			if(ballLastTouch == -1 || ballLastTouch == 1) { scores[1]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball = new Ball(225, 225);
		} else if(ball.getY() >= 500) {
			if(ballLastTouch == -1 || ballLastTouch == 2) { scores[2]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball = new Ball(225, 225);
		} else if(ball.getY() <= -50) {
			if(ballLastTouch == -1 || ballLastTouch == 3) { scores[3]--; }
			else { scores[ballLastTouch]++; }
			ballLastTouch = -1;
			ball = new Ball(225, 225);
		}

		if(!paused) {
			// Draw everything on the screen
			renderer.render();
		} else {
			renderer.renderPaused();
		}
	}
}