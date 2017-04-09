
import org.newdawn.slick.*;
import org.newdawn.slick.command.Control;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.*;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.imageio.ImageIO;

public class MainScreenGameState extends BasicGameState { 
		
	public static final int ID = 1;
	
	  private Image background;
	  private StateBasedGame game;
	  private HudPlayController hudMenu;
	  
	 
	 

	 
	  public void init(GameContainer container, StateBasedGame game) throws SlickException {
	    this.game = game;
	    this.background =  new Image("Fond.jpg");
	    hudMenu = new HudPlayController(container,game);
	 
	
	    
	  }

	 
	
	  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	    background.draw(0, 0, container.getWidth(), container.getHeight());
	    //g.setColor(Color.white);
	    //g.drawString("Appuyer sur une touche", 300, 300);
	    hudMenu.paintComponent(container,game, g );
	    
	  }

	 
	  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	  }

	 
	// public void mouseReleased(int index, int h, int w){
		 
	 //if (playbutton.pressed) {
	   
	    //game.enterState(Worms.ID);
	  //}
	 //}
	 

	
	  public int getID() {
	    return ID;
	  }



	




	
	
	   
	  
}
