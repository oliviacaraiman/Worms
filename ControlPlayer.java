import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class ControlPlayer implements KeyListener {
	private Player player;
	
	public ControlPlayer(Player player){
		this.player = player;
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		return false;
	}

	@Override
	public void setInput(Input arg0) {
	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
        case Input.KEY_UP:   
        	this.player.setDy(-1);
			break;
		case Input.KEY_LEFT:
			this.player.setDx(-1);
			break;
		case Input.KEY_DOWN:
			this.player.setDy(1);
			break;
		case Input.KEY_RIGHT:
			this.player.setDx(1);
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		  case Input.KEY_UP: case Input.KEY_DOWN:  player.setDy(0); break;
		  case Input.KEY_LEFT: case Input.KEY_RIGHT: player.setDx(0); break;
		  }
	}

}
