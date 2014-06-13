package gnp.utils;

import java.awt.event.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class InputHandler implements KeyListener {
	public static boolean upPressed = false;
	public static boolean downPressed = false;
	public static boolean commaPressed = false;
	public static boolean periodPressed = false;
	public static boolean bPressed = false;
	public static boolean vPressed = false;
	public static boolean wPressed = false;
	public static boolean sPressed = false;

	@Override
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();

		if(keyCode == KeyEvent.VK_UP) { upPressed = true; }
		if(keyCode == KeyEvent.VK_DOWN) { downPressed = true; }
		if(keyCode == KeyEvent.VK_COMMA) { commaPressed = true; }
		if(keyCode == KeyEvent.VK_PERIOD) { periodPressed = true; }
		if(keyCode == KeyEvent.VK_V) { vPressed = true; }
		if(keyCode == KeyEvent.VK_B) { bPressed = true; }
		if(keyCode == KeyEvent.VK_W) { wPressed = true; }
		if(keyCode == KeyEvent.VK_S) { sPressed = true; }
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		int keyCode = ke.getKeyCode();

		if(keyCode == KeyEvent.VK_UP) { upPressed = false; }
		if(keyCode == KeyEvent.VK_DOWN) { downPressed = false; }
		if(keyCode == KeyEvent.VK_COMMA) { commaPressed = false; }
		if(keyCode == KeyEvent.VK_PERIOD) { periodPressed = false; }
		if(keyCode == KeyEvent.VK_V) { vPressed = false; }
		if(keyCode == KeyEvent.VK_B) { bPressed = false; }
		if(keyCode == KeyEvent.VK_W) { wPressed = false; }
		if(keyCode == KeyEvent.VK_S) { sPressed = false; }
	}

	@Override
	public void keyTyped(KeyEvent ke) { }
}