package com.lauresoft.ZBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader 
{
	public static Texture texture;
	public static TextureRegion bg, grass;
	
	public static Animation animation;
	public static TextureRegion bird, birdDown, birdUp;
	
	public static TextureRegion skullUp, skullDown, bar;
	
	public static Sound dead;
	public static Sound flap;
	public static Sound coin;
	
	public static BitmapFont shadow,text;
	
	public static Preferences pref;
	
	public static void load()
	{
		//elke afbeelding moet geflipt worden omdat we met een y down systeem werken
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg = new TextureRegion(texture,0,0,136,43);
		bg.flip(false, true);
		
		grass = new TextureRegion(texture,0,43,143,11);
		grass.flip(false, true);
		
		birdDown = new TextureRegion(texture,136,0,17,12);
		birdDown.flip(false, true);
		
		bird = new TextureRegion(texture, 153, 0, 17, 12);
	    bird.flip(false, true);
	    
	    birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);
        
        TextureRegion[] birds = { birdDown, bird, birdUp };
        animation = new Animation(0.06f, birds);
        animation.setPlayMode(PlayMode.LOOP_PINGPONG);
        
        //de palen, langs boven en onder
        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);
        
        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
        
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f,-.25f);
        text = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        text.setScale(.25f,-.25f);
        
        pref= Gdx.app.getPreferences("ZombieBird");
        if(!pref.contains("HighScore"))
        {
        	pref.putInteger("HighScore", 0);
        }
	}
	
	public static void dispose()
	{
		texture.dispose();
		dead.dispose();
		flap.dispose();
		coin.dispose();
		shadow.dispose();
		text.dispose();
	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setHighScore(int val) {
	    pref.putInteger("HighScore", val);
	    pref.flush();
	}

	// Retrieves the current high score
	public static int getHighScore() {
	    return pref.getInteger("HighScore");
	}
}
