package org.deathsbreedgames.gnp2.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

import org.deathsbreedgames.gnp2.GlobalVars;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: Similar to the Paddle class, this class
 * contains all the necessary variables needed for the Ball
 * that would be too much of a hastle to put them in another
 * class.
 * 
 */
public class Ball extends Entity {
	// Variables used for '3...2...1...' countdown
	public int timer = 90;
	public boolean start = false;
	
	// Maximum velocity of this entity
	private final float MAXVEL = 8.0f;
	// Collision bounds
	private Rectangle bounds;
	
	private float moveAngle;
	
	// Constructor
	public Ball(float x, float y) {
		// Run the constructor of the super-class (in this case Entity)
		super(x, y);
		// Set the bounding Rectangle
		setBounds(new Rectangle(getX() + 12, getY() + 12, 22, 22));
	}
	
	// Update
	public void update(float delta) {
		// Run the update method of the super-class
		super.update(delta);
		// Set the position of the rectangle to the entity's position
		setBoundsPosition(getX() + 15, getY() + 12);
		// Countdown stuff:
		if(timer == 0) {
			if(GlobalVars.currentGameMode == 1) {
				while(true) {
					moveAngle = MathUtils.random(360.0f);
					if(moveAngle <= 75.0f && moveAngle >= 335.0f) { break; }
					else if(moveAngle >= 115 && moveAngle <= 245) { break; }
				}
			} else {
				moveAngle = MathUtils.random(360.0f);
			}
			setVelX(MathUtils.cosDeg(moveAngle) * MAXVEL);
			setVelY(MathUtils.sinDeg(moveAngle) * MAXVEL);
			start = true;
			timer--;
		} else if(timer > 0) { timer--; }
	}
	
	// Reset Ball
	public void reset(float x, float y) {
		timer = 90;
		start = false;
		setX(x);
		setY(y);
		setVelX(0.0f);
		setVelY(0.0f);
	}
	
	// Getter/Setter methods:
	public Rectangle getBounds() { return bounds; }
	public float getMaxVel() { return MAXVEL; }
	public float getMoveAngle() { return moveAngle; }

	public void setBounds(Rectangle c) { bounds = c; }
	public void setBoundsPosition(float x, float y) { bounds.setPosition(x, y); }
	public void setMoveAngle(float nA) {
		setVelX(MathUtils.cosDeg(nA) * MAXVEL);
		setVelY(MathUtils.sinDeg(nA) * MAXVEL);
		moveAngle = nA;
	}
}
