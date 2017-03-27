import org.newdawn.slick.*;

public class Grenade {
	
	private Image grenade;
	private float xPosition;
	private float yPosition;
	private boolean isThere;
	
	public Grenade() {
		try {
			grenade=new SpriteSheet("src/main/ressources/Spritesheets/spritesheet_weapons.png",24,24).getSprite(2, 0);
		} catch(Exception e) {
			
		}
		xPosition=0; //valeur lambda
		yPosition=0; //valeur lambda
		isThere=false;
	}
	
	public void paintComponent() {
		if(isThere) {
			//g.drawImage(grenade,xPosition,yPosition);
			grenade.drawCentered(xPosition,yPosition);
		}
	}
	
	public void setIsThere(boolean b) {
		isThere=b;
	}
	
	public void setPosition(float x,float y) {
		xPosition=x;
		yPosition=y;
	}
}
