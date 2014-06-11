package gnp.sprites;

import java.awt.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class Paddle extends Sprite {
	private boolean vertical = false;

	private final int SPEED = 5;

	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;

	public Paddle(int x, int y) {
		super(x, y);
	}

	public void update() {
		if(moveUp && getY() > 0) { incY(-SPEED); }
		if(moveDown && getY() < 500) { incY(SPEED); }
		if(moveLeft && getX() > 0) { incX(-SPEED); }
		if(moveRight && getX() < 500) { incX(SPEED); }
	}

	public boolean getVertical() { return vertical; }
	public boolean getMoveUp() { return moveUp; }
	public boolean getMoveDown() { return moveDown; }
	public boolean getMoveLeft() { return moveLeft; }
	public boolean getMoveRight() { return moveRight; }
	public Rectangle getBounds() {
		Rectangle r;
		if(vertical) {
			r = new Rectangle(getX() - 3, getY() - 55, 6, 110);
		} else {
			r = new Rectangle(getX() - 55, getY() - 3, 110, 6);
		}
		return r;
	}

	public void setVertical(boolean v) { this.vertical = v; }
	public void setMoveUp(boolean m) { this.moveUp = m; }
	public void setMoveDown(boolean m) { this.moveDown = m; }
	public void setMoveLeft(boolean m) { this.moveLeft = m; }
	public void setMoveRight(boolean m) { this.moveRight = m; }
}