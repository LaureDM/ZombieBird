package com.lauresoft.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.lauresoft.ZBHelpers.AssetLoader;

public class Bird 
{
	//can hold x and y component
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float rotation;
	private int width;
	private int height;
	
	private Circle circle;
	
	private Boolean isAlive;
	public Bird(float posx, float posy, int width, int height)
	{
		this.width=width;
		this.height=height;
		position = new Vector2(posx,posy);
		velocity = new Vector2(0,0);
		acceleration = new Vector2(0,460);
		circle = new Circle();
		isAlive=true;
	}
	
	public void update(float delta)
	{
		velocity.add(acceleration.cpy().scl(delta));
		if(velocity.y>200)
			velocity.y=200;
		
		if(position.y<-13)
		{
			position.y=-13;
			velocity.y=0;
		}
		position.add(velocity.cpy().scl(delta));
		circle.set(position.x+9,position.y+6,6.5f);
		
		//stijgende draai
		if(velocity.y<0)
		{
			rotation-=600*delta;
			if(rotation<-20)
				rotation=-20;
		}
		
		//vallende draai
		if(isFalling()||!isAlive)
		{
			rotation+=480*delta;
			if(rotation>90)
				rotation=90;
		}
	}
	
	public void onClick()
	{
		if(isAlive)
		{
			AssetLoader.flap.play();
			velocity.y = -140; 
		
		}
	}
	
	public boolean isFalling()
	{
		return velocity.y>110;
	}
	
	public boolean shouldntFlap()
	{
		return velocity.y>70||!isAlive;
	}
	
	 public float getX() {
	        return position.x;
	    }

	    public float getY() {
	        return position.y;
	    }

	    public float getWidth() {
	        return width;
	    }

	    public float getHeight() {
	        return height;
	    }

	    public float getRotation() {
	        return rotation;
	    }
	    
	    public Circle getCircle()
	    {
	    	return circle;
	    }

		public boolean isAlive() {
			// TODO Auto-generated method stub
			return isAlive;
		}

		public void die() {
			isAlive=false;
			velocity.y=0;
			// TODO Auto-generated method stub
			
		}

		public void decelerate() {
			// TODO Auto-generated method stub
			acceleration.y=0;
		}

		public void onRestart(int y) {
			// TODO Auto-generated method stub
			  rotation = 0;
		        position.y = y;
		        velocity.x = 0;
		        velocity.y = 0;
		        acceleration.x = 0;
		        acceleration.y = 460;
		        isAlive = true;
		}
	    
	    
}
