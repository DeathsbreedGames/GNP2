package gnp.sprites;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class Sprite {
	private int x, y;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { return x; }
	public int getY() { return y; }

	public void setX(int nX) { this.x = nX; }
	public void setY(int nY) { this.y = nY; }
	public void incX(int iX) { this.x += iX; }
	public void incY(int iY) { this.y += iY; }
}