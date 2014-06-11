package gnp.sprites;

import java.awt.*;
import java.util.Random;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class Ball extends Sprite {
	private final int SPEED = 6;
	private int moveAngle;

	private int timer = 150;
	private int countDown;

	private boolean play = false;

	private Random rand = new Random();

	public Ball(int x, int y) {
		super(x, y);
		moveAngle = rand.nextInt(360);
	}

	public void update() {
		if(!play) {
			if(timer >= 0) {
				if(timer == 0) {
					countDown = 0;
					play = true;
				} else if(timer < 50) { countDown = 1; }
				else if(timer < 100) { countDown = 2; }
				else if(timer < 150) { countDown = 3; }
				timer--;
			}
		} else {
			incX((int)(Math.cos(Math.toRadians(moveAngle)) * SPEED));
			incY(-(int)(Math.sin(Math.toRadians(moveAngle)) * SPEED));
		}
	}

	public int getMoveAngle() { return moveAngle; }
	public int getCountDown() { return countDown; }
	public boolean getPlay() { return play; }
	public Rectangle getBounds() {
		Rectangle r = new Rectangle(getX() - 6, getY() - 6, 12, 12);
		return r;
	}

	public void setMoveAngle(int nMA) { this.moveAngle = nMA; }
}