import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Worms extends BasicGameState /*implements GameState, Game*/{
	private StateBasedGame game;
	private GameContainer container;
	private Map map;
	private static int dimX=1440;
	private static int dimY=800;
	public static final int ID =2;

	private Joueur j1;
	private Joueur j2;
	private Joueur jCourant;
	private ControlPlayer controller;
	private TextNextTurn tnt;
	private ControlHud control;
	private Camera cam;
	private HudPlayController hudPlay;
	
	


    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	this.container=container;
    	this.game = game;
    	map=new Map();
    	this.hudPlay = new HudPlayController(container, game);
    	j1=new Joueur(hudPlay.getNameJoueur1().getText(),map,dimX);
    	j2=new Joueur(hudPlay.getNameJoueur2().getText(),map,dimX);
    	jCourant=j1;
    	j1.init();
    	j2.init();
    	control=new ControlHud(container,dimX);
    	controller = new ControlPlayer(j1,j2,control);
    	container.getInput().addKeyListener(controller);
    	tnt=new TextNextTurn(dimX/2,dimY/4,j1);
    	cam=new Camera(jCourant,this);
    }
    

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	cam.render(dimX,map.getWidth(), g);
    	map.render(0, 0);
    	j1.render(g);
    	j2.render(g);
    	tnt.paintComponent(g);
    	control.paintComponent(container, g);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	jCourant.update(delta);
    	if (jCourant.getDistanceParcourue() >= jCourant.DISTANCE_MAX && jCourant.grenadeLancee && !jCourant.isJumping()
    			&& !jCourant.isMoving()){
    		changeJoueurCourant();
    		tnt.setIsThere(true, jCourant);
    		
    	}
    }
    
    public void changeJoueurCourant() {
    	controller.setJoueurCourant();
    	jCourant=controller.getJoueurCourant();
    	jCourant.setDistanceParcourue(0);
    	jCourant.grenadeLancee = false;
    	cam.setJoueur(jCourant);
    }
    
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
    	if (Input.KEY_ENTER==key) {
    		jCourant.stopMoving();
//    		jCourant.setDistanceParcourue(0);
//    		jCourant.setGrenade(false);
    		this.changeJoueurCourant();
    		tnt.setIsThere(true,jCourant);
    	} else if (Input.KEY_ESCAPE==key) {
            container.exit();
        }
    }
    
    public void keyPressed(int key, char c) {
    	controller.keyPressed(key, c);
    }
    
    public void translate(int x) {
    	j1.translateHud(x);
    	j2.translateHud(x);
    	tnt.translate(x);
    	control.translate(x);
    }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}


	


	


	


	


	

}
	