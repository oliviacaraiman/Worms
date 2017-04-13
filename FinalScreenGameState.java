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
	private GameOver gameOverScreen; 
	private Worms worms;
	public String nomGagnant;
	public String nomPerdant;
	
	/**
	 * Constructeur de la classe
	 * @param w : la classe Worms a laquelle cette classe est associee
	 */
	public FinalScreenGameState(Worms w){
		this.worms = w;
	}

	/**
	 * initialise la fenetre GameOver.
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		gameOverScreen = new GameOver(container, game,this);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		gameOverScreen.paintComponent(container, game, g);
	}

	/**
	 * met a jour les noms des personnages(gagnant/perdant)
	 */
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException {
		this.nomPerdant = worms.nomPerdant;
		this.nomGagnant = worms.nomGagnant;
		gameOverScreen.setNomGagnant(nomGagnant);
		gameOverScreen.setNomPerdant(nomPerdant);
	}
	
	
	public void setNomGagnant(String nom){
		nomGagnant = nom;
	}
	public void setNomPerdant(String nom){
		nomPerdant = nom;
	}
	public String getNomGagnant(){
		return nomGagnant;
	}
	public String getNomPerdant(){
		return nomPerdant;
	}
	

	@Override
	public int getID() {
		return ID;
	};
}
