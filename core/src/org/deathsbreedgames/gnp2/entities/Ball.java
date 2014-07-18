package org.deathsbreedgames.gnp2.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

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
	public int timer = 90;
	public boolean start = false;
	private Rectangle bounds;
	private final float MAXVEL = 8.0f;

	public Ball(float x, float y) {
		super(x, y);
		setBounds(new Rectangle(getX() + 12, getY() + 12, 22, 22));
	}

	public void update(float delta) {
		super.update(delta);
		setBoundsPosition(getX() + 15, getY() + 12);
		if(timer == 0) {
			float moveAngle = MathUtils.random(360.0f);
			setVelX(MathUtils.cosDeg(moveAngle) * MAXVEL);
			setVelY(MathUtils.sinDeg(moveAngle) * MAXVEL);
			start = true;
			timer--;
		} else if(timer > 0) { timer--; }
	}

	public Rectangle getBounds() { return bounds; }
	public float getMaxVel() { return MAXVEL; }

	public void setBounds(Rectangle c) { bounds = c; }
	public void setBoundsPosition(float x, float y) { bounds.setPosition(x, y); }
	public void setMoveAngle(float nA) {
		setVelX(MathUtils.cosDeg(nA) * MAXVEL);
		setVelY(MathUtils.sinDeg(nA) * MAXVEL);
	}
}