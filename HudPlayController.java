import org.newdawn.slick.*;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HudPlayController implements ComponentListener {
	  private StateBasedGame game;
	  private TextField nomJoueur1;
	  private TextField nomJoueur2;
	  private MouseOverArea playButton;
	  private MouseOverArea creditsButton;
	  private Font font;
	  private Image playButtonImage;
	  private Image creditsButtonImage;
	  private MouseOverArea rulesButton;
	  private Image rulesButtonImage;
	  
	  
	  
	  
	 

	  public HudPlayController( GameContainer container,StateBasedGame game) {
	    this.game = game;
	    font=container.getDefaultFont();
	   
	    nomJoueur1=new TextField(container,font,550,100,150,50,this);
	    //nomJoueur1.setText(nomJoueur1.getText());
	    
		nomJoueur1.setTextColor(Color.blue);
		nomJoueur2=new TextField(container,font,550,200,150,50,this);
		nomJoueur2.setTextColor(Color.blue);
		//nomJoueur2.setText("joueur 2");
		
		nomJoueur2.setBorderColor( Color.white);
		nomJoueur1.setBorderColor( Color.white);
		
		try {
			playButtonImage=new Image("PLAY.png");
			creditsButtonImage=new Image("CREDITS.png");
			rulesButtonImage = new Image("RULES.png");
		} catch (Exception e) {
			
		}
		
		playButton=new MouseOverArea(container,playButtonImage,750,120,this);
		creditsButton=new MouseOverArea(container,creditsButtonImage,900,650,this);
		rulesButton = new MouseOverArea(container,rulesButtonImage,400,650,this);
	}
	  

public void paintComponent(GameContainer container, StateBasedGame game, Graphics g) {
	/*java.awt.Font stringFont = new  java.awt.Font( "SansSerif", java.awt.Font.PLAIN, 45 );   
	g.setFont( new TrueTypeFont(stringFont, true) );
	g.drawString( "WORMS", 720-g.getFont().getWidth("WORMS")/2, 10);*/
	
	//g.drawString("WORMS", 500, 10);
	g.setColor(Color.blue);
	g.setBackground(Color.white);
	g.setFont(font);
	playButton.render(container, g);
	creditsButton.render(container, g);
	rulesButton.render(container, g);
	g.drawString("Nom Joueur1", 400,100);
	nomJoueur1.render(container, g);
	g.drawString("Nom Joueur 2", 400,200);
	nomJoueur2.render(container, g);
}

public void componentActivated(AbstractComponent arg0) { //throws SlickException {
	if(arg0==playButton) {
		//new AppGameContainer( new Worms(), 1440,800, false).start()
		game.enterState(Worms.ID);
	} else if (arg0==creditsButton) {
		new FenetreCredits();
	}else if(arg0==rulesButton){
		new FenetreRegles();
	}
}

public TextField getNameJoueur1(){
	return nomJoueur1;
}


public TextField getNameJoueur2(){
	return nomJoueur2;
}










}

	  

