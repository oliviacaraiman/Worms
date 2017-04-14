import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class FinalScreenGameState extends BasicGameState {

	public static final int ID = 3;
	private GameOver gameOverScreen; 
	private Worms worms;
	public String nomGagnant;
	public String nomPerdant;
	private GameContainer container;
	
	/**
	 * Constructeur de la classe FinalScreenGameState.
	 * @param w La classe Worms a laquelle cette classe est associee.
	 */
	public FinalScreenGameState(Worms w){
		this.worms = w;
	}

	/**
	 * Initialise la fenetre GameOver.
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container=container;
		gameOverScreen = new GameOver(container, game,this);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		gameOverScreen.paintComponent(container, game, g);
	}

	/**
	 * Met a jour les noms des personnages(gagnant/perdant).
	 */
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException {
		this.nomPerdant = worms.nomPerdant;
		this.nomGagnant = worms.nomGagnant;
		gameOverScreen.setNomGagnant(nomGagnant);
		gameOverScreen.setNomPerdant(nomPerdant);
	}
	
	/**
	 * Réagit à la pression des touches.
	 * L'appui sur ECHAP quitte le jeu.
	 */
	public void keyPressed(int key, char c) {
		if (Input.KEY_ESCAPE==key) {
            container.exit();
        }
    }
	
	/**
	 * Retourne le nom du joueur gagnant.
	 * @return Le nom du joueur gagnant.
	 */
	public String getNomGagnant(){
		return nomGagnant;
	}
	/**
	 * Retourne le nom du joueur perdant.
	 * @return Le nom du joueur perdant.
	 */
	public String getNomPerdant(){
		return nomPerdant;
	}
	
	/**
     * Retourne l'identifiant de la phase pour la classe.
     * @return L'identifiant de la classe FinalScreenGameState.
     */
	public int getID() {
		return ID;
	}
}