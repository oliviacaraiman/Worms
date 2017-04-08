import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;

public class ControlHud implements ComponentListener {
	
	private final int HEIGHT;
	private final int HEIGHT_TEXT;
	private final int WIDTH_TEXT;
	private final int x1;
	private final int x2;
	private final int x3;
	private final int x4;
	private final int x5;
	private final int x6;
	private TextField puissanceJet;
	private TextField angleJet;
	private Font font;
	private MouseOverArea leftButton;
	private MouseOverArea rightButton;
	private Image imleft;
	private Image imright;
	private int direction;
	private int xTranslate;
	
	public ControlHud(GameContainer container,float xDim) {
		HEIGHT=10;
		HEIGHT_TEXT=30;
		WIDTH_TEXT=80;
		x1=366;
		x2=487;
		x3=608;
		x4=729;
		x5=850;
		x6=971;
		xTranslate=0;
		font=container.getDefaultFont();
		puissanceJet=new TextField(container,font,x4,HEIGHT,WIDTH_TEXT,HEIGHT_TEXT);
		angleJet=new TextField(container,font,x6,HEIGHT,WIDTH_TEXT,HEIGHT_TEXT);
		try {
			imleft=new Image("src/main/ressources/PNG/Tiles/signLeft.png");
			imright=new Image("src/main/ressources/PNG/Tiles/signRight.png");
		} catch (Exception e) {
			
		}
		imleft.setAlpha(0.5f);
		leftButton=new MouseOverArea(container,imleft,x1,HEIGHT,this);
		rightButton=new MouseOverArea(container,imright,x2,HEIGHT,this);
		direction=1; //droite
	}
	
	public void paintComponent(GameContainer container,Graphics g) {
		g.translate(-xTranslate, 0); //permet au ControlHud de se déplacer avec la caméra
		g.setColor(Color.black);
		g.setFont(font);
		leftButton.render(container, g);
		rightButton.render(container, g);
		g.drawString("Puissance", x3,HEIGHT+HEIGHT_TEXT/3);
		puissanceJet.render(container, g);
		g.drawString("Angle", x5,HEIGHT+HEIGHT_TEXT/3);
		angleJet.render(container, g);
	}
	
	public int getPuissance() {
		int p;
		if(puissanceJet.getText().isEmpty()) {
			p=30;
		} else {
			p=Integer.parseInt(puissanceJet.getText());
		}
		return p;
	}
	
	public int getAngle() {
		int a;
		if(angleJet.getText().isEmpty()) {
			a=80;
		} else {
			a=Integer.parseInt(angleJet.getText());
		}
		return a;
	}

	public void componentActivated(AbstractComponent arg0) {
		if(arg0==leftButton) {
			imleft.setAlpha(1);
			imright.setAlpha(0.5f);
			direction=0;
		} else if (arg0==rightButton) {
			imleft.setAlpha(0.5f);
			imright.setAlpha(1);
			direction=1;
		}
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void translate(int x) {
		xTranslate=x;
	}
}
