package gnp;

import javax.swing.*;
import java.applet.Applet;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.net.*;

import gnp.sprites.*;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 */
public class Game extends JPanel implements Runnable, KeyListener {
	// Basic dimensions
	private int width, height;

	// The gameloop thread
	private Thread gameloop;

	// Used as a backbuffer to avoid flickering
	private Graphics buffer;
	private Image image;

	// Image setup
	private BufferedImage bg;
	private BufferedImage bluePaddle, redPaddle, greenPaddle, purplePaddle, balli;

	// Setting up the sprites
	private Paddle player1, player2, player3, player4;
	private Ball ball;

	// Says who touched the ball last
	private int ballLastTouch = 0;

	// The scores
	private int score1 = 0;
	private int score2 = 0;
	private int score3 = 0;
	private int score4 = 0;

	// Constructor
	public Game(int w, int h) {
		this.width = w;
		this.height = h;
		setSize(width, height);
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();

		try {
			bg = ImageIO.read(this.getClass().getResource("assets/gfx/bg.jpg"));
			bluePaddle = ImageIO.read(this.getClass().getResource("assets/gfx/Paddle_blue.png"));
			redPaddle = ImageIO.read(this.getClass().getResource("assets/gfx/Paddle_red.png"));
			greenPaddle = ImageIO.read(this.getClass().getResource("assets/gfx/Paddle_green.png"));
			purplePaddle = ImageIO.read(this.getClass().getResource("assets/gfx/Paddle_purple.png"));
			balli = ImageIO.read(this.getClass().getResource("assets/gfx/Ball_orange.png"));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		player1 = new Paddle(30, height / 2);
		player2 = new Paddle(470, height / 2);
		player3 = new Paddle(width / 2, 30);
		player4 = new Paddle(width / 2, 470);
		ball = new Ball(width / 2, height / 2);

		player1.setVertical(true);
		player2.setVertical(true);

		addKeyListener(this);

		start();
	}

	// Start the gameloop
	public void start() {
		gameloop = new Thread(this);
		gameloop.start();
	}

	// Stop the gameloop
	public void stop() { gameloop = null; }

	// the paint method
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			buffer = image.getGraphics();
		}

		buffer.setColor(getBackground());
		buffer.fillRect(0, 0, getWidth(), getHeight());
		buffer.setColor(getForeground());
		paint(buffer);

