import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class StateGame extends StateBasedGame {
	
	private static int dimX;
	private static int dimY;
	
	/**
	 * Constructeur de la classe StateGame, qui gère les différentes phases de jeu.
	 * Il définit le titre et les dimensions de la fenêtre de jeu.
	 */
	public StateGame() {
		super("Worms");
		dimX=1440;
		dimY=800;
	}
	
	
	/**
	 * Ajoute les différentes phases de jeu à l'instance courante.
	 */
	public void initStatesList(GameContainer container) throws SlickException {
		Worms m=new Worms(dimX,dimY);
	    this.addState(new MainScreenGameState(m));
	    this.addState(m); 
	}
	
	public static void main(String[]args) throws SlickException {
		new AppGameContainer(new StateGame(),dimX,dimY, false).start();
	}
}