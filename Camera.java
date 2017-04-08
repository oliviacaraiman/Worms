import org.newdawn.slick.*;

public class Camera {

	private Joueur j;
	private Worms w;
	
	public Camera(Joueur joueur,Worms w) {
		j=joueur;
		this.w=w;
	}
	
	public void render(float xmax,float xMapMax, Graphics g) {
		if(j.getX()>xmax/2 && j.getX()<xMapMax-xmax/2) { //si au milieu de la carte, on translate
			g.translate((int)(xmax/2-j.getX()),0);
			w.translate((int)(xmax/2-j.getX()));
		} else if(j.getX()<xmax/2) {
			g.translate(0, 0);
			w.translate(0);
		} else if(j.getX()>xMapMax-xmax/2) { //si tout à droite de l'écran, ne plus translater
			g.translate((int)(xmax-xMapMax),0);
			w.translate((int)(xmax-xMapMax));
		}
	}
	
	public void setJoueur(Joueur jc) {
		j=jc;
	}
}
