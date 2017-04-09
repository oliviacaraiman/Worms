import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class StateGame extends StateBasedGame {
	
	public static void main(String[]args) throws SlickException{
		new AppGameContainer(new StateGame(), 1440,800, false).start();
	}
	public StateGame() {
		super("StateGame");
	}
	public void initStatesList(GameContainer container) throws SlickException {
	    this.addState(new MainScreenGameState());
	    this.addState(new Worms());
	    
	  }

}
