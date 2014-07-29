package org.deathsbreedgames.gnp2.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This is a template class used to reduce the
 * work and effort needed on other entities.
 * 
 */
public class Entity {
	// Variables used for movement & positioning of entity
	private Vector2 position;
	private Vector2 velocity;
	
	// Constructor
	public Entity(float x, float y) {
		// Declare variables:
		position = new Vector2(x, y);
		velocity = new Vector2(0.0f, 0.0f);
	}
	
	// Update
	public void update(float delta) {
		// Add velocity to the position of the entity
		position.add(velocity);
	}
	
	// Getter/Setter methods:
	public float getX() { return position.x; }
	public float getY() { return position.y; }
	public float getVelX() { return velocity.x; }
	public float getVelY() { return velocity.y; }

	public void setX(float x) { position.x = x; }
	public void setY(float y) { position.y = y; }
	public void setVelX(float vX) { velocity.x = vX; }
	public void setVelY(float vY) { velocity.y = vY; }
}
