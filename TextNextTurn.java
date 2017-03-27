import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

public class TextNextTurn {

	private final float X_POSITION;
	private final float Y_POSITION;
	private boolean isThere;
	private Joueur jCourant;
	TrueTypeFont ttf;
	
	public TextNextTurn(float x, float y, Joueur jc) {
		X_POSITION=x; //valeur au centre
		Y_POSITION=y; //valeur au centre
		jCourant=jc;
		isThere=false;
		java.awt.Font font = new java.awt.Font("Distant Galaxy", java.awt.Font.PLAIN, 32);
		ttf = new TrueTypeFont(font, true);
	}
	
	public void paintComponent(Graphics g) {
		if(isThere) {
			g.setFont(ttf);
			String s="Au tour de "+jCourant.getNom();
			g.drawString(s,X_POSITION-g.getFont().getWidth(s)/2,Y_POSITION);
		}
	}
	
	public void setIsThere(boolean b,Joueur j) {
		jCourant=j;
		isThere=b;
	}
	
}
