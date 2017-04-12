import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class StateGame extends StateBasedGame {
	
	private static int dimX;
	private static int dimY;
	
	/**
	 * Constructeur de la classe StateGame, qui g�re les diff�rentes phases de jeu.
	 * Il d�finit le titre et les dimensions de la fen�tre de jeu.
	 */
	public StateGame() {
		super("Worms");
		dimX=1440;
		dimY=800;
	}
	
	
	/**
	 * Ajoute les diff�rentes phases de jeu � l'instance courante.
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