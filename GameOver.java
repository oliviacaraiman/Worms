

import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.StateBasedGame;
import javax.imageio.*;
import java.io.File;

public class GameOver{
	
	private StateBasedGame game;
	private TextField gameOverText;
	private TextField nomGagnantText;
	private TextField nomPerdantText;
	private String nomPerdant;
	private String nomGagnant;
	private Font font;
	private Image gameOverImage;
	
	public GameOver(GameContainer container, StateBasedGame game){
		this.game = game;
		gameOverText = new TextField(container, font, 550, 100, 150, 50);
		gameOverText.setText("GAME OVER");
		StateGame finalGame = (StateGame) game;
		nomGagnant = "fewkjnfwj";
		nomPerdant = finalGame.getNomPerdant();
		nomGagnant = finalGame.getNomGagnant();
		
		
		
		try {
			gameOverImage = new Image("src/gameOver.jpg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(GameContainer container, StateBasedGame game, Graphics g) {
		g.setBackground(Color.black);
		g.drawImage(gameOverImage, 500, 150);
		nomGagnantText = new TextField(container, font, 400, 600, 150, 500);
		nomGagnantText.setTextColor(Color.white);
		nomGagnantText.setBackgroundColor(Color.blue);
		nomGagnantText.setText("GAGNANT : "+nomGagnant);
		nomPerdantText = new TextField(container, font, 400, 100, 150, 50);
		nomPerdantText.setText("PERDANT : "+nomPerdant);
//		String s = " GAME OVER ";
//		g.drawString(s, 400, 100);
		//gameOverText.render(container, g);
	}

}
