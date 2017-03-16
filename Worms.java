import org.newdawn.slick.*;

public class Worms extends BasicGame {

	private GameContainer container;
	private Map map;
	private static int dimX=1440;
	private static int dimY=800;

	private Joueur j1;
	private Joueur j2;
	private ControlPlayer controller;
	
	public Worms() {
        super("Worms");
    }


    public void init(GameContainer container) throws SlickException {
    	this.container=container;
    	map=new Map();
    	j1=new Joueur("Albert",map);
    	j2=new Joueur("Anna",map);
    	j1.init();
    	j2.init();
    	controller = new ControlPlayer(j1);
    	container.getInput().addKeyListener(controller);
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
    	map.render(0, 0, 0);
    	j1.render(g);
    	j2.render(g);
    }

    public void update(GameContainer container, int delta) throws SlickException {
    	j1.update(delta);
    }
    
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
        if (Input.KEY_ESCAPE==key) {
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
