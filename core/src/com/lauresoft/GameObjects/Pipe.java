package com.lauresoft.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

	private Random random;
	private Rectangle skullUp,skullDown,barUp,barDown;
	
	private static int VERTICAL_PIPE_GAP =45;
	private static int SKULL_WIDTH = 24;
	private static int SKULL_HEIGHT = 11;
	
	private float groundY;
	
	private boolean isScored;
	public Pipe(float x, float y, int width, int height, float scrollSpeed,float groundY) 
	{
		super(x, y, width, height, scrollSpeed);
		random = new Random();
		skullUp = new Rectangle();
		skullDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();
		this.groundY = groundY;
		isScored=false;
	
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x,position.y+height+VERTICAL_PIPE_GAP,width,groundY-(position.y+height+VERTICAL_PIPE_GAP));

        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,SKULL_WIDTH, SKULL_HEIGHT);
	}
	
	@Override
	public void reset(float newX)
	{
		super.reset(newX);
		super.height = random.nextInt(90)+15;
		isScored=false;
	}


	public boolean collides(Bird bird)
	{
	    if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getCircle(), barUp)
                    || Intersector.overlaps(bird.getCircle(), barDown)
                    || Intersector.overlaps(bird.getCircle(), skullUp) || Intersector
                        .overlaps(bird.getCircle(), skullDown));
        }
        return false;
	}
	public Rectangle getSkullUp() {
		return skullUp;
	}

	public Rectangle getSkullDown() {
		return skullDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}

	public float getGroundY() {
		return groundY;
	}
	
	public boolean isScored()
	{
		return isScored;
	}

	public void setScored(boolean s)
	{
		isScored=s;
	}

	public void onRestart(float x, float scrollSpeed) {
		// TODO Auto-generated method stub
	       velocity.x = scrollSpeed;
	        reset(x);
	}
	
}
