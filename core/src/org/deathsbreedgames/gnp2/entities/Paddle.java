package org.deathsbreedgames.gnp2.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class contains the essentials of the
 * paddle variables and methods.
 * 
 */
public class Paddle extends Entity {
	// Essential variables
	private final float MAXVEL = 5.0f;
	private boolean vertical;
	private boolean ai;
	private Ball ball;
	Rectangle bounds;

	// Constructor
	public Paddle(float x, float y, boolean vertical, Ball ball) {
		super(x, y);
		this.vertical = vertical;
		this.ai = ai;
		this.ball = ball;

		if(vertical) {
			setBounds(new Rectangle(getX() + 17, getY() + 17, 16.0f, 120.0f));
		} else {
			setBounds(new Rectangle(getX() + 17, getY() + 17, 120.0f, 16.0f));
		}
	}

	public void update(float delta) {
		if(ai) {
			if(vertical) {
				if(ball.getX() - this.getX() > -250 && ball.getX() - this.getX() < 250) {
					if(ball.getY() > this.getY() + 75) { setVelY(MAXVEL); }
					else if(ball.getY() < this.getY() + 25) { setVelY(-MAXVEL); }
				}
			} else {
				if(ball.getY() - this.getY() > -250 && ball.getY() - this.getY() < 250) {
					if(ball.getX() > this.getX() + 75) { setVelX(MAXVEL); }
					else if(ball.getX() < this.getX() + 25) { setVelX(-MAXVEL); }
				}
			}
		}
		
		super.update(delta);
		
		if(vertical) {
			if(getY() < 0.0f) { setY(0.0f); }
			else if(getY() > 350.0f) { setY(350.0f); }
		} else {
			if(getX() < 0.0f) { setX(0.0f); }
			else if(getX() > 350.0f) { setX(350.0f); }
		}
		
		setVelX(0.0f);
		setVelY(0.0f);
		setBoundsPosition(getX() + 17, getY() + 17);
	}

	public boolean getVertical() { return vertical; }
	public boolean getAI() { return ai; }
	public Rectangle getBounds() { return bounds; }

	public void setBounds(Rectangle b) { bounds = b; }
	public void setAI(boolean ai) { this.ai = ai; }
	public void setBoundsPosition(float x, float y) { bounds.setPosition(x, y); }
	public void movePos() {
		if(vertical) { setVelY(MAXVEL); }
		else { setVelX(MAXVEL); }
	}
	public void moveNeg() {
		if(vertical) { setVelY(-MAXVEL); }
		else { setVelX(-MAXVEL); }
	}
}
