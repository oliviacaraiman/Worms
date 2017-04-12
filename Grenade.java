import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.newdawn.slick.*;

public class Grenade {

	private Image grenade;
	private float xPosition;
	private float yPosition;
	private boolean isThere;

	/**
	 * Constructeur de la classe Grenade.
	 */
	public Grenade() {
		try {
			grenade=new SpriteSheet("src/main/ressources/Spritesheets/spritesheet_weapons.png",24,24).getSprite(2, 0);
		} catch (Exception e) {

		}
		xPosition = 0; // valeur d'initialisation sans importance
		yPosition = 0; // valeur d'initialisation sans importance
		isThere = false;
	}
	
	/**
	 * Dessine la grenade lorsqu'elle est lancée. Après avoir atterri sur le sol, elle disparait.
	 */
	public void paintComponent() {
		ActionListener disparitionGrenade=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIsThere(false);
			}
		};
		if (isThere) {
			grenade.drawCentered(xPosition, yPosition);
			Timer grenadeTimer=new Timer(1500,disparitionGrenade);
			grenadeTimer.setRepeats(false);
			grenadeTimer.start();
		}
	}
	
	/**
	 * Modifie la valeur de isThere, qui permet de savoir si la grenade a été lancée ou non.
	 * @param b La nouvelle valeur de "La grenade est lancée".
	 */
	public void setIsThere(boolean b) {
		isThere = b;
	}
	
	/**
	 * Met à jour la position de la grenade à intervalle de temps régulier.
	 * @param x La nouvelle abscisse de la grenade.
	 * @param y La nouvelle ordonnée de la grenade.
	 */
	public void setPosition(float x, float y) {
		//timer pour lancer la grenade
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xPosition = x;
				yPosition = y;
				try {
					Thread.sleep(15);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		};
		Timer myTimer = new Timer(1000, listener);
		myTimer.setInitialDelay(0);
		myTimer.setRepeats(false);
		myTimer.start();
	}
}
			

