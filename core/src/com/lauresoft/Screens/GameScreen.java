package com.lauresoft.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.lauresoft.GameWorld.GameRenderer;
import com.lauresoft.GameWorld.GameWorld;
import com.lauresoft.ZBHelpers.InputHandler;

public class GameScreen implements Screen {
	private GameWorld gameWorld;
	private GameRenderer gameRenderer;
	private float runTime=0;

	public GameScreen(){
		System.out.println("GameScreen Attached");
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth/gameWidth);
		int midPointY = (int) (gameHeight/2);
		gameWorld = new GameWorld(midPointY);
		gameRenderer = new GameRenderer(gameWorld,(int)gameHeight,midPointY);
		
		Gdx.input.setInputProcessor(new InputHandler(gameWorld));
	}
	@Override
	public void render(float delta) 
	{
		runTime+=delta;
		gameWorld.update(delta);
		gameRenderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) 
	{
		System.out.println("Gamescreen - Resizing");	
	}

	@Override
	public void show() 
	{
		System.out.println("Gamescreen - show called");	
	}

	@Override
	public void hide() 
	{
		System.out.println("Gamescreen - hide called");	
	}

	@Override
	public void pause() 
	{
		System.out.println("Gamescreen - pause called");	
	}

	@Override
	public void resume() 
	{
		System.out.println("Gamescreen - resume called");	
	}

	@Override
	public void dispose() 
	{
		
	}

}
