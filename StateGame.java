import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {
	
	public String nomGagnant;
	public String nomPerdant;
	private static int dimX =1440;
	private static int dimY = 800;

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new StateGame(), 1440, 800, false).start();
	}

	public StateGame() {
		super("Worms");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		Worms w=new Worms(dimX,dimY);
	    this.addState(new MainScreenGameState(w));
	    this.addState(w); 
	    this.addState(new FinalScreenGameState(w));
	}

	public String getNomGagnant() {
		return nomGagnant;
	}

	public String getNomPerdant() {
		return nomPerdant;
	}

	public void setNoms(String nom1, String nom2){
		nomGagnant = nom1;
		nomPerdant = nom2;
	}
}
