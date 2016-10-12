package Tigress;

import java.util.ArrayList;

import jig.Entity;
import jig.ResourceManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TigressGame extends StateBasedGame {
	
	public static final int STARTUPSTATE = 0;
	public static final int PLAYINGSTATE = 1;
	public static final int GAMEOVERSTATE = 2;
	
	/*
	public static final String BALL_BALLIMG_RSC = "bounce/resource/ball.png";
	public static final String PADDLE_PADDLEIMG_RSC = "bounce/resource/paddle.png";
	
	public static final String GAMEOVER_BANNER_RSC = "bounce/resource/gameover.png";
	public static final String STARTUP_2_BANNER_RSC = "bounce/resource/PressSpace_2.png";
	public static final String STARTUP_3_BANNER_RSC = "bounce/resource/PressSpace_3.png";
	public static final String SPLASH_BANNER_RSC = "bounce/resource/SplashScreen.png";
	public static final String YOUWIN_BANNER_RSC = "bounce/resource/YouWin.png";
	public static final String BANG_EXPLOSIONIMG_RSC = "bounce/resource/explosion.png";
	
	public static final String HITWALL_RSC = "bounce/resource/wall_hit.wav";
	public static final String HITPADDLE_RSC = "bounce/resource/paddle_hit.wav";
	public static final String HITBRICK_RSC = "bounce/resource/brick_hit.wav";
	public static final String LOSELIFE_RSC = "bounce/resource/lose_life.wav";
	public static final String EXPLOSION_RSC = "bounce/resource/explosion.wav";
	
	public static final String BRICK_BRICK_1_IMG_RSC = "bounce/resource/brick.png";
	public static final String BRICK_BRICK_2_1_IMG_RSC = "bounce/resource/brick_2_1.png";
	public static final String BRICK_BRICK_2_2_IMG_RSC = "bounce/resource/brick2_2.png";
	public static final String BRICK_BRICK_3_1_IMG_RSC = "bounce/resource/brick_3_1.png";
	public static final String BRICK_BRICK_3_2_IMG_RSC = "bounce/resource/brick_3_2.png";
	public static final String BRICK_BRICK_3_3_IMG_RSC = "bounce/resource/brick_3_3.png";
	*/

	public final int ScreenWidth;
	public final int ScreenHeight;
	
	int level;
	/*Ball ball;
	Paddle paddle;
	ArrayList<Bang> explosions;
	ArrayList<Brick> bricks;*/
	/**
	 * Create the BounceGame frame, saving the width and height for later use.
	 * 
	 * @param title
	 *            the window's title
	 * @param width
	 *            the window's width
	 * @param height
	 *            the window's height
	 */
	public TigressGame(String title, int width, int height) {
		super(title);
		ScreenHeight = height;
		ScreenWidth = width;

		Entity.setCoarseGrainedCollisionBoundary(Entity.AABB);
		//explosions = new ArrayList<Bang>(10);	
	}


	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new StartUpState());
		//addState(new GameOverState());
		//addState(new PlayingState());
		
		// the sound resource takes a particularly long time to load,
		// we preload it here to (1) reduce latency when we first play it
		// and (2) because loading it will load the audio libraries and
		// unless that is done now, we can't *disable* sound as we
		// attempt to do in the startUp() method.		
		/*ResourceManager.loadSound(HITWALL_RSC);	
		ResourceManager.loadSound(HITPADDLE_RSC);	
		ResourceManager.loadSound(HITBRICK_RSC);		
		ResourceManager.loadSound(LOSELIFE_RSC);
		ResourceManager.loadSound(EXPLOSION_RSC);

		// preload all the resources to avoid warnings & minimize latency...
		ResourceManager.loadImage(BALL_BALLIMG_RSC);
		ResourceManager.loadImage(GAMEOVER_BANNER_RSC);
		ResourceManager.loadImage(YOUWIN_BANNER_RSC);
		ResourceManager.loadImage(STARTUP_2_BANNER_RSC);
		ResourceManager.loadImage(STARTUP_3_BANNER_RSC);
		ResourceManager.loadImage(SPLASH_BANNER_RSC);
		ResourceManager.loadImage(BANG_EXPLOSIONIMG_RSC);
		ResourceManager.loadImage(PADDLE_PADDLEIMG_RSC);
		ResourceManager.loadImage(BRICK_BRICK_1_IMG_RSC);
		ResourceManager.loadImage(BRICK_BRICK_2_1_IMG_RSC);
		ResourceManager.loadImage(BRICK_BRICK_2_2_IMG_RSC);
		ResourceManager.loadImage(BRICK_BRICK_3_1_IMG_RSC);
		ResourceManager.loadImage(BRICK_BRICK_3_2_IMG_RSC);
		ResourceManager.loadImage(BRICK_BRICK_3_3_IMG_RSC);*/
		
		level = 1;
		/*ball = new Ball(ScreenWidth / 2, ScreenHeight / 2, .1f, .2f);
		paddle = new Paddle(ScreenWidth / 2, ScreenHeight - 13);
		bricks = new ArrayList<Brick>();	*/

	}
	
	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new TigressGame("Break Out!", 800, 600));
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	
}