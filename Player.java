import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class Player {
	
	private float x , y;
	private Animation[] animations;
	private int direction;
	private float dx, dy;
	// private Map map;
	private String nom;
	private int X_BEGIN;
	private int Y_BEGIN;
	private static int num=1;
	private boolean moving;
	private Hud hud;
	private final float LIFE_MAX;
	private float life;
	
//	private int xPerso;
//	private int yPerso;
	
	private final int xDim;
	private final int yDim;
	
	public Player(String nom) {
		//initialisation des variables simples
		this.nom = nom;
		xDim = 200;
		yDim = 416;
		LIFE_MAX=100;
		life=LIFE_MAX;
//		this.x = 200;
//		this.y = 416;
		this.direction = 2;
		this.dx = 0;
		this.dy = 0;
		
		//différence entre les deux personnages
		//direction=2*(num-1); 
		X_BEGIN=100+(num-1)*(xDim-200-128); //200 pour l'écart, 128 car largeur perso
		Y_BEGIN=800-384;//hauteur du sol
		this.x = X_BEGIN;
		this.y = Y_BEGIN;
		
		//Création du HUD
		//c'est eclipse qui a demande le try/catch
        try {
			hud = new Hud(num,xDim,yDim,nom);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //Augmentation du numéro du joueur
        System.out.println(num);
		num++;
	}

	public void init() throws SlickException {

		SpriteSheet spriteSheet = new SpriteSheet("src/Spritesheets/spritesheet_players.png", 128, 256);
		animations = new Animation[11];
		for (int i = 0; i < 11; i++) {
			animations[i] = new Animation();
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

	public void render(Graphics g) throws SlickException {

		// ombre personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(x + 19, y - 8, 90, 16);
		// dessin du personnage
		g.drawAnimation(animations[direction + (isMoving() ? 4 : 0)], x, y - 256);
	//	hud.render(g,this.getPourcentVie());
	}

	public void update(int delta) throws SlickException {
		// mouvement du personnage
		if (this.isMoving()) {
			updateDirection();
			this.x = this.x + .3f * delta * dx;
			this.y = this.y + .3f * delta * dy;
		}
	}
	
	
	private void updateDirection() {
		if (dx > 0 && dx >= Math.abs(dy)) {
			direction = 3;
		} else if (dx < 0 && -dx >= Math.abs(dy)) {
			direction = 1;
		} else if (dy < 0) {
			direction = 0;
		} else if (dy > 0) {
			direction = 2;
		}
	}
	
	public void setDirection(int direction) { 
		  this.direction = direction;
		  switch (direction) {
		  case 0: dx =  0; dy = -1; break;
		  case 1: dx = -1; dy =  0; break;
		  case 2: dx =  0; dy =  1; break;
		  case 3: dx =  1; dy =  0; break; 
		  default: dx = 0; dy =  0; break;
		  } 
		}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public boolean isMoving() {
		return dx != 0 || dy != 0;
	}
	
	public void stopMoving() {
		dx = 0;
		dy = 0;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public String getNom() {
		return nom;
	}
	public float getPourcentVie() {
    	return life/LIFE_MAX;
    }

}
