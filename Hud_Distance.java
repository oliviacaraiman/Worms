import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Hud_Distance {

		private Image contourBarre;
		private final float X_IMAGE;
		private final float Y_IMAGE;
		private final float X_BARRE;
		private final float Y_BARRE;
		private final float HEIGHT_BARRE;
		private final float WIDTH_MAX_BARRE;
		private final String nom;
		private final int xDim;
		private final int num;
		
		public Hud_Distance(int nume, int xDimen, String name) {
			num=nume;
			nom=name;
			xDim=xDimen;
			X_IMAGE=10+(num-1)*(xDim-20-256); //256 largeur de la barre
			Y_IMAGE=10;
			X_BARRE=X_IMAGE+12;
			Y_BARRE=Y_IMAGE+35;
			HEIGHT_BARRE=16;
			WIDTH_MAX_BARRE=232;
			try {
				contourBarre=new Image("src/main/ressources/Vie/enemy_health_bar_foreground_000.png");
			} catch (Exception e) {
				
			}
		}
		
		public void paintComponent(Graphics g,float fr) {
			//dessin de la barre de vie
			g.setColor(Color.blue);
			g.fillRect(X_BARRE,Y_BARRE,Math.max(0,fr*WIDTH_MAX_BARRE),HEIGHT_BARRE);
			g.drawImage(contourBarre, X_IMAGE,Y_IMAGE);
			
		}
	
}