		g.drawImage(image, 0, 0, this);
	}

	public void paint(Graphics g) {
		g.translate(0, 0);
		g.drawImage(bg, 0, 0, this);
		g.drawImage(bluePaddle, player1.getX() - 25, player1.getY() - 75, this);
		g.drawImage(redPaddle, player2.getX() - 25, player2.getY() - 75, this);
		g.drawImage(greenPaddle, player3.getX() - 75, player3.getY() - 25, this);
		g.drawImage(purplePaddle, player4.getX() - 75, player4.getY() - 25, this);
		g.drawImage(balli, ball.getX() - 25, ball.getY() - 25, this);

		g.setColor(Color.BLUE);
		g.drawString("" + score1, 10, 20);
		g.setColor(Color.RED);
		g.drawString("" + score2, 490, 480);
		g.setColor(Color.GREEN);
		g.drawString("" + score3, 20, 10);
		g.setColor(Color.MAGENTA);
		g.drawString("" + score4, 480, 490);

		g.setColor(Color.WHITE);
		if(!ball.getPlay()) {
			g.drawString("" + ball.getCountDown(), 250, 250);
		}
	}

	// The run method
	@Override
	public void run() {
		Thread t = Thread.currentThread();

		while(t == gameloop) {
			try {
				gameUpdate();

				Thread.sleep(20);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			// Runs the paintComponent method
			repaint();
		}
	}

	private void gameUpdate() {
		checkCollisions();

		player1.update();
		player2.update();
		player3.update();
		player4.update();
		ball.update();
	}

	public void checkCollisions() {
		double newAngle;

		if(ball.getBounds().intersects(player1.getBounds())) {
			newAngle = ((player1.getY() - ball.getY() + 61) * 180 / 122) - 90;
			ball.setMoveAngle((int)newAngle);
			ball.setX(player1.getX() + 10);
			ballLastTouch = 1;
		} else if(ball.getBounds().intersects(player2.getBounds())) {
			newAngle = ((ball.getY() - player2.getY() + 61) * 180 / 122) + 90;
			ball.setMoveAngle((int)newAngle);
			ball.setX(player2.getX() - 10);
			ballLastTouch = 2;
		}

		if(ball.getBounds().intersects(player3.getBounds())) {
			newAngle = ((ball.getX() - player3.getX() + 61) * 180 / 122) + 180;
			ball.setMoveAngle((int)newAngle);
			ball.setY(player3.getY() + 10);
			ballLastTouch = 3;
		} else if(ball.getBounds().intersects(player4.getBounds())) {
			newAngle = ((player4.getX() - ball.getX() + 61) * 180 / 122);
			ball.setMoveAngle((int)newAngle);
			ball.setY(player4.getY() - 10);
			ballLastTouch = 4;
		}

		if(ball.getX() <= 0) {
			if(ballLastTouch == 0 || ballLastTouch == 1) { score1--; }
			else if(ballLastTouch == 2) { score2++; }
			else if(ballLastTouch == 3) { score3++; }
			else if(ballLastTouch == 4) { score4++; }

			ball = new Ball(width / 2, height / 2);
			ballLastTouch = 0;
		}
		if(ball.getX() >= width) {
			if(ballLastTouch == 0 || ballLastTouch == 2) { score2--; }
			else if(ballLastTouch == 1) { score1++; }
			else if(ballLastTouch == 3) { score3++; }
			else if(ballLastTouch == 4) { score4++; }

			ball = new Ball(width / 2, height / 2);
			ballLastTouch = 0;
		}
		if(ball.getY() <= 0) {
			if(ballLastTouch == 0 || ballLastTouch == 3) { score3--; }
			else if(ballLastTouch == 1) { score1++; }
			else if(ballLastTouch == 2) { score2++; }
			else if(ballLastTouch == 4) { score4++; }

			ball = new Ball(width / 2, height / 2);
			ballLastTouch = 0;
		}
		if(ball.getY() >= height) {
			if(ballLastTouch == 0 || ballLastTouch == 4) { score4--; }
			else if(ballLastTouch == 1) { score1++; }
			else if(ballLastTouch == 2) { score2++; }
			else if(ballLastTouch == 3) { score3++; }

			ball = new Ball(width / 2, height / 2);
			ballLastTouch = 0;
		}
	}

	public void keyPressed(KeyEvent ke) {
		System.out.println("A key was pressed.");
		int keyCode = ke.getKeyCode();
		if(keyCode == KeyEvent.VK_W) { player1.setMoveUp(true); }
		if(keyCode == KeyEvent.VK_S) { player1.setMoveDown(true); }
		if(keyCode == KeyEvent.VK_UP) { player2.setMoveUp(true); }
		if(keyCode == KeyEvent.VK_DOWN) { player2.setMoveDown(true); }
		if(keyCode == KeyEvent.VK_V) { player3.setMoveLeft(true); }
		if(keyCode == KeyEvent.VK_B) { player3.setMoveRight(true); }
		if(keyCode == KeyEvent.VK_COMMA) { player4.setMoveLeft(true); }
		if(keyCode == KeyEvent.VK_PERIOD) { player4.setMoveRight(true); }
	}
	public void keyReleased(KeyEvent ke) {
		System.out.println("A key was released.");
		int keyCode = ke.getKeyCode();
		if(keyCode == KeyEvent.VK_W) { player1.setMoveUp(false); }
		if(keyCode == KeyEvent.VK_S) { player1.setMoveDown(false); }
		if(keyCode == KeyEvent.VK_UP) { player2.setMoveUp(false); }
		if(keyCode == KeyEvent.VK_DOWN) { player2.setMoveDown(false); }
		if(keyCode == KeyEvent.VK_V) { player3.setMoveLeft(false); }
		if(keyCode == KeyEvent.VK_B) { player3.setMoveRight(false); }
		if(keyCode == KeyEvent.VK_COMMA) { player4.setMoveLeft(false); }
		if(keyCode == KeyEvent.VK_PERIOD) { player4.setMoveRight(false); }
	}
	public void keyTyped(KeyEvent ke) {}
}