import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState { 
		
	public static final int ID = 1;
	
	  private Image background;
	  private HudPlayController hudMenu;
	  private Worms w;
	  private Music theme;
	  
	  /**
	   * Constructeur de la classe MainScreenGameState, qui correspond à la phase de menu de début de jeu.
	   * @param w L'instance de Worms du jeu.
	   */
	  public MainScreenGameState(Worms w) {
		  this.w=w;
	  }
	 
	  /**
	   * Initialise l'image de fond et les différents contrôles du menu.
	   * @param container Le GameContainer du jeu.
	   * @param game L'instance StateBasedGame du jeu.
	   */
	  public void init(GameContainer container, StateBasedGame game) {
		  try {
			    background =  new Image("src/main/ressources/menu/Fond2.jpeg");
			    theme=new Music("src/main/ressources/Musique/theme.ogg");
		  } catch (Exception e) {}
		  hudMenu = new HudPlayController(container,game,w); 
		  theme.loop();
	  }
	  
	  /**
	   * Dessine le menu.
	   * @param container Le GameContainer du jeu.
	   * @param game L'instance StateBasedGame du jeu.
	   * @param g L'instance Graphics liée à la fenêtre.
	   */
	  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		  background.draw(0, 0, container.getWidth(), container.getHeight());
		  hudMenu.paintComponent(container,game, g);
	  }
	  
	  /**
	   * Met à jour le menu.
	   * @param container Le GameContainer du jeu.
	   * @param game L'instance StateBasedGame du jeu.
	   * @param delta	Le pas de temps.
	   */
	  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
	  
	  /**
	   * Retourne l'identifiant de la phase pour la classe.
	   * @return L'identifiant de la classe MainScreenBasedGame.
	   */
	  public int getID() {
	    return ID;
	  }
}