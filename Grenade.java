import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.newdawn.slick.*;

public class Grenade {

	private Image grenade;
	private float xPosition;
	private float yPosition;
	private boolean isThere;

	public Grenade() {
		try {
			grenade=new SpriteSheet("src/main/ressources/Spritesheets/spritesheet_weapons.png",24,24).getSprite(2, 0);
		} catch (Exception e) {

		}
		xPosition = 0; // valeur lambda
		yPosition = 0; // valeur lambda
		isThere = false;
	}

	public void paintComponent() {
		ActionListener disparitionGrenade = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIsThere(false);
			}
		};
		if (isThere) {
			grenade.drawCentered(xPosition, yPosition);
			Timer grenadeTimer = new Timer(1500,disparitionGrenade);
			grenadeTimer.setRepeats(false);
			grenadeTimer.start();
		}
	}

	public void setIsThere(boolean b) {
		isThere = b;
	}

	public void setPos(float x, float y) {
		xPosition = x;
		yPosition = y;
		System.out.println(xPosition + " " + yPosition);
	}

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
			

