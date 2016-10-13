package Tigress;

import java.util.Iterator;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This state is active prior to the Game starting. In this state, sound is
 * turned off, and the bounce counter shows '?'. The user can only interact with
 * the game by pressing the SPACE key which transitions to the Playing State.
 * Otherwise, all game objects are rendered and updated normally.
 * Transitions From (Initialization), GameOverState
 * Transitions To PlayingState
 */
class StartUpState extends BasicGameState {
	int timeX;
	int timeY;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setSoundOn(false);
		timeX = 0;
		timeY = 0;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		TigressGame bg = (TigressGame)game;
		
		/*bg.ball.render(g);
		for (Bang b : bg.explosions)
			b.render(g);*/
		/*if (bg.level == 1) {
			g.drawImage(ResourceManager.getImage(BounceGame.SPLASH_BANNER_RSC),
					100, 100);	
		} else if (bg.level == 2) {
			g.drawImage(ResourceManager.getImage(BounceGame.STARTUP_2_BANNER_RSC),
					150, 150);	
		} else {
			g.drawImage(ResourceManager.getImage(BounceGame.STARTUP_3_BANNER_RSC),
					150, 150);
		}*/
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		TigressGame bg = (TigressGame)game;

		if (input.isKeyDown(Input.KEY_SPACE))
			bg.enterState(TigressGame.PLAYINGSTATE);	
		
	}

	@Override
	public int getID() {
		return TigressGame.STARTUPSTATE;
	}
	
}