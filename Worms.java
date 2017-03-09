import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;


public class Worms extends BasicGame {

	public Worms() {
        super("Worms");
    }
	
	private GameContainer container;
	private TiledMap map;
	private Player player1 = new Player("Alex");
	private Player player2 = new Player("Tom");
	private static int dimX=1440;
	private static int dimY=800;
	private int ysol=384;
	private ControlPlayer controller = new ControlPlayer(this.player1);

     public void init(GameContainer container) throws SlickException {
    	this.container=container;
    	this.player1.init();
    	this.player2.init();
    	this.map= new TiledMap("src/main/ressources/map/map.tmx");
    	//ControlPlayer controller = new ControlPlayer(this.player);
    	container.getInput().addKeyListener(controller);
    	
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.map.render(0,0);
    	this.player1.render(g);
    	this.player2.render(g);
    }

    public void update(GameContainer container, int delta) throws SlickException {
    	this.player1.update(delta);
    //	this.player2.update(delta);
    	
    }
    

    @Override
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
    }
    
    @Override
    public void keyPressed(int key, char c) {
    	controller.keyPressed(key, c);
    }
    
    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new Worms(), dimX, dimY, false).start();
    }
}
