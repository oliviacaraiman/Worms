
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver {

	private String nomPerdant;
	private String nomGagnant;
	private Image gameOverImage;
	private TrueTypeFont font;
	private StateBasedGame game;
	private FinalScreenGameState fs;
	private GameContainer container;

	/**
	 * constructeur de la classe GameOver
	 * @param container 
	 * @param game
	 * @param finalScreen la classe FinalScreen associee
	 */
	public GameOver(GameContainer container, StateBasedGame game, FinalScreenGameState finalScreen) {
		this.game = game;
		this.container = container;
		this.fs = finalScreen;
		nomGagnant = fs.getNomGagnant();
		nomPerdant = fs.getNomPerdant();
		java.awt.Font awtFont = new java.awt.Font("Berlin Sans FB", java.awt.Font.PLAIN, 32);
		font = new TrueTypeFont(awtFont, true);
		try {
			gameOverImage = new Image("src/gameover_screen.jpg");


		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * ecriture des noms des personnages
	 * @param container
	 * @param game
	 * @param g
	 */
	public void paintComponent(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(gameOverImage.getScaledCopy(1440, 1000), 0, 0);
		g.setFont(font);
		String s1 = "GAGNANT: " + nomGagnant;
		g.drawString(s1, 550, 200);
		String s2 = "PERDANT: " + nomPerdant;
		g.drawString(s2, 550, 240);
		
	}

	public void setNomGagnant(String nom){
		nomGagnant = nom;
	}
	public void setNomPerdant(String nom){
		nomPerdant = nom;
	}

}

