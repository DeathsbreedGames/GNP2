package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
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
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class renders and controls the Main Menu
 * of the game.
 * 
 */
public class MainMenuScreen extends BaseScreen {
	public Stage mainStage;
	public Stage otherStage;
	private SpriteBatch batch = new SpriteBatch();
	private Skin skin = new Skin();
	private BitmapFont buttonFont = new BitmapFont();
	private BitmapFont textFont = new BitmapFont();
	private TextButtonStyle buttonStyle = new TextButtonStyle();
	public static int currentMenu = 0;
	public Game game;

	public MainMenuScreen(Game game) {
		this.game = game;
		initSetup();
	}

	private void initSetup() {
		mainStage = new Stage();
		otherStage = new Stage();
		Gdx.input.setInputProcessor(mainStage);

		Pixmap mainPixmap = new Pixmap(120, 40, Format.RGBA8888);
		mainPixmap.setColor(Color.GREEN);
		mainPixmap.fill();
		skin.add("main", new Texture(mainPixmap));

		buttonFont.scale(0.5f);
		skin.add("default", buttonFont);

		textFont.scale(0.1f);

		buttonStyle.up = skin.newDrawable("main", Color.BLACK);
		buttonStyle.down = skin.newDrawable("main", Color.BLACK);
		buttonStyle.over = skin.newDrawable("main", Color.LIGHT_GRAY);
		buttonStyle.font = skin.getFont("default");
		skin.add("default", buttonStyle);

		setupMainMenu();
		setupOtherMenu();
	}

	private void setupMainMenu() {
		TextButton playButton = new TextButton("Play", buttonStyle);
		TextButton instructionsButton = new TextButton("Instructions", buttonStyle);
		TextButton creditsButton = new TextButton("Credits", buttonStyle);
		playButton.setPosition(190, 250);
		instructionsButton.setPosition(190, 210);
		creditsButton.setPosition(190, 170);
		mainStage.addActor(playButton);
		mainStage.addActor(instructionsButton);
		mainStage.addActor(creditsButton);

		playButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen(game));
			}
		});
		instructionsButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				currentMenu = 1;
				Gdx.input.setInputProcessor(otherStage);
			}
		});
		creditsButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				currentMenu = 2;
				Gdx.input.setInputProcessor(otherStage);
			}
		});
	}

	private void setupOtherMenu() {
		TextButton backButton = new TextButton("Back", buttonStyle);
		backButton.setPosition(190, 10);
		otherStage.addActor(backButton);

		backButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				currentMenu = 0;
				Gdx.input.setInputProcessor(mainStage);
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(currentMenu == 0) {
			mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			mainStage.draw();
		} else if(currentMenu == 1) {
			otherStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			otherStage.draw();

			batch.begin();
			buttonFont.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			buttonFont.draw(batch, "Instructions", 195, 450);
			textFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			textFont.draw(batch, "Left User: W, S", 20, 400);
			textFont.draw(batch, "Right User: UP, DOWN", 20, 370);
			textFont.draw(batch, "Top User: V, B", 20, 340);
			textFont.draw(batch, "Bottom User: COMMA, PERIOD", 20, 310);
			textFont.draw(batch, "Pause Game: P", 20, 280);
			textFont.draw(batch, "Quit to Menu: ESC", 20, 250);
			batch.end();
		} else if(currentMenu == 2) {
			otherStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			otherStage.draw();

			batch.begin();
			buttonFont.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			buttonFont.draw(batch, "Credits", 215, 450);
			textFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			textFont.draw(batch, "This program is Free Software and is licenced with the GNU", 20, 400);
			textFont.draw(batch, "GPLv3 license. The graphics of this game are license with a", 20, 370);
			textFont.draw(batch, "CC-BY-SA license. Follows the link to the source-code:", 20, 340);
			textFont.draw(batch, "https://github.com/DeathsbreedGames/GNP2", 60, 310);
			textFont.draw(batch, "For more projects by Deathsbreed visit of the following:", 20, 260);
			textFont.draw(batch, "https://github.com/DeathsbreedGames", 60, 230);
			textFont.draw(batch, "https://github.com/Deathsbreed", 60, 200);
			textFont.draw(batch, "http://themusicinnoise.net", 60, 170);
			textFont.draw(batch, "v0.2", 455, 25);
			batch.end();
		}
	}

	@Override
	public void dispose() {
		mainStage.dispose();
		otherStage.dispose();
		skin.dispose();
	}
}