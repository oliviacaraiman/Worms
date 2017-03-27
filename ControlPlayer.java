import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class ControlPlayer implements KeyListener {
	private Joueur player;
	
	public ControlPlayer(Joueur player){
		this.player = player;
	}
	
	public void setJoueurCourant(Joueur player) {
		this.player=player;
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
        	player.setDy(-1);
			break;
		case Input.KEY_LEFT:
			player.setDx(-1);
			break;
		case Input.KEY_DOWN:
			player.setDy(1);
			break;
		case Input.KEY_RIGHT:
			player.setDx(1);
			break;
		case Input.KEY_M: //pour tester la diminution de la vie
			player.setVie(player.getVie()-10);
			break;
		case Input.KEY_D: //pour tester la destruction du décor
			float[] dest={0,0};
			dest=player.lancerGrenade(0.5f, 75f);
			player.destroy((int)dest[0],(int)dest[1]);
			break;
		}
	}

	public void keyReleased(int key, char c) {
		switch (key) {
			case Input.KEY_UP: case Input.KEY_DOWN:  player.setDy(0); break;
			case Input.KEY_LEFT: case Input.KEY_RIGHT: player.setDx(0); break;
		}
	}

}