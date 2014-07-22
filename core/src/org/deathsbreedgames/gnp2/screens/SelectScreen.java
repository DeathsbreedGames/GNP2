package org.deathsbreedgames.gnp2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
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

import org.deathsbreedgames.gnp2.Game;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class is between the Main Menu and the
 * Game. Here the player(s) select the game mode they wish to
 * play, and which paddles will be AI and which will be players.
 * 
 */
public class SelectScreen extends BaseScreen {
	public Game game;
	
	private SpriteBatch batch = new SpriteBatch();
	public Stage gameModeStage, groupModeStage, classicModeStage;
	private Skin buttonSkin = new Skin();
	private BitmapFont buttonFont = new BitmapFont();
	private TextButtonStyle buttonStyle = new TextButtonStyle();
	
	private TextButton groupModeButton;
	private TextButton classicModeButton;
	private TextButton[] aiPlayersGroup = new TextButton[4];
	private TextButton[] aiPlayersClassic = new TextButton[2];
	public TextButton backToModeSelect;
	private TextButton backToMainMenu;
	
	public static int currentMenu = 0;
	
	private boolean[] groupAIPlayers = new boolean[4];
	private boolean[] classicAIPlayers = new boolean[2];
	
	public SelectScreen(Game game) {
		this.game = game;
		
		for(int i = 0; i < groupAIPlayers.length; i++) { groupAIPlayers[i] = false; }
		for(int i = 0; i < classicAIPlayers.length; i++) { classicAIPlayers[i] = false; }
		
		gameModeStage = new Stage();
		groupModeStage = new Stage();
		classicModeStage = new Stage();
		
		Gdx.input.setInputProcessor(gameModeStage);
		
		Pixmap buttonPixmap = new Pixmap(140, 40, Format.RGBA8888);
		buttonPixmap.setColor(Color.WHITE);
		buttonPixmap.fill();
		buttonSkin.add("main", new Texture(buttonPixmap));
		
		buttonFont.scale(0.4f);
		buttonSkin.add("default", buttonFont);
		
		buttonStyle.up = buttonSkin.newDrawable("main", Color.BLACK);
		buttonStyle.down = buttonSkin.newDrawable("main", Color.BLACK);
		buttonStyle.over = buttonSkin.newDrawable("main", Color.GREEN);
		buttonStyle.font = buttonSkin.getFont("default");
		buttonSkin.add("default", buttonStyle);
		
		setupModeSelect();
		
		backToModeSelect = new TextButton("Back", buttonStyle);
		backToModeSelect.setPosition(10, 10);
		//groupModeStage.addActor(backToModeSelect);
		backToModeSelect.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				currentMenu = 0;
				Gdx.input.setInputProcessor(gameModeStage);
			}
		});
	}
	
	private void setupModeSelect() {
		backToMainMenu = new TextButton("Back", buttonStyle);
		backToMainMenu.setPosition(170, 10);
		gameModeStage.addActor(backToMainMenu);
		backToMainMenu.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen(game));
			}
		});
		
		groupModeButton = new TextButton("Group Mode", buttonStyle);
		groupModeButton.setPosition(170, 250);
		gameModeStage.addActor(groupModeButton);
		groupModeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				/*currentMenu = 1;
				groupModeStage.addActor(backToModeSelect);
				Gdx.input.setInputProcessor(groupModeStage);*/
				game.setScreen(new GameScreen(game));
			}
		});
		
		classicModeButton = new TextButton("Classic Mode", buttonStyle);
		classicModeButton.setPosition(170, 210);
		gameModeStage.addActor(classicModeButton);
		classicModeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				currentMenu = 2;
				classicModeStage.addActor(backToModeSelect);
				Gdx.input.setInputProcessor(classicModeStage);
			}
		});
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(currentMenu == 0) {
			gameModeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			gameModeStage.draw();
		} else if(currentMenu == 1) {
			groupModeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			groupModeStage.draw();
		} else if(currentMenu == 2) {
			classicModeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30.0f));
			classicModeStage.draw();
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		gameModeStage.dispose();
		groupModeStage.dispose();
		classicModeStage.dispose();
		buttonSkin.dispose();
		buttonFont.dispose();
	}
}
