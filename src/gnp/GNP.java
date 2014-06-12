package gnp;

import java.awt.*;
import javax.swing.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class GNP extends JApplet {
	// Basic dimensions
	public final int WIDTH = 500;
	public final int HEIGHT = 500;

	private JPanel game;

	@Override
	public void init() {
		setBackground(Color.BLACK);

		game = new Game(WIDTH, HEIGHT);
		add(game);

		setSize(WIDTH, HEIGHT);
	}
}