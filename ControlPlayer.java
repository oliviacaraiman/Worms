import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class ControlPlayer implements KeyListener {
	private Joueur playerCourant;
	private Joueur playerSuivant;
	private ControlHud control;
	
	/**
	 * Constructeur de la classe ControlPlayer, permettant de gérer la réponse aux entrées clavier.
	 * @param playerC Le joueur courant.
	 * @param playerS Le joueur suivant.
	 * @param cont Le ControlHud du jeu.
	 */
	public ControlPlayer(Joueur playerC,Joueur playerS,ControlHud cont){
		this.playerCourant = playerC;
		this.playerSuivant = playerS;
		control=cont;
	}
	
	/**
	 * Change le joueur courant.
	 */
	public void setJoueurCourant() {
		Joueur j =playerCourant;
		playerCourant=playerSuivant;
		playerSuivant=j;
	}
	
	/**
	 * Renvoie le joueur courant.
	 * @return Le joueur courant.
	 */
	public Joueur getJoueurCourant() {
		return playerCourant;
	}

	public void inputEnded() {}

	public void inputStarted() {}

	public boolean isAcceptingInput() {return false;}

	public void setInput(Input arg0) {}
	
	/**
	 * Gère la réaction aux entrées clavier (touches directionnelles et touche D).
	 */
	public void keyPressed(int key, char c) {
		switch (key) {
        case Input.KEY_UP:   
        	playerCourant.setDy(-0.3f);
        	playerCourant.setJumping(true);
			break;
		case Input.KEY_LEFT:
			playerCourant.setDx(-0.5f);
			break;
		case Input.KEY_DOWN:
			playerCourant.setDy(0.5f);
			break;
		case Input.KEY_RIGHT:
			playerCourant.setDx(0.5f);
			break;
		case Input.KEY_D: //pour tester la destruction du décor
			float[] dest={0,0};
			if(!playerCourant.grenadeLancee) {
				dest=playerCourant.lancerGrenade(control.getAngle(),control.getPuissance(),control.getDirection());
				playerCourant.changeLife((int)dest[0],(int)dest[1]);
				playerSuivant.changeLife((int)dest[0],(int)dest[1]);
			}
			break;
		}
	}

	/**
	 * Gère le relâchement des touches du clavier (arrêt du joueur).
	 */
	public void keyReleased(int key, char c) {
		switch (key) {
			case Input.KEY_DOWN:  playerCourant.setDy(0); break;
			case Input.KEY_LEFT: case Input.KEY_RIGHT: playerCourant.setDx(0); break;
		}
	}

}