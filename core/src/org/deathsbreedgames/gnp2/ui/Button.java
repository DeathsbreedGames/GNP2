package org.deathsbreedgames.gnp2.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * @author Nicolás A. Ortega
 * @copyright DeathsbreedGames
 * @license GNU GPLv3
 * @year 2014
 * 
 * Description: This class is supposed to make button creation
 * for menus easier by doing the bulk of the work.
 * 
 */
public class Button extends TextButton {
	private Pixmap pixmap;
	private Skin skin;
	private BitmapFont font;
	private static TextButtonStyle style = new TextButtonStyle();
	
	public Button(String text, int width, int height, float scale) {
		super(text, style);
		skin = new Skin();
		
		pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("main", new Texture(pixmap));
		
		font = new BitmapFont();
		font.scale(scale);
		skin.add("default", font);
		
		style.up = skin.newDrawable("main", Color.BLACK);
		style.down = skin.newDrawable("main", Color.BLACK);
		style.over = skin.newDrawable("main", Color.GREEN);
		style.font = skin.getFont("default");
		skin.add("default", style);
		
		setText(text);
		setStyle(style);
	}
	
	public Button(String text, int width, int height, float scale, Color color1, Color color2) {
		super(text, style);
		skin = new Skin();
		
		pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("main", new Texture(pixmap));
		
		font = new BitmapFont();
		font.scale(scale);
		skin.add("default", font);
		
		style.up = skin.newDrawable("main", color1);
		style.down = skin.newDrawable("main", color1);
		style.checked = skin.newDrawable("main", color2);
		style.font = skin.getFont("default");
		skin.add("default", style);
		
		setText(text);
		setStyle(style);
	}
}
