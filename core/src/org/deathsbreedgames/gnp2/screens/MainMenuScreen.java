package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
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
 * Description: This class renders and controls the Main Menu
 * of the game.
 * 
 */
public class MainMenuScreen extends BaseScreen {
	private SpriteBatch batch = new SpriteBatch();
	private Texture gnpLogo;
	private BitmapFont textFont;
	
	private Stage mainStage, otherStage;
	private BitmapFont buttonFont;
	private TextButtonStyle buttonStyle;
	
	/*
	 * This variable is used in order to know which menu to draw using
	 * the following scheme:
	 * 		0 = Main Menu
	 * 		1 = Instructions Menu
	 * 		2 = Credits Menu
	 */
	public static int currentMenu;

	// Constructor
	public MainMenuScreen() {
		currentMenu = 0;
		
		gnpLogo = new Texture(Gdx.files.internal("gfx/GNP2.png"));
		textFont = new BitmapFont();
		textFont.scale(0.1f);
		
		
		mainStage = new Stage();
		otherStage = new Stage();
		TextureAtlas buttonAtlas = new TextureAtlas("gfx/buttons.pack");
		Skin buttonSkin = new Skin(buttonAtlas);
		
		Gdx.input.setInputProcessor(mainStage);
		
		buttonFont = new BitmapFont();
		buttonFont.scale(0.5f);
		
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = buttonSkin.getDrawable("button_green");
		buttonStyle.down = buttonSkin.getDrawable("button_green");
		buttonStyle.over = buttonSkin.getDrawable("button_light_green");
		buttonStyle.font = buttonFont;
		
		// This function is used to make the code a little neater
		createButtons();
	}
	
	private void createButtons() {
		TextButton playButton = new TextButton("Play", buttonStyle);
		playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, 250);
		mainStage.addActor(playButton);
		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				setDone(true);
			}
		});
		
		TextButton instructionsButton = new TextButton("Instructions", buttonStyle);
		instructionsButton.setPosition(Gdx.graphics.getWidth() / 2 - instructionsButton.getWidth() / 2, playButton.getY() - instructionsButton.getHeight());
		mainStage.addActor(instructionsButton);
		instructionsButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Change to Instructions Menu
				currentMenu = 1;
				Gdx.input.setInputProcessor(otherStage);
			}
		});
		
		TextButton creditsButton = new TextButton("Credits", buttonStyle);
		creditsButton.setPosition(Gdx.graphics.getWidth() / 2 - creditsButton.getWidth() / 2, instructionsButton.getY() - creditsButton.getHeight());
		mainStage.addActor(creditsButton);
		creditsButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Change to Credits Menu
				currentMenu = 2;
				Gdx.input.setInputProcessor(otherStage);
			}
		});
		
		// This is for desktop targetting only, not HTML5
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			TextButton exitButton = new TextButton("Exit", buttonStyle);
			exitButton.setPosition(Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2, creditsButton.getY() - exitButton.getHeight());
			mainStage.addActor(exitButton);
			exitButton.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					// Quit the application
					Gdx.app.exit();
				}
			});
		}
		
		TextButton backButton = new TextButton("Back", buttonStyle);
		backButton.setPosition(Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2, 10);
		otherStage.addActor(backButton);
		backButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Change to Main Menu
				currentMenu = 0;
				Gdx.input.setInputProcessor(mainStage);
			}
		});
	}

	// Update
	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(currentMenu == 0) {
			mainStage.act(delta);
			mainStage.draw();

			batch.begin();
			batch.draw(gnpLogo, 50, 300);
			batch.end();
		} else if(currentMenu == 1) {
			otherStage.act(delta);
			otherStage.draw();

			batch.begin();
			buttonFont.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			buttonFont.draw(batch, "Instructions", 195, 450);
			textFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			textFont.draw(batch, "Left Player: W, S", 20, 400);
			textFont.draw(batch, "Right Player: APOSTROPHE, SLASH", 20, 370);
			textFont.draw(batch, "Top Player: V, B", 20, 340);
			textFont.draw(batch, "Bottom Player: M, COMMA", 20, 310);
			textFont.draw(batch, "Pause Game: P", 20, 280);
			textFont.draw(batch, "Quit to Menu: ESC", 20, 250);
			textFont.draw(batch, "In 1v1v1v1 the objective is to be the last person to touch the ball", 20, 200);
			textFont.draw(batch, "right before it goes into someone else's goal. Have fun!", 20, 170);
			batch.end();
		} else if(currentMenu == 2) {
			otherStage.act(delta);
			otherStage.draw();

			batch.begin();
			buttonFont.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			buttonFont.draw(batch, "Credits", 215, 450);
			textFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			textFont.draw(batch, "Work - Author - License", 20, 400);
			textFont.draw(batch, "Graphics - Nicolás A. Ortega - CC-BY-SA", 20, 370);
			textFont.draw(batch, "Music/Sfx - Nicolás A. Ortega - CC-BY-SA", 20, 340);
			textFont.draw(batch, "Programming - Nicolás A. Ortega - GNU GPLv3", 20, 310);
			textFont.draw(batch, "source-code: https://github.com/DeathsbreedGames/GNP2", 20, 250);
			textFont.draw(batch, "More by DeathsbreedGames:", 20, 220);
			textFont.draw(batch, "https://github.com/DeathsbreedGames", 40, 190);
			textFont.draw(batch, "v1.0", 455, 25);
			batch.end();
		}
	}
	
	// Dispose
	@Override
	public void dispose() {
		batch.dispose();
		gnpLogo.dispose();
		textFont.dispose();
		mainStage.dispose();
		otherStage.dispose();
		buttonFont.dispose();
	}
}
