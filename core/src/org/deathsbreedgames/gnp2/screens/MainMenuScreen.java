package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	private Stage stage;
	private Skin skin;
	public Game game;

	public MainMenuScreen(Game game) {
		this.game = game;
		setupMenu();
	}

	private void setupMenu() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin();

		Pixmap pixmap = new Pixmap(60, 40, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		BitmapFont font = new BitmapFont();
		font.scale(0.5f);
		skin.add("default", font);

		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("white", Color.BLACK);
		buttonStyle.down = skin.newDrawable("white", Color.BLACK);
		buttonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		buttonStyle.font = skin.getFont("default");
		skin.add("default", buttonStyle);

		TextButton button = new TextButton("Play", buttonStyle);
		button.setPosition(220, 275);
		stage.addActor(button);

		button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen(game));
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
}