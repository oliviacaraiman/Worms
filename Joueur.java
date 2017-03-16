import org.newdawn.slick.*;

public class Joueur {

	//concerne la signature du personnage
	private String nom;
	private static int num=1;
	//concerne le placement du personnage sur la carte
	private Map map;
	private Animation[] animations;
	private float xPerso,yPerso;
	private float X_BEGIN,Y_BEGIN;
	private float dx,dy;
	private int direction;
	private boolean moving;
	
	private final float LARGEUR_PERSO;
	private final float HAUTEUR_PERSO;
	//concerne l'affichage de la vie sur la carte
	private Hud hud;
	private final float LIFE_MAX;
	private float life;

	
	public Joueur(String nom,Map map) throws SlickException {
		//initialisation des variables simples
		this.nom=nom;
		this.map=map;
		LIFE_MAX=100;
		life=LIFE_MAX;
		moving=false;
		dx=0;
		dy=0;
		
		//différence entre les deux personnages
		direction=2*(num-1);
		LARGEUR_PERSO=128;
		HAUTEUR_PERSO=256;
		X_BEGIN=100+(num-1)*(this.map.getWidth()-200-LARGEUR_PERSO); //200 pour l'écart au bord
		Y_BEGIN=map.f(X_BEGIN);//hauteur du sol
		xPerso=X_BEGIN;
		yPerso=Y_BEGIN;
        
        //Création du HUD
        hud=new Hud(num,this.map.getWidth(),this.map.getHeight(),nom);
        
        //Augmentation du numéro du joueur
		num++;
	}
	
	public void init() throws SlickException {//Chargement des animations
		SpriteSheet spriteSheet = new SpriteSheet("src/main/ressources/Spritesheets/spritesheet_players.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
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
	
	public void render(Graphics g) throws SlickException {
		// ombre personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(xPerso + 19, yPerso - 8, 90, 16);
		// dessin du personnage animé
		g.drawAnimation(animations[direction + (isMoving() ? 4 : 0)], xPerso, yPerso-HAUTEUR_PERSO); //256 correspond à la hauteur du frame
		//dessin de la barre de vie
		hud.render(g,this.getPourcentVie());
	}
	
	public void update(int delta) throws SlickException {
		// mouvement du personnage
		if (this.isMoving()) {
			updateDirection();
			float futurX=this.xPerso + .3f * delta * dx;
			float futurY=this.yPerso + .3f * delta * dy;
			//conditions de sortie d'écran
			if(futurX<0) {
				futurX=0;
			}
			if(futurY<HAUTEUR_PERSO/2) {
				futurY=HAUTEUR_PERSO/2;
			}
			if(futurX>map.getWidth()-LARGEUR_PERSO) {
				futurX=map.getWidth()-LARGEUR_PERSO;
			}
			if(!map.collision(futurX,futurY)) { //en cours d'écriture, on peut mettre contact pour l'instant
				this.xPerso = futurX;
				this.yPerso = futurY;
			}
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
	
	public int getDirection() {
		return direction;
	}
	
	public boolean getMoving() {
		return moving;
	}
	
	
	public boolean isMoving() {
		return dx != 0 || dy != 0;
	}
	
	public void stopMoving() {
		dx = 0;
		dy = 0;
	}
	
	public float getX() {
		return xPerso;
	}

	public float getY() {
		return yPerso;
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

	public Animation[] getAnimations() {
		return animations;
	}
	
	public String getNom() {
		return nom;
	}
	
	public float getPourcentVie() {
    	return life/LIFE_MAX;
    }
}
