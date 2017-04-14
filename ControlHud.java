import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;

public class ControlHud implements ComponentListener {
	
	private final int HEIGHT;
	private final int HEIGHT_TEXT;
	private final int WIDTH_TEXT;
	private final int x1;
	private final int x2;
	private final int x3;
	private final int x4;
	private final int x5;
	private final int x6;
	private TextField puissanceJet;
	private TextField angleJet;
	private Font font;
	private MouseOverArea leftButton;
	private MouseOverArea rightButton;
	private Image imleft;
	private Image imright;
	private int direction;
	private int xTranslate;
	
	/**
	 * Constructeur de la classe ControlHud, permettant de personnaliser le lancer de grenade.
	 * @param container Le GameContainer du jeu.
	 * @param xDim La dimension en abscisse de la fenêtre.
	 */
	public ControlHud(GameContainer container,float xDim) {
		HEIGHT=10;
		HEIGHT_TEXT=30;
		WIDTH_TEXT=80;
		x1=366;
		x2=487;
		x3=608;
		x4=729;
		x5=850;
		x6=971;
		xTranslate=0;
		font=container.getDefaultFont();
		puissanceJet=new TextField(container,font,x4,HEIGHT,WIDTH_TEXT,HEIGHT_TEXT);
		angleJet=new TextField(container,font,x6,HEIGHT,WIDTH_TEXT,HEIGHT_TEXT);
		try {
			imleft=new Image("src/main/ressources/PNG/Tiles/signLeft.png");
			imright=new Image("src/main/ressources/PNG/Tiles/signRight.png");
		} catch (Exception e) {
			
		}
		imleft.setAlpha(0.5f);
		leftButton=new MouseOverArea(container,imleft,x1,HEIGHT,this);
		rightButton=new MouseOverArea(container,imright,x2,HEIGHT,this);
		direction=1; //droite
	}
	
	/**
	 * Dessine les différents éléments du Hud.
	 * @param container Le GameContainer du jeu.
	 * @param g L'instance Graphics liée à la fenêtre.
	 */
	public void paintComponent(GameContainer container,Graphics g) {
		g.translate(-xTranslate, 0); //permet au ControlHud de se déplacer correctement avec la caméra. Il y a sinon un décalage entre l'image des éléments (boutons par ex.) et leur vraie position (zone de clic).
		g.setColor(Color.black);
		g.setFont(font);
		leftButton.render(container, g);
		rightButton.render(container, g);
		g.drawString("Puissance", x3,HEIGHT+HEIGHT_TEXT/3);
		puissanceJet.render(container, g);
		g.drawString("Angle", x5,HEIGHT+HEIGHT_TEXT/3);
		angleJet.render(container, g);
	}
	
	/**
	 * Renvoie la vitesse de lancer de la grenade, en écartant les valeurs non souhaitées.
	 * @return La vitesse de la grenade.
	 */
	public int getPuissance() {
		int p;
		boolean isNumber=true;
		try {
			Integer.parseInt(puissanceJet.getText());
		} catch (Exception e) {
			isNumber=false;
		}
		if(!isNumber || Integer.parseInt(puissanceJet.getText())<=15) {
			p=15;
		} else {
			p=Integer.parseInt(puissanceJet.getText());
		}
		return p;
	}
	
	/**
	 * Renvoie l'angle de lancer de la grenade, en écartant les valeurs non souhaitées.
	 * @return L'angle de la grenade.
	 */
	public int getAngle() {
		int a;
		boolean isNumber=true;
		try {
			Integer.parseInt(angleJet.getText());
		} catch (Exception e) {
			isNumber=false;
		}
		if(!isNumber || Integer.parseInt(angleJet.getText())<0 || Integer.parseInt(angleJet.getText())>85) {
			a=80;
		} else {
			a=Integer.parseInt(angleJet.getText());
		}
		return a;
	}

	/**
	 * Réagit au clic sur les boutons.
	 */
	public void componentActivated(AbstractComponent arg0) {
		if(arg0==leftButton) {
			imleft.setAlpha(1);
			imright.setAlpha(0.5f);
			direction=0;
		} else if (arg0==rightButton) {
			imleft.setAlpha(0.5f);
			imright.setAlpha(1);
			direction=1;
		}
	}
	
	/**
	 * Renvoie la direction de lancer de la grenade.
	 * @return La direction de lancer de la grenade.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Translate les différents éléments du hud à une certaine abscisse.
	 * @param x L'abscisse à laquelle les éléments sont translatés.
	 */
	public void translate(int x) {
		xTranslate=x;
	}
	
	/**
	 * Vide les zones de texte.
	 */
	public void emptyTextField() {
		puissanceJet.setText("");
		angleJet.setText("");
	}
}
