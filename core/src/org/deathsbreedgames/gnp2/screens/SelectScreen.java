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

import org.deathsbreedgames.gnp2.GlobalVars;

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
	public Stage gameModeStage, groupModeStage, classicModeStage;
	private BitmapFont buttonFont;
	private TextButtonStyle buttonStyle;
	private TextButtonStyle aiChooseStyle;
	
	private TextButton groupModeButton;
	private TextButton classicModeButton;
	private TextButton[] aiPlayersGroup = new TextButton[4];
	private TextButton[] aiPlayersClassic = new TextButton[2];
	private TextButton playGroupMode;
	private TextButton playClassicMode;
	public TextButton backToModeSelect;
	private TextButton backToMainMenu;
	
	/*
	 * This variable is used in order to know which menu to draw using
	 * the following scheme:
	 * 		0 = Mode Select Menu
	 * 		1 = Group Mode Menu
	 * 		2 = Classic Mode Menu
	 */
	public static int currentMenu = 0;
	
	// These variables indicate whether a player is ai or not
	public boolean[] groupAIPlayers = new boolean[4];
	public boolean[] classicAIPlayers = new boolean[2];
	
	// Constructor
	public SelectScreen() {
		// To start, set all ai variables to false
		for(int i = 0; i < groupAIPlayers.length; i++) { groupAIPlayers[i] = false; }
		for(int i = 0; i < classicAIPlayers.length; i++) { classicAIPlayers[i] = false; }
		
		gameModeStage = new Stage();
		groupModeStage = new Stage();
		classicModeStage = new Stage();
		TextureAtlas buttonAtlas = new TextureAtlas("gfx/buttons.pack");
		Skin buttonSkin = new Skin(buttonAtlas);
		
		Gdx.input.setInputProcessor(gameModeStage);
		
		buttonFont = new BitmapFont();
		buttonFont.scale(0.5f);
		
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = buttonSkin.getDrawable("button_green");
		buttonStyle.down = buttonSkin.getDrawable("button_green");
		buttonStyle.over = buttonSkin.getDrawable("button_light_green");
		buttonStyle.font = buttonFont;
		
		aiChooseStyle = new TextButtonStyle();
		aiChooseStyle.up = buttonSkin.getDrawable("button_blue");
		aiChooseStyle.down = buttonSkin.getDrawable("button_blue");
		aiChooseStyle.checked = buttonSkin.getDrawable("button_red");
		aiChooseStyle.font = buttonFont;
		
		createButtons();
	}
	
	private void createButtons() {
		backToMainMenu = new TextButton("Back", buttonStyle);
		backToMainMenu.setPosition(Gdx.graphics.getWidth() / 2 - backToMainMenu.getWidth() / 2, 10);
		gameModeStage.addActor(backToMainMenu);
		backToMainMenu.addListener(new ChangeListener() { 
			public void changed(ChangeEvent event, Actor actor) {
				// Go back to the MainMenuScreen
				GlobalVars.currentGameMode = -1;
				setDone(true);
			}
		});
		
		groupModeButton = new TextButton("Group Mode", buttonStyle);
		groupModeButton.setPosition(Gdx.graphics.getWidth() / 2 - groupModeButton.getWidth() / 2, 250);
		gameModeStage.addActor(groupModeButton);
		groupModeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Go to the Group Mode Menu
				currentMenu = 1;
				groupModeStage.addActor(backToModeSelect);
				Gdx.input.setInputProcessor(groupModeStage);
			}
		});
		
		classicModeButton = new TextButton("Classic Mode", buttonStyle);
		classicModeButton.setPosition(Gdx.graphics.getWidth() / 2 - classicModeButton.getWidth() / 2, groupModeButton.getY() - classicModeButton.getHeight());
		gameModeStage.addActor(classicModeButton);
		classicModeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Go to the Classic Mode Menu
				currentMenu = 2;
				classicModeStage.addActor(backToModeSelect);
				Gdx.input.setInputProcessor(classicModeStage);
			}
		});
		
		for(int i = 0; i < aiPlayersGroup.length; i++) {
			aiPlayersGroup[i] = new TextButton("Player", aiChooseStyle);
			groupModeStage.addActor(aiPlayersGroup[i]);
		}
		aiPlayersGroup[0].setPosition(40, Gdx.graphics.getHeight() / 2 - aiPlayersGroup[0].getHeight() / 2);
		aiPlayersGroup[1].setPosition(Gdx.graphics.getWidth() - (aiPlayersGroup[1].getWidth() + 40), aiPlayersGroup[0].getY());
		aiPlayersGroup[2].setPosition(Gdx.graphics.getWidth() / 2 - aiPlayersGroup[2].getWidth() / 2, Gdx.graphics.getHeight() - (aiPlayersGroup[2].getHeight() + 40));
		aiPlayersGroup[3].setPosition(aiPlayersGroup[2].getX(), 40);
		
		aiPlayersGroup[0].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!groupAIPlayers[0]) {
					groupAIPlayers[0] = true;
					aiPlayersGroup[0].setText("AI");
				} else {
					groupAIPlayers[0] = false;
					aiPlayersGroup[0].setText("Player");
				}
			}
		});
		aiPlayersGroup[1].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!groupAIPlayers[1]) {
					groupAIPlayers[1] = true;
					aiPlayersGroup[1].setText("AI");
				} else {
					groupAIPlayers[1] = false;
					aiPlayersGroup[1].setText("Player");
				}
			}
		});
		aiPlayersGroup[2].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!groupAIPlayers[2]) {
					groupAIPlayers[2] = true;
					aiPlayersGroup[2].setText("AI");
				} else {
					groupAIPlayers[2] = false;
					aiPlayersGroup[2].setText("Player");
				}
			}
		});
		aiPlayersGroup[3].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!groupAIPlayers[3]) {
					groupAIPlayers[3] = true;
					aiPlayersGroup[3].setText("AI");
				} else {
					groupAIPlayers[3] = false;
					aiPlayersGroup[3].setText("Player");
				}
			}
		});
		
		playGroupMode = new TextButton("Play", buttonStyle);
		playGroupMode.setPosition(Gdx.graphics.getWidth() - (playGroupMode.getWidth() + 10), 10);
		groupModeStage.addActor(playGroupMode);
		playGroupMode.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Play Group Mode
				currentMenu = 0;
				Gdx.input.setInputProcessor(gameModeStage);
				GlobalVars.currentGameMode = 0;
				GlobalVars.ai = groupAIPlayers;
				setDone(true);
			}
		});
		
		
		for(int i = 0; i < aiPlayersClassic.length; i++) {
			aiPlayersClassic[i] = new TextButton("Player", aiChooseStyle);
			classicModeStage.addActor(aiPlayersClassic[i]);
		}
		aiPlayersClassic[0].setPosition(40, Gdx.graphics.getHeight() / 2 - aiPlayersClassic[0].getHeight() / 2);
		aiPlayersClassic[1].setPosition(Gdx.graphics.getWidth() - (aiPlayersClassic[1].getWidth() + 40), aiPlayersClassic[0].getY());
		
		aiPlayersClassic[0].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!classicAIPlayers[0]) {
					classicAIPlayers[0] = true;
					aiPlayersClassic[0].setText("AI");
				} else {
					classicAIPlayers[0] = false;
					aiPlayersClassic[0].setText("Player");
				}
			}
		});
		
		aiPlayersClassic[1].addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!classicAIPlayers[1]) {
					classicAIPlayers[1] = true;
					aiPlayersClassic[1].setText("AI");
				} else {
					classicAIPlayers[1] = false;
					aiPlayersClassic[1].setText("Player");
				}
			}
		});
		
		playClassicMode = new TextButton("Play", buttonStyle);
		playClassicMode.setPosition(Gdx.graphics.getWidth() - (playClassicMode.getWidth() + 10), 10);
		classicModeStage.addActor(playClassicMode);
		playClassicMode.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Play Classic Mode
				currentMenu = 0;
				Gdx.input.setInputProcessor(gameModeStage);
				GlobalVars.currentGameMode = 1;
				GlobalVars.ai = classicAIPlayers;
				setDone(true);
			}
		});
		
		
		backToModeSelect = new TextButton("Back", buttonStyle);
		backToModeSelect.setPosition(10, 10);
		backToModeSelect.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				// Go back to the Mode Select Menu
				currentMenu = 0;
				Gdx.input.setInputProcessor(gameModeStage);
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
			gameModeStage.act(delta);
			gameModeStage.draw();
		} else if(currentMenu == 1) {
			groupModeStage.act(delta);
			groupModeStage.draw();
		} else if(currentMenu == 2) {
			classicModeStage.act(delta);
			classicModeStage.draw();
		}
	}
	
	// Dispose
	@Override
	public void dispose() {
		gameModeStage.dispose();
		groupModeStage.dispose();
		classicModeStage.dispose();
		buttonFont.dispose();
	}
}
