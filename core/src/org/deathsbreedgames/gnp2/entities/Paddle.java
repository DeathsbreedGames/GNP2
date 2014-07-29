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
	// Maximum velocity of this entity
	public static final float MAXVEL = 5.0f;
	// Variables for collision
	private Rectangle bounds;
	private boolean vertical;
	// AI variable
	private boolean ai;

	// Constructor
	public Paddle(float x, float y, boolean vertical) {
		// Run the constructor of the super class (in this case Entity)
		super(x, y);
		
		this.vertical = vertical;
		
		// Set the bounding Rectangle
		if(vertical) {
			setBounds(new Rectangle(getX() + 17, getY() + 17, 16.0f, 120.0f));
		} else {
			setBounds(new Rectangle(getX() + 17, getY() + 17, 120.0f, 16.0f));
		}
	}
	
	// Update
	public void update(float delta) {
		// Run the update method of the super-class
		super.update(delta);
		
		// Do not permit movement past certain point
		if(vertical) {
			if(getY() < 0.0f) { setY(0.0f); }
			else if(getY() > 350.0f) { setY(350.0f); }
		} else {
			if(getX() < 0.0f) { setX(0.0f); }
			else if(getX() > 350.0f) { setX(350.0f); }
		}
		
		// Set the velocities to 0
		setVelX(0.0f);
		setVelY(0.0f);
		// Set the bounds position to the paddle's position
		setBoundsPosition(getX() + 17, getY() + 17);
	}
	
	// Getter/Setter methods:
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
