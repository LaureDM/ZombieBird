package com.lauresoft.zombiebird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lauresoft.Screens.GameScreen;
import com.lauresoft.ZBHelpers.AssetLoader;

public class ZBGame extends Game
{

	@Override
	public void create() 
	{
		System.out.println("Game created!");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override 
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}
}
