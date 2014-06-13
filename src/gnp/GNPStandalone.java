package gnp;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class GNPStandalone extends JFrame {
	// Basic dimensions
	public final int WIDTH = 500;
	public final int HEIGHT = 500;

	private JPanel game;

	public GNPStandalone() {
		super("GNP2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game = new Game(WIDTH, HEIGHT);
		add(game);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}

	public static void main(String[] args) { new GNPStandalone(); }
}