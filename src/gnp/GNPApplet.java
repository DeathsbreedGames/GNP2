package gnp;

import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class GNPApplet extends JApplet {
	// Basic dimensions
	public final int WIDTH = 500;
	public final int HEIGHT = 500;

	private JPanel game;

	@Override
	public void init() {
		game = new Game(WIDTH, HEIGHT);
		add(game);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
}