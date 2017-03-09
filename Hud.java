import org.newdawn.slick.*;

public class Hud {

	private Image contourBarre;
	private final float X_IMAGE;
	private final float Y_IMAGE;
	private final float X_BARRE;
	private final float Y_BARRE;
	private final float HEIGHT_BARRE;
	private final float WIDTH_MAX_BARRE;
	private final String nom;
	private final int xDim;
	private final int yDim;
	private final int num;
	
	public Hud(int nume, int xDimen, int yDimen, String name) throws SlickException {
		num=nume;
		nom=name;
		xDim=xDimen;
		yDim=yDimen;
		X_IMAGE=10+(num-1)*(xDim-20-256);
		Y_IMAGE=10;
		X_BARRE=X_IMAGE+12;
		Y_BARRE=Y_IMAGE+8;
		HEIGHT_BARRE=16;
		WIDTH_MAX_BARRE=232;
		
	}
	
	public void init() throws SlickException {
		contourBarre=new Image("Vie/enemy_health_bar_foreground_000.png");
	}
	
	public void render(Graphics g,float fr) {
		//dessin de la barre de vie
		g.setColor(Color.red);
		g.fillRect(X_BARRE,Y_BARRE,fr*WIDTH_MAX_BARRE,HEIGHT_BARRE);
		g.drawImage(contourBarre, X_IMAGE,Y_IMAGE);
		
		//dessin du nom
		g.setColor(Color.black);
		g.drawString(nom, X_IMAGE, Y_IMAGE+50);
	}
	
}
