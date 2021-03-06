package com.lauresoft.ZBHelpers;

import com.badlogic.gdx.InputProcessor;
import com.lauresoft.GameObjects.Bird;
import com.lauresoft.GameWorld.GameWorld;

public class InputHandler implements InputProcessor {

	private GameWorld world;
	private Bird bird;
	
	public InputHandler(GameWorld world)
	{
		this.world=world;
		bird = world.getBird();
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(world.isReady())
		{
			world.start();
		}
		bird.onClick();
		if(world.isGameOver()||world.isHighScore())
			world.restart();
		
		return true; //touch is handled
		
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
