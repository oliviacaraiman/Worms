import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;


public class TextNextTurn {

	private float X_POSITION;
	private float Y_POSITION;
	private int xTranslate;
	private boolean isThere;
	private Joueur jCourant;
	private Timer timer;
	TrueTypeFont ttf;
	
	/**
	 * Constructeur de la classe TextNextTurn, affichant le nom du joueur dont c'est le d�but du tour.
	 * @param x L'abscisse du texte � afficher.
	 * @param y L'ordonn�e du texte � afficher.
	 * @param jc Le Joueur dont c'est le d�but du tour.
	 */
	public TextNextTurn(float x, float y, Joueur jc) {
		X_POSITION=x; //valeur au centre
		Y_POSITION=y; //valeur au centre
		xTranslate=0;
		jCourant=jc;
		isThere=false;
		java.awt.Font font = new java.awt.Font("Distant Galaxy", java.awt.Font.PLAIN, 32);
		ttf = new TrueTypeFont(font, true);
		timer = new Timer();
		
	}
	
	class RemindTask extends TimerTask{
		public void run() {
			setIsThere(false, jCourant);
		}
	}
	
	/**
	 * Affiche le nom du joueur dont c'est le d�but du tour pendant 1 seconde.
	 * @param g L'instance Graphics reli�e � la fen�tre.
	 */
	public void paintComponent(Graphics g) {
		if(isThere) {
			g.setColor(Color.black);
			g.setFont(ttf);
			String s="Au tour de "+jCourant.getNom();
			g.drawString(s,X_POSITION-xTranslate-g.getFont().getWidth(s)/2,Y_POSITION);
			timer.schedule(new RemindTask(), 1000); //timer:fait disparaitre le text apres 1 sec
		}
	}
	
	/**
	 * Modifie la valeur de isThere, qui permet de savoir si le texte doit s'afficher.
	 * Change le joueur courant.
	 * @param b La nouvelle valeur de isThere, signifiant "Le texte doit s'afficher".
	 * @param j Le nouveau joueur courant.
	 */
	public void setIsThere(boolean b,Joueur j) {
		jCourant=j;
		isThere=b;
	}
	
	/**
	 * Translate le texte � dessiner � une certaine abscisse.
	 * @param x L'abscisse � laquelle le texte est translat�.
	 */
	public void translate(int x) {
		xTranslate=x;
	}
}