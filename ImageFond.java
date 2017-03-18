import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.File;

public class ImageFond extends JPanel {

	private Image imgFond;
	public ImageFond() {
		
		try{
			//imgFond = ImageIO.read(new File("Fond.jpg"));
			imgFond= ImageIO.read(new File("src/Fond.jpg"));
		} catch(Exception e) {
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(imgFond!=null) {
			g.drawImage(imgFond,0,0,getWidth(),getHeight(),this);
		}
	}
}
