import org.newdawn.slick.*;
import org.newdawn.slick.command.Control;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.*;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class FinalScreenGameState extends BasicGameState {

	public static final int ID = 3;
	private StateBasedGame game;
	private StateGame myGame;
	private GameOver gameOverScreen; 
	private Worms worms;
	public String nomGagnant;
	public String nomPerdant;
	
	public FinalScreenGameState(){
		this.nomGagnant = nomGagnant;
		this.nomPerdant = nomPerdant;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		gameOverScreen = new GameOver(container, game);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		gameOverScreen.paintComponent(container, game, g);
		
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
	}

	@Override
	public int getID() {
		return ID;
	};
}
