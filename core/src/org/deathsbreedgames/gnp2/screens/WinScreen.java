package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU Affero GPLv3
 * @year 2014
 * 
 * Description: This screen appears when a player wins the game.
 * 
 */
public class WinScreen extends BaseScreen {
	// The number of the player who won
	private int playerNumber;
	
	private SpriteBatch batch;
	private BitmapFont winnerFont;
	private Stage buttonStage;
	
	// Constructor
	public WinScreen(int pNumber) {
		// pNumber is the one behind because of how arrays work
		this.playerNumber = pNumber + 1;
		
		batch = new SpriteBatch();
		
		winnerFont = new BitmapFont();
		winnerFont.scale(1.5f);
		
		// For the neatness of the code
		setupButtons();
	}
	
	private void setupButtons() {
		buttonStage = new Stage();
		TextureAtlas buttonAtlas = new TextureAtlas("gfx/buttons.pack");
		Skin buttonSkin = new Skin(buttonAtlas);
		
		Gdx.input.setInputProcessor(buttonStage);
		
		BitmapFont buttonFont = new BitmapFont();
		
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = buttonSkin.getDrawable("button_green");
		buttonStyle.down = buttonSkin.getDrawable("button_green");
		buttonStyle.over = buttonSkin.getDrawable("button_light_green");
		buttonStyle.font = buttonFont;
		
		TextButton backToMainMenu = new TextButton("Main Menu", buttonStyle);
		backToMainMenu.setPosition(Gdx.graphics.getWidth() / 2 - backToMainMenu.getWidth() / 2, 50);
		buttonStage.addActor(backToMainMenu);
		backToMainMenu.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Go back to the Main Menu
				setDone(true);
			}
		});
	}
	
	// Update
	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		winnerFont.draw(batch, "PLAYER " + playerNumber + " WINS!", 100, 300); 
		batch.end();
		
		// Draw button:
		buttonStage.act(delta);
		buttonStage.draw();
	}
	
	// Dispose
	@Override
	public void dispose() {
		batch.dispose();
		winnerFont.dispose();
		buttonStage.dispose();
	}
}
