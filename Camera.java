import org.newdawn.slick.*;

public class Camera {

	private Joueur j;
	private Worms w;
	
	/**
	 * Constructeur de la classe Cam�ra, permettant de suivre le joueur courant sur l'�cran.
	 * @param joueur Le premier joueur courant.
	 * @param w L'instance de jeu Worms.
	 */
	public Camera(Joueur joueur,Worms w) {
		j=joueur;
		this.w=w;
	}
	
	/**
	 * Fait translater l'ensemble de ce qui est dessin� avec le d�placement du joueur courant.
	 * @param xmax La dimension en abscisse de la fen�tre.
	 * @param xMapMax La dimension en abscisse de la carte.
	 * @param g L'instance Graphics li�e � la fen�tre.
	 */
	public void render(float xmax,float xMapMax, Graphics g) {
		if(j.getX()>xmax/2 && j.getX()<xMapMax-xmax/2) { //si au milieu de la carte, on translate
			g.translate((int)(xmax/2-j.getX()),0);
			w.translate((int)(xmax/2-j.getX()));
		} else if(j.getX()<xmax/2) {
			g.translate(0, 0);
			w.translate(0);
		} else if(j.getX()>xMapMax-xmax/2) { //si tout � droite de l'�cran, ne plus translater
			g.translate((int)(xmax-xMapMax),0);
			w.translate((int)(xmax-xMapMax));
		}
	}
	
	/**
	 * Modifie le joueur courant.
	 * @param jc Le nouveau joueur courant.
	 */
	public void setJoueur(Joueur jc) {
		j=jc;
	}
}
