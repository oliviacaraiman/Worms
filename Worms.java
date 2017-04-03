import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;

public class Worms extends BasicGame {

	private GameContainer container;
	private Map map;
	private static int dimX=1440;
	private static int dimY=800;

	private Joueur j1;
	private Joueur j2;
	private Joueur jCourant;
	private ControlPlayer controller;
	private TextNextTurn tnt;
	
	private TextField puissanceJet;
	
	public Worms() {
        super("Worms");
    }


    public void init(GameContainer container) throws SlickException {
    	this.container=container;
    	map=new Map();
    	j1=new Joueur("Albert",map);
    	j2=new Joueur("Anna",map);
    	jCourant=j1;
    	j1.init();
    	j2.init();
    	controller = new ControlPlayer(j1,j2);
    	container.getInput().addKeyListener(controller);
    	tnt=new TextNextTurn(dimX/2,dimY/4,j1);
    	/*puissanceJet=new TextField(container,container.getDefaultFont(),600,40,100,30);
    	System.out.println("Puissance Jet here");*/
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
    	map.render(0, 0);
    	j1.render(g);
    	j2.render(g);
    	tnt.paintComponent(g);
    	puissanceJet.render(container, g);
    }

    public void update(GameContainer container, int delta) throws SlickException {
    	jCourant.update(delta);
    }
    
    public void changeJoueurCourant() {
    	controller.setJoueurCourant();
    }
    
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
    	if (Input.KEY_ENTER==key) {
    		jCourant.stopMoving();
    		this.changeJoueurCourant();
    		tnt.setIsThere(true,jCourant);
    	} else if (Input.KEY_ESCAPE==key) {
            container.exit();
        }
    }
    
    public void keyPressed(int key, char c) {
    	controller.keyPressed(key, c);
    }

    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new Worms(), dimX, dimY, false).start();
    }

}
