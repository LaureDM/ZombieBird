package com.lauresoft.GameWorld;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.lauresoft.GameObjects.Bird;
import com.lauresoft.GameObjects.Grass;
import com.lauresoft.GameObjects.Pipe;
import com.lauresoft.GameObjects.ScrollHandler;
import com.lauresoft.ZBHelpers.AssetLoader;

public class GameRenderer {

	
	private GameWorld gameWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	
	//Extra
	private int gameHeight;
	private int midPointY;
	
	//Game objects
	private Bird bird;
	private Grass grass1,grass2;
	private Pipe pipe1,pipe2,pipe3;
	private ScrollHandler scroller;
	//Assets
	private TextureRegion bg, grass;
	private Animation birdAnimation;
	private TextureRegion birdMid, birdDown, birdUp;
	private TextureRegion skullUp, skullDown, bar;
	
	public GameRenderer(GameWorld gameWorld, int gameHeight, int midPointY) 
	{
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		this.gameWorld = gameWorld;
		cam = new OrthographicCamera();
		cam.setToOrtho(true,136,gameHeight);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		initGameObjects();
		initAssets();
		
	}

	public void initGameObjects()
	{
		bird = gameWorld.getBird();
		scroller = gameWorld.getScroller();
		grass1 = scroller.getGrass1();
		grass2 = scroller.getGrass2();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
	}
	
	public void initAssets()
	{
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.animation;
		birdMid = AssetLoader.bird;
		birdUp = AssetLoader.birdUp;
		birdDown = AssetLoader.birdDown;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;
	}
	public void render(float runTime) 
	{
		//black background
		System.out.println("GameRenderer - flap");
		
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		//bg color
		shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        //grass color
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);
	
        //dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);
        
        //end
        shapeRenderer.end();
        
        batch.begin();
        
 
        batch.disableBlending();
        
        batch.draw(bg,0,midPointY+23,136,43);
        drawGrass();
        drawPipes();
        
        batch.enableBlending();
        drawSkulls();
        if(bird.shouldntFlap())
        {
        batch.draw(birdMid, bird.getX(), bird.getY(), bird.getWidth()/2.0f,bird.getHeight()/2.0f,bird.getWidth(),bird.getHeight(),1,1,bird.getRotation());	
        }
        else
        {
        batch.draw(birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth()/2.0f, bird.getHeight()/2.0f,bird.getWidth(),bird.getHeight(),1,1,bird.getRotation());
        }
        
        if(gameWorld.isReady())
        {
            AssetLoader.shadow.draw(batch, "Touch me", (136 / 2)
                    - (42), 76);
            // Draw text
            AssetLoader.text.draw(batch, "Touch me", (136 / 2)
                    - (42 - 1), 75);
        }
        else
        	{
        	if (gameWorld.isGameOver()||gameWorld.isHighScore()) 
            {
        		if(gameWorld.isGameOver())
        		{
                AssetLoader.shadow.draw(batch, "Game Over", 25, 56);
                AssetLoader.text.draw(batch, "Game Over", 24, 55);
                AssetLoader.shadow.draw(batch, "High Score:", 23, 106);
                AssetLoader.text.draw(batch, "High Score:", 22, 105);

                String highScore = AssetLoader.getHighScore() + "";

                // Draw shadow first
                AssetLoader.shadow.draw(batch, highScore, (136 / 2)
                        - (3 * highScore.length()), 128);
                // Draw text
                AssetLoader.text.draw(batch, highScore, (136 / 2)
                        - (3 * highScore.length() - 1), 127);
            } else {
                AssetLoader.shadow.draw(batch, "High Score!", 19, 56);
                AssetLoader.text.draw(batch, "High Score!", 18, 55);
            }
                AssetLoader.shadow.draw(batch, "Try again?", 23, 76);
                AssetLoader.text.draw(batch, "Try again?", 24, 75);    
            }
	}
        
        String score = gameWorld.getScore()+"";
        AssetLoader.shadow.draw(batch, gameWorld.getScore()+"",(136/2)-(3*score.length()) , 12);
        AssetLoader.text.draw(batch,gameWorld.getScore()+"",(136/2)-(3*score.length()-1),11);
       
        batch.end();
     
        
	}
	
	public void drawGrass()
	{
		batch.draw(grass, grass1.getX(), grass1.getY(), grass1.getWidth(), grass1.getHeight());
		batch.draw(grass, grass2.getX(), grass2.getY(), grass2.getWidth(), grass2.getHeight());
	}
	
	public void drawSkulls()
	{
		  batch.draw(skullUp, pipe1.getX() - 1,
	                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
	        batch.draw(skullDown, pipe1.getX() - 1,
	                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

	        batch.draw(skullUp, pipe2.getX() - 1,
	                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
	        batch.draw(skullDown, pipe2.getX() - 1,
	                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

	        batch.draw(skullUp, pipe3.getX() - 1,
	                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
	        batch.draw(skullDown, pipe3.getX() - 1,
	                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
	}
	
	public void drawPipes()
	{
		 batch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
	                pipe1.getHeight());
	        batch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
	                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

	        batch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
	                pipe2.getHeight());
	        batch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
	                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

	        batch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
	                pipe3.getHeight());
	        batch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
	                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
	}
	
}