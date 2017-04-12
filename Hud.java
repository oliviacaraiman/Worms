import org.newdawn.slick.*;

public class Hud {

	private Image contourBarre;
	private float X_IMAGE;
	private float Y_IMAGE;
	private float X_BARRE;
	private float Y_BARRE;
	private final float HEIGHT_BARRE;
	private final float WIDTH_MAX_BARRE;
	private String nom;
	private final int xDim;
	private final int num;
	private int xTranslate; //pour la translation avec la caméra
	
	/**
	 * Le constructeur de la classe Hud, affichant différentes informations sur un joueur.
	 * @param nume Le numéro du Joueur lié au hud.
	 * @param xDimen La dimension en abscisse de la fenêtre.
	 * @param name Le nom du Joueur.
	 */
	public Hud(int nume, int xDimen, String name) {
		num=nume;
		nom=name;
		xDim=xDimen;
		X_IMAGE=10+(num-1)*(xDim-20-256); //256 largeur de la barre
		Y_IMAGE=10;
		X_BARRE=X_IMAGE+12;
		Y_BARRE=Y_IMAGE+8;
		HEIGHT_BARRE=16;
		WIDTH_MAX_BARRE=232;
		xTranslate=0;
		try {
			contourBarre=new Image("src/main/ressources/Vie/enemy_health_bar_foreground_000.png");
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Dessine les différents éléments du Hud.
	 * @param g L'instance Graphics liée à la fenêtre.
	 * @param fr La fraction de vie restante au Joueur.
	 */
	public void paintComponent(Graphics g,float frVie,float frEnd) {
		//dessin de la barre de vie
		g.setColor(Color.red);
		g.fillRect(X_BARRE-xTranslate,Y_BARRE,Math.max(0,frVie*WIDTH_MAX_BARRE),HEIGHT_BARRE);
		g.drawImage(contourBarre, X_IMAGE-xTranslate,Y_IMAGE);
		
		//dessin de la barre d'endurance
		g.setColor(Color.blue);
		g.fillRect(X_BARRE-xTranslate,Y_BARRE+27,Math.max(0,frEnd*WIDTH_MAX_BARRE),HEIGHT_BARRE);
		g.drawImage(contourBarre, X_IMAGE-xTranslate,Y_IMAGE+27);
		
		//dessin du nom
		g.setColor(Color.black);
		g.drawString(nom, X_IMAGE-xTranslate, Y_IMAGE+70);
	}
	
	/**
	 * Translate les éléments du hud à une certaine abscisse.
	 * @param x L'abscisse à laquelle les éléments sont translatés.
	 */
	public void translate(int x) {
		xTranslate=x;		
	}
	
	/**
	 * Modifie le nom du personnage affiché.
	 * @param a Le nouveau nom du Joueur.
	 */
	public void setNom(String a) {
		nom=a;
	}
}
