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
	
	public void paintComponent(Graphics g) {
		if(isThere) {
			g.setColor(Color.black);
			g.setFont(ttf);
			String s="Au tour de "+jCourant.getNom();
			g.drawString(s,X_POSITION-xTranslate-g.getFont().getWidth(s)/2,Y_POSITION);
			timer.schedule(new RemindTask(), 1000); //timer:fait disparaitre le text apres 1 sec
		}
	}
	
	
	public void setIsThere(boolean b,Joueur j) {
		jCourant=j;
		isThere=b;
	}
	
	public void translate(int x) {
		xTranslate=x;
	}
}