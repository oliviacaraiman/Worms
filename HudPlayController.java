import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

public class HudPlayController implements ComponentListener {
	private StateBasedGame game;
	private TextField nomJoueur1;
	private TextField nomJoueur2;
	private MouseOverArea playButton;
	private MouseOverArea creditsButton;
	private MouseOverArea rulesButton;
	private Font font;
	private Image playButtonImage;
	private Image creditsButtonImage;
	private Image rulesButtonImage;
	private Worms w;
	 
	  /**
	   * Constructeur de la classe HudPlayController. Il instancie les différents TextField et boutons qui seront sur le menu de départ.
	   * @param container Le GameContainer du jeu.
	   * @param game L'instance StateBasedGame du jeu.
	   * @param w L'instance Worms correspondant à la phase de jeu.
	   */
	public HudPlayController(GameContainer container,StateBasedGame game,Worms w) {
		this.game = game;
		font=container.getDefaultFont();
		
		nomJoueur1=new TextField(container,font,650,150,150,50,this);
		nomJoueur1.setTextColor(Color.blue);
		nomJoueur1.setText("Joueur 1");
		
		nomJoueur2=new TextField(container,font,650,250,150,50,this);
		nomJoueur2.setTextColor(Color.blue);
		nomJoueur2.setText("Joueur 2");
		
		nomJoueur2.setBorderColor( Color.white);
		nomJoueur1.setBorderColor( Color.white);
	
		try {
			playButtonImage=new Image("src/main/ressources/menu/play.png");
		    creditsButtonImage=new Image("src/main/ressources/menu/CREDITS.png");
		    rulesButtonImage=new Image("src/main/ressources/menu/RULES.png");
		} catch (Exception e) {}
			  
		playButton=new MouseOverArea(container,playButtonImage,850,165,this);
		creditsButton=new MouseOverArea(container,creditsButtonImage,900,650,this);
		rulesButton=new MouseOverArea(container,rulesButtonImage,400,650,this);
		this.w=w;
	}
	  
	/**
	 * Dessine les différents éléments du hud.
	 * @param container Le GameContainer du jeu.
	 * @param game L'instance StateBasedGame du jeu.
	 * @param g L'instance Graphics de la fenêtre.
	 */
	public void paintComponent(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.blue);
		g.setBackground(Color.white);
		g.setFont(font);
		playButton.render(container, g);
		creditsButton.render(container, g);
		rulesButton.render(container,g);
		g.drawString("Nom Joueur 1",500,165);
		nomJoueur1.render(container, g);
		g.drawString("Nom Joueur 2",500,265);
		nomJoueur2.render(container, g);
	}
	
	/**
	 * Réagit aux appuis sur les boutons.
	 * Si on appuie sur le bouton play, la phase de jeu se lance.
	 * Si on appuie sur le bouton crédit, la fenêtre de crédit s'ouvre.
	 * Si on appuie sur le bouton règles, la fenêtre donnant les règles du jeu s'ouvre.
	 */
	public void componentActivated(AbstractComponent arg0) {
	if(arg0==playButton) {
		game.enterState(Worms.ID);
		w.setNom(nomJoueur1.getText(),nomJoueur2.getText());
		} else if (arg0==creditsButton) {
			new FenetreCredits();
		} else if(arg0==rulesButton) {
			new FenetreRegles();
		}
	}
}