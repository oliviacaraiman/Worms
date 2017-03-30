import org.newdawn.slick.*;
import java.util.*;

public class Joueur {

	//concerne la signature du personnage
	private String nom;
	private static int num=1;
	private int numeroJoueur;
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
	
	private boolean jumping;
	private int JUMP_HEIGHT;
	private boolean goingUp;
	private boolean goingDown;
	private int highestPoint;
	private float gravity;
	private float base;
	
	private Grenade gr;

	
	public Joueur(String nom,Map map) throws SlickException {
		//initialisation des variables simples
		this.nom=nom;
		this.map=map;
		LIFE_MAX=100;
		life=LIFE_MAX;
		moving=false;
		dx=0;
		dy=0;
		
		//différentes données à propos du saut
		JUMP_HEIGHT = 150;
		goingUp = false;
		goingDown = false;
		highestPoint = 10000;
		gravity = -.05f;
		jumping=false;
		
		//différence entre les deux personnages
		direction=2*(num-1);
		LARGEUR_PERSO=64;
		HAUTEUR_PERSO=128;
		X_BEGIN=200+(num-1)*(this.map.getWidth()-400-LARGEUR_PERSO); //200 pour l'écart au bord
		Y_BEGIN=map.f(X_BEGIN+LARGEUR_PERSO/2);//hauteur du sol
		xPerso=X_BEGIN;
		yPerso=Y_BEGIN;
		base=Y_BEGIN;
        
        //Création du HUD et de la grenade;
        hud=new Hud(num,this.map.getWidth(),nom);
        gr=new Grenade();
        
        //Augmentation du numéro du joueur
        numeroJoueur=num;
		num++;
	}
	
	public void init() {//Chargement des animations
		SpriteSheet face=null;
		SpriteSheet marcheGauche0=null;
		SpriteSheet marcheGauche1=null;
		SpriteSheet marcheDroite0=null;
		SpriteSheet marcheDroite1=null;
		SpriteSheet sautGauche=null;
		SpriteSheet sautDroite=null;
		try {
			face = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_front.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
			marcheGauche0 = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_walk1_0.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
			marcheGauche1 = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_walk2_0.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
			marcheDroite0 = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_walk1_1.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
			marcheDroite1 = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_walk2_1.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
			sautGauche = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_jump_0.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
			sautDroite = new SpriteSheet("src/main/ressources/PNG/Players/64x128/"+numeroJoueur+"/"+numeroJoueur+"_jump_1.png",(int)LARGEUR_PERSO,(int)HAUTEUR_PERSO);
		} catch (Exception e) {
			
		}
		animations=new Animation[5];
		for (int i=0;i<5;i++) {
    		animations[i]=new Animation();
    	}
        animations[0].addFrame(face.getSprite(0, 0), 150); //face
        animations[1].addFrame(marcheGauche0.getSprite(0, 0), 150); //marche gauche
        animations[1].addFrame(marcheGauche1.getSprite(0, 0), 150);
        animations[2].addFrame(marcheDroite0.getSprite(0, 0), 150); //marche droite
        animations[2].addFrame(marcheDroite1.getSprite(0, 0), 150);
        animations[3].addFrame(sautGauche.getSprite(0, 0), 150); //saut gauche
        animations[4].addFrame(sautDroite.getSprite(0, 0), 150); //saut droite
	}
	
	public void render(Graphics g) {
		// ombre personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(xPerso + 10, yPerso - 4, 45, 8);
		// dessin du personnage animé
		g.drawAnimation(animations[(isMoving() ? 1+(int)direction/3: 0)+(isJumping() ? (toTheLeft() ? -1-(int)direction/3+3:0)+(toTheRight() ? -1-(int)direction/3+4:0):0)], xPerso, yPerso-HAUTEUR_PERSO);
		//dessin de la barre de vie
		hud.paintComponent(g,this.getPourcentVie());
		gr.paintComponent();
	}
	
	public void update(int delta) {
		// mouvement du personnage
		if (this.isMoving()) {
			updateDirection();
			
			float futurX=this.xPerso + .3f * delta * dx;
			float futurY=this.yPerso + .3f * delta * dy;
			
			if(!jumping) {
				base=yPerso;
				if(!map.collision(futurX+LARGEUR_PERSO/2,futurY)) { //pas la même condition pour avoir une certaine "marge"
					dy=-10*gravity;
					futurY=this.yPerso + .3f * delta * dy;
				}
			}
			
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
			if(futurY>map.getHeight()) {
				futurY=map.getHeight();
			}
			
			if (jumping || goingUp || goingDown) {
				if (futurY < highestPoint || goingUp) {
					goingUp = true;
					highestPoint = (int) futurY;
					this.dy = gravity * delta;
				}
				if (highestPoint < base - JUMP_HEIGHT|| goingDown) {
					goingUp = false;
					goingDown = true;
					this.dy = -gravity * delta;
				}
				if(map.collision(futurX+LARGEUR_PERSO/2,futurY-map.getHeightTile())) {
					goingDown = false;
					goingUp = false;
					this.jumping = false;
					highestPoint = 10000;
					this.dy = 0;
				}
			}
			
			if(!map.collision(futurX+LARGEUR_PERSO/2,futurY-map.getHeightTile())) { //en cours d'écriture, on peut mettre contact pour l'instant
				this.xPerso = futurX;
				this.yPerso = futurY;
			} else {
				setDy(0); //cette partie permet de corriger des bugs causés par l'ajout de la gravité
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
			case 0: dx =  0; dy = -0.5f; break;
			case 1: dx = -0.5f; dy =  0; break;
			case 2: dx =  0; dy =  0.5f; break;
			case 3: dx =  0.5f; dy =  0; break; 
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
		return dx != 0 || dy != 0 || jumping==true;
	}
	
	public void setJumping(boolean j) {
		this.jumping = j;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public boolean toTheLeft(){
		return (dx<0);
	}
	
	public boolean toTheRight(){
		return (dx>0);
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
	
	public void setVie(float v) {
		life=v;
	}
	
	public float getVie() {
		return life;
	}
	
	public float getPourcentVie() {
    	return life/LIFE_MAX;
    }
	
	public void destroy(int x, int y) {
		map.destroy(x,y,this);
	}
	
	public float getWidth() {
		return LARGEUR_PERSO;
	}
	
	public float getHeight() {
		return HAUTEUR_PERSO;
	}
	
	public float[] lancerGrenade(float a, float f) { //attention, diminution de y vers le haut, donc force<0 et g>0 et - devant la tangente
		float angle = a ;
		float force = -f ;
		float pas=8f;
		ArrayList<Float> traj=new ArrayList<Float>();
		
		float x0=(float)(xPerso+LARGEUR_PERSO/2);//valeur de départ de x
		float y0=(float)(yPerso-LARGEUR_PERSO/4);//valeur de départ de y, au milieu du corps du perso
		gr.setPosition(x0,y0);
		gr.setIsThere(true);
		
		traj.add(yPerso); //première valeur de y
		float x=(float)(x0+pas);
		float y =(float)(((10*Math.pow(x-x0,2))/(2*Math.pow(force,2)))*(1+Math.pow(Math.tan(angle),2)) - (x-x0)*Math.tan(angle)+y0); //1ere itération
		while(x>pas && x<map.getWidth()-pas && y>pas && y<map.getHeight()-pas && !map.collision(x, y)) { //ne pas faire les tests si sortie d'écran
			traj.add(y);//ajout du précédent
			x=(float)(x+pas);
			y =(float)(((10*Math.pow(x-x0,2))/(2*Math.pow(force,2)))*(1+Math.pow(Math.tan(angle),2)) - (x-x0)*Math.tan(angle)+y0);
			gr.setPosition(x,y);
			gr.paintComponent();
		}
		
		float[] tabTraj=new float[traj.size()]; //tableau provisoire, ensuite on affichera les grenades
		for (int j=0;j<tabTraj.length;j++) {
			tabTraj[j]=(float)(traj.get(j));
		}
		
		float[] finale={(int)x/map.getWidthTile(),(int)(y+1)/map.getWidthTile()}; //+1 car on s'arrête juste avant l'entier
		return finale;	
	}
}
