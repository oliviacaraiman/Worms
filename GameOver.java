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
	private FinalScreenGameState fs;

	/**
	 * Constructeur de la classe GameOver
	 * @param container Le GameContainer du jeu.
	 * @param game L'instance StateBasedGame du jeu.
	 * @param finalScreen La classe FinalScreen associée.
	 */
	public GameOver(GameContainer container, StateBasedGame game, FinalScreenGameState finalScreen) {
		this.fs = finalScreen;
		nomGagnant = fs.getNomGagnant();
		nomPerdant = fs.getNomPerdant();
		java.awt.Font awtFont = new java.awt.Font("Berlin Sans FB", java.awt.Font.PLAIN, 32);
		font = new TrueTypeFont(awtFont, true);
		try {
			gameOverImage = new Image("src/main/ressources/GameOver/gameover_screen.jpg");


		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Ecrit le nom du gagnant et du perdant.
	 * @param container Le GameContainer du jeu.
	 * @param game L'instance StateBasedGame du jeu.
	 * @param g L'instance Graphics liée à la fenêtre.
	 */
	public void paintComponent(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(gameOverImage.getScaledCopy(1440, 1000), 0, 0);
		g.setFont(font);
		String s1 = "GAGNANT: " + nomGagnant;
		g.drawString(s1, 550, 200);
		String s2 = "PERDANT: " + nomPerdant;
		g.drawString(s2, 550, 240);
		String s3 = "Appuyez sur Echap ...";
		g.drawString(s3, 550, 320);
		
	}
	
	/**
	 * Met à jour le nom du gagnant.
	 * @param nom Le nom du gagnant.
	 */
	public void setNomGagnant(String nom){
		nomGagnant = nom;
	}
	
	/**
	 * Met à jour le nom du perdant.
	 * @param nom Le nom du perdant.
	 */
	public void setNomPerdant(String nom){
		nomPerdant = nom;
	}

}