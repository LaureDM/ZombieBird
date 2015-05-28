package com.lauresoft.GameObjects;

import com.lauresoft.GameWorld.GameWorld;
import com.lauresoft.ZBHelpers.AssetLoader;

public class ScrollHandler 
{
	private Pipe pipe1, pipe2,pipe3;
	private Grass grass1, grass2;
	
	private static int SCROLL_SPEED = -59;
	private static int PIPE_GAP = 49;
	
	private GameWorld gameWorld;

	public ScrollHandler(GameWorld gameWorld,float y)
	{
		pipe1 = new Pipe(210,0,22,60,SCROLL_SPEED,y);
		pipe2 = new Pipe(pipe1.getTailX()+PIPE_GAP,0,22,70,SCROLL_SPEED,y);
		pipe3 = new Pipe(pipe2.getTailX()+PIPE_GAP,0,22,60,SCROLL_SPEED,y);
		
		grass1 = new Grass(0,y,143,11,SCROLL_SPEED);
		grass2 = new Grass(grass1.getTailX(),y,143,11,SCROLL_SPEED);
		
		this.gameWorld = gameWorld;
	}
	
	public Pipe getPipe1() {
		return pipe1;
	}

	public Pipe getPipe2() {
		return pipe2;
	}

	public Pipe getPipe3() {
		return pipe3;
	}

	public Grass getGrass1() {
		return grass1;
	}

	public Grass getGrass2() {
		return grass2;
	}

	public void update(float delta)
	{
	grass1.update(delta);
	grass2.update(delta);
	pipe1.update(delta);
	pipe2.update(delta);
	pipe3.update(delta);
	
	if(pipe1.isScrolledLeft())
		pipe1.reset(pipe3.getTailX()+PIPE_GAP);
	else if(pipe2.isScrolledLeft())
		pipe2.reset(pipe1.getTailX()+PIPE_GAP);
	else if(pipe3.isScrolledLeft())
		pipe3.reset(pipe2.getTailX()+PIPE_GAP);
	
	
	if(grass1.isScrolledLeft())
		grass1.reset(grass2.getTailX());
	else if(grass2.isScrolledLeft())
		grass2.reset(grass1.getTailX());
	
	
}

	public boolean collides(Bird bird) 
	{
		//de vogel is voorbij de helft van de paal
		if(!pipe1.isScored() && pipe1.getX()+(pipe1.getWidth()/2)<bird.getX()+bird.getWidth())
		{
		
			addScore(1);
			pipe1.setScored(true);
			AssetLoader.coin.play();
		}
		else if(!pipe2.isScored() && pipe2.getX()+(pipe2.getWidth()/2)<bird.getX()+bird.getWidth())
		{
		
			addScore(1);
			pipe2.setScored(true);
			AssetLoader.coin.play();
		}
		else if(!pipe3.isScored() && pipe3.getX()+(pipe3.getWidth()/2)<bird.getX()+bird.getWidth())
		{
		
			addScore(1);
			pipe3.setScored(true);
			AssetLoader.coin.play();
		}
		return (pipe1.collides(bird)||pipe2.collides(bird)||pipe3.collides(bird));
	}
	
	public void addScore(int incre)
	{
		gameWorld.addScore(incre);
	}

	public void stop() 
	{
		grass1.stop();
		grass2.stop();
		pipe1.stop();
		pipe2.stop();
		pipe3.stop();
	}

	public void onRestart() {
		// TODO Auto-generated method stub
		  grass1.onRestart(0, SCROLL_SPEED);
	        grass2.onRestart(grass1.getTailX(), SCROLL_SPEED);
	        pipe1.onRestart(210, SCROLL_SPEED);
	        pipe2.onRestart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
	        pipe3.onRestart(pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
	}
}
