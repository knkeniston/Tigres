package Tigress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jig.Collision;
import jig.ResourceManager;
import jig.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;


/**
 * This state is active when the Game is being played. In this state, sound is
 * turned on, the bounce counter begins at 0 and increases until 10 at which
 * point a transition to the Game Over state is initiated. The user can also
 * control the ball using the WAS & D keys.
 * Transitions From StartUpState
 * Transitions To GameOverState
 */
class PlayingState extends BasicGameState {
	int lives;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		lives = 3;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setSoundOn(true);
		TigressGame bg = (TigressGame)game;
		
		
		/*bg.bricks.clear();
		
		if (bg.level == 1) {
			for (int i = 1; i <= 4; i++) {
				bg.bricks.add(new Brick(i * (bg.ScreenWidth / 5), 200, bg.level));
			}
			bg.ball.setVelocity(new Vector(.1f, .2f));
		}
		if (bg.level == 2) {
			for (int i = 1; i <= 5; i++) {
				bg.bricks.add(new Brick(i * (bg.ScreenWidth / 6), 100, bg.level));
			}
			for (int i = 1; i <= 4; i++) {
				bg.bricks.add(new Brick(i * (bg.ScreenWidth / 5), 250, bg.level));
			}
			bg.ball.setVelocity(new Vector(.2f, .3f));
		} else if (bg.level == 3) {
			for (int i = 1; i <= 5; i++) {
				bg.bricks.add(new Brick(i * (bg.ScreenWidth / 6), 50, bg.level));
			}
			for (int i = 1; i <= 4; i++) {
				bg.bricks.add(new Brick(i * (bg.ScreenWidth / 5), 200, bg.level));
			}
			for (int i = 1; i <= 3; i++) {
				bg.bricks.add(new Brick(i * (bg.ScreenWidth / 4), 350, bg.level));
			}
			bg.ball.setVelocity(new Vector(.3f, .4f));
		}
		bg.ball.setPosition(bg.ScreenWidth / 2, bg.ScreenHeight / 2);*/
	}
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		TigressGame bg = (TigressGame)game;
		
		g.drawImage(ResourceManager.getImage(TigressGame.BACKGROUND_IMG_RSC),
				0, 0);	
		
		bg.tigress.render(g);
		bg.poacher.render(g);
		for (Cub c : bg.cubs) 
			c.render(g);
		for (Flower f : bg.flowers) 
			f.render(g);
		for (Meat m : bg.meats) 
			m.render(g);
		
		g.drawString("Lives: " + lives, 10, 50);
		g.drawString("Level: " + bg.level, 10, 30);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		TigressGame bg = (TigressGame)game;
		
		keyPresses(input, bg, delta);
	
		bg.tigress.update(delta);
		bg.poacher.update(delta);
		for (Cub c : bg.cubs) 
			c.update(delta);
		
		// tigress collision with cubs
		if (!bg.tigress.holdingCub()) {
			for (Cub c : bg.cubs) {
				Collision coll = bg.tigress.collides(c);
				if (coll != null) {
					bg.tigress.setRescueCub(c);
					break;
				}
			}
		}
		
		// poacher collision with tigress or cubs
		Collision poacherTigress = bg.tigress.collides(bg.poacher);
		Collision poacherCub = null;
		for (Cub c : bg.cubs) {
			Collision coll = bg.poacher.collides(c);
			if (coll != null) {
				bg.tigress.setRescueCub(null);
				c.removeImage(ResourceManager.getImage(c.getCurImage()));
				bg.cubs.remove(c);
				poacherCub = coll;
				break;
			}
		}
		
		if (poacherTigress != null || poacherCub != null) {
			lives -= 1;
			bg.tigress.setPosition(bg.ScreenWidth - 50, bg.ScreenHeight - 50);
			bg.poacher.setPosition(50, 50);
		}
		
		
		/*
		// Collision between ball and paddle
		Collision paddleCol = bg.ball.collides(bg.paddle);
		if (paddleCol != null && timeLastCol <= 0) {
			float ballX = bg.ball.getX();
			float paddleX = bg.paddle.getX();
			float diffX = Math.abs(paddleX - ballX);
			float oldSpeed = bg.ball.getVelocity().length();
			float newX = oldSpeed * (diffX / 100);
			float newY = (float)Math.sqrt(Math.pow(oldSpeed, 2) - Math.pow(newX, 2));
			if (ballX < paddleX) {
				bg.ball.setVelocity(new Vector(-1 * newX, -1 * newY));
			} else {
				bg.ball.setVelocity(new Vector(newX, -1 * newY));
			}
			ResourceManager.getSound(BounceGame.HITPADDLE_RSC).play();
			timeLastCol = 10;
		}
		timeLastCol--;
		
		// Collision between ball and brick
		ArrayList<Brick> toRemove = new ArrayList<Brick>();
		for (Brick b : bg.bricks) {
			Collision brickCol = bg.ball.collides(b);
			boolean cont = true;
			if (timeBricks.containsKey(b) && timeBricks.get(b) > 0) {
				cont = false;
			}
			if (brickCol != null && cont) { 
				bg.ball.bounce(0);
				b.decHealth();
				timeBricks.put(b, 10);
				if (b.getHealth() == 0) {
					toRemove.add(b);
					bg.explosions.add(new Bang(b.getX(), b.getY()));
				} else {
					ResourceManager.getSound(BounceGame.HITBRICK_RSC).play();
				}
			}
		}
		for (Brick b : timeBricks.keySet()) {
			timeBricks.put(b, timeBricks.get(b) - 1);
		}
		for (Brick b : toRemove) {
			bg.bricks.remove(b);
		}
		
		// Collision between ball and wall
		boolean bounced = false;		
		if ((bg.ball.getCoarseGrainedMaxX() > bg.ScreenWidth
				|| bg.ball.getCoarseGrainedMinX() < 0) && timeX <= 0) {
			timeX = 10;
			ResourceManager.getSound(BounceGame.HITWALL_RSC).play();
			bg.ball.bounce(90);
			bounced = true;
		} else if (bg.ball.getCoarseGrainedMinY() < 0 && timeY <= 0) {
			timeY = 10;
			ResourceManager.getSound(BounceGame.HITWALL_RSC).play();
			bg.ball.bounce(0);
			bounced = true;
		}
		if (bg.ball.getCoarseGrainedMaxY() > bg.ScreenHeight) {
			ResourceManager.getSound(BounceGame.LOSELIFE_RSC).play();
			this.lives -= 1;
			bg.ball.setPosition(bg.ScreenWidth / 2, bg.ScreenHeight / 2);
			bg.ball.setLifeWait();
		}
		timeX--;
		timeY--;
		
		//Updating animations
		if (bounced) {
			bounces++;
		}
		bg.ball.update(delta);
		bg.paddle.update(delta);
		
		// check if there are any finished explosions, if so remove them
		for (Iterator<Bang> i = bg.explosions.iterator(); i.hasNext();) {
			if (!i.next().isActive()) {
				i.remove();
			}
		}
		
		// Change levels
		if (bg.bricks.size() == 0) {
			bg.level++;
			if (bg.level == 4) {
				game.enterState(BounceGame.GAMEOVERSTATE, new EmptyTransition(), new HorizontalSplitTransition());
			} else {
				game.enterState(BounceGame.STARTUPSTATE, new EmptyTransition(), new HorizontalSplitTransition());
			}
		}
		*/

		checkLives(game, bg);
		
	}
	
	private void keyPresses(Input input, TigressGame bg, int delta) {		
		// Control user input
		if (input.isKeyDown(Input.KEY_LEFT)) 
			bg.tigress.setVelocity(new Vector(-.3f, 0));
		else if (input.isKeyDown(Input.KEY_RIGHT)) 
			bg.tigress.setVelocity(new Vector(.3f, 0f));
		else if (input.isKeyDown(Input.KEY_UP)) 
			bg.tigress.setVelocity(new Vector(0f, -.3f));
		else if (input.isKeyDown(Input.KEY_DOWN)) 
			bg.tigress.setVelocity(new Vector(0f, .3f));
		else 
			bg.tigress.setVelocity(new Vector(0f, 0f));
		
		// if space pressed, tigress drops cub
		if (input.isKeyDown(Input.KEY_SPACE) && bg.tigress.holdingCub()) 
			bg.tigress.dropCub();
		
	}
	
	private void checkLives(StateBasedGame game, TigressGame bg) {
		// Game over state if no lives left
		if (lives <= 0) {
			//((GameOverState)game.getState(TigressGame.GAMEOVERSTATE)).setUserScore(bounces);
			bg.level = 1;
			lives = 3;
			game.enterState(TigressGame.GAMEOVERSTATE);
		}
	}

	@Override
	public int getID() {
		return TigressGame.PLAYINGSTATE;
	}
	
}