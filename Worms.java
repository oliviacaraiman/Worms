import org.newdawn.slick.*;

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
	private ControlHud control;
	private Camera cam;
	
	public Worms() {
        super("Worms");
    }


    public void init(GameContainer container) throws SlickException {
    	this.container=container;
    	map=new Map();
    	j1=new Joueur("Albert",map,dimX);
    	j2=new Joueur("Anna",map,dimX);
    	jCourant=j1;
    	j1.init();
    	j2.init();
    	control=new ControlHud(container,dimX);
    	controller = new ControlPlayer(j1,j2,control);
    	container.getInput().addKeyListener(controller);
    	tnt=new TextNextTurn(dimX/2,dimY/4,j1);
    	cam=new Camera(jCourant,this);
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
    	cam.render(dimX,map.getWidth(), g);
    	map.render(0, 0);
    	j1.render(g);
    	j2.render(g);
    	tnt.paintComponent(g);
    	control.paintComponent(container, g);
    }

    public void update(GameContainer container, int delta) throws SlickException {
    	jCourant.update(delta);
    }
    
    public void changeJoueurCourant() {
    	controller.setJoueurCourant();
    	jCourant=controller.getJoueurCourant();
    	cam.setJoueur(jCourant);
    }
    
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
    	if (Input.KEY_ENTER==key) {
    		jCourant.stopMoving();
    		jCourant.setDistanceParcourue(0);
    		jCourant.setGrenade(false);
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

    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new Worms(), dimX, dimY, false).start();
    }

}
