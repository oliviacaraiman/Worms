import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class Worms extends BasicGame {

	public Worms() {
        super("Leçon 1 :: Worms");
    }
	
	private GameContainer container;
	private TiledMap map;
	private static int dimX=1440;
	private static int dimY=800;
	private int ysol=384;
	private float x = 200, y =dimY-ysol;
	private Animation[] animations;
	private int direction = 0;
	private boolean moving = false;

    public void init(GameContainer container) throws SlickException {
    	this.container=container;
    	this.map= new TiledMap("src/main/ressources/map/map.tmx");
    	
    	SpriteSheet spriteSheet = new SpriteSheet("src/Spritesheets/spritesheet_players.png", 128, 256);
    	animations=new Animation[11];
    	for (int i=0;i<11;i++) {
    		animations[i]=new Animation();
    	}
        animations[0].addFrame(spriteSheet.getSprite(0, 0), 1000);
        animations[1].addFrame(spriteSheet.getSprite(1, 0), 1000);
        animations[2].addFrame(spriteSheet.getSprite(0, 1), 1000);
        animations[3].addFrame(spriteSheet.getSprite(1, 1), 1000);
        animations[4].addFrame(spriteSheet.getSprite(0, 2), 1000);
        animations[5].addFrame(spriteSheet.getSprite(1, 2), 1000);
        animations[6].addFrame(spriteSheet.getSprite(0, 3), 1000);
        animations[7].addFrame(spriteSheet.getSprite(0, 4), 1000);
        animations[8].addFrame(spriteSheet.getSprite(0, 5), 1000);
        animations[9].addFrame(spriteSheet.getSprite(0, 6), 1000);
        animations[10].addFrame(spriteSheet.getSprite(0, 7), 1000);
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.map.render(0,0);
    	g.drawAnimation(animations[direction + (moving ? 4 : 0)], x, y-256);
    }

    public void update(GameContainer container, int delta) throws SlickException {
    }
    
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE==key) {
            container.exit();
        }
    }
	
    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new Worms(), dimX, dimY, false).start();
    }
}
