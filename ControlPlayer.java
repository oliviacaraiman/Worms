import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class ControlPlayer implements KeyListener {
	private Joueur playerCourant;
	private Joueur playerSuivant;
	private ControlHud control;
	
	public ControlPlayer(Joueur playerC,Joueur playerS,ControlHud cont){
		this.playerCourant = playerC;
		this.playerSuivant = playerS;
		control=cont;
	}
	
	public void setJoueurCourant() {
		Joueur j =playerCourant;
		playerCourant=playerSuivant;
		playerSuivant=j;
	}
	
	public Joueur getJoueurCourant() {
		return playerCourant;
	}

	public void inputEnded() {
	}

	public void inputStarted() {
	}

	public boolean isAcceptingInput() {
		return false;
	}

	public void setInput(Input arg0) {
	}

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

	public void keyReleased(int key, char c) {
		switch (key) {
			case Input.KEY_DOWN:  playerCourant.setDy(0); break;
			case Input.KEY_LEFT: case Input.KEY_RIGHT: playerCourant.setDx(0); break;
		}
	}

}