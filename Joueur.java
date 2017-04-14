import org.newdawn.slick.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Joueur {

	//concerne la signature du personnage
	private String nom;
	private static int num=1;
	private int numeroJoueur;
	
	//concerne le placement du personnage sur la carte
	private Map map;
	private Animation[] animations;
	private final float LARGEUR_PERSO,HAUTEUR_PERSO;
	private final float X_BEGIN,Y_BEGIN;
	private float xPerso,yPerso;
	private float dx,dy;
	
	//concerne les problématiques de déplacement et de gestion des tours
	private int direction;
	private boolean jumping;
	private int JUMP_HEIGHT;
	private boolean goingUp;
	private boolean goingDown;
	private int highestPoint;
	private float gravity;
	private float base;
	public final int DISTANCE_MAX;
	private int distanceParcourue;
	public boolean grenadeLancee;
	
	//concerne la vie du personnage
	private final float LIFE_MAX;
	private float life;
	
	//concerne les hud
	private Hud hud;
	//private Hud_Distance hud_distance;
	
	//concerne la grenade
	private Grenade gr;

	/**
	 * Constructeur de la classe Joueur.
	 * @param nom Le nom du personnage.
	 * @param map La carte sur laquelle sera dessiné le personnage.
	 * @param dimX La dimension en abscisse de la fenêtre.
	 * @throws SlickException
	 */
	public Joueur(String nom,Map map,int dimX) throws SlickException {
		//concerne la signature du personnage
		this.nom=nom;
		numeroJoueur=num;
		
		//concerne le placement du personnage sur la carte
		this.map=map;
		LARGEUR_PERSO=64;
		HAUTEUR_PERSO=128;
		X_BEGIN=100+(num-1)*(this.map.getWidth()-200-LARGEUR_PERSO); //cette formule permet d'avoir le même écart au bord.
		Y_BEGIN=map.f(X_BEGIN+LARGEUR_PERSO/2);
		xPerso=X_BEGIN;
		yPerso=Y_BEGIN;
		dx=0;
		dy=0;
		
		//concerne les problématiques de déplacement et de gestion des tours
		direction=2*(num-1);
		jumping=false;
		JUMP_HEIGHT = 160;
		goingUp = false;
		goingDown = false;
		highestPoint = 10000;
		gravity = -.03f;
		base=Y_BEGIN;
		DISTANCE_MAX=200;
		distanceParcourue=0;
		grenadeLancee=false;
		
		//concerne la vie du personnage
		LIFE_MAX=100;
		life=LIFE_MAX;
		
		//concerne les hud
        hud=new Hud(num,dimX,nom);
        
        //concerne la grenade
        gr=new Grenade();
        
        //Augmentation du numéro du joueur
		num++;
	}
	
	/**
	 * Initialise les animations du personnage.
	 */
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
	
	/**
	 * Dessine le personnage, gère les animations et dessine les hud liés au personnage.
	 * @param g L'instance Graphics liée à la fenêtre.
	 */
	public void render(Graphics g) {
		// ombre personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(xPerso + 10, yPerso - 4, 45, 8);
		// dessin du personnage animé
		g.drawAnimation(animations[(isMoving() ? 1+(int)direction/3: 0)+(isJumping() ? (toTheLeft() ? -1-(int)direction/3+3:0)+(toTheRight() ? -1-(int)direction/3+4:0):0)], xPerso, yPerso-HAUTEUR_PERSO);
		//dessin des barres
		hud.paintComponent(g,this.getPourcentVie(),1 - (float)distanceParcourue/DISTANCE_MAX);
		gr.paintComponent();
	}
	
	/**
	 * Met à jour l'animation et la position du personnage.
	 * @param delta	Le pas de temps.
	 */
	public void update(int delta) {
		// mouvement du personnage
		checkDistance();
		if (this.isMoving() || this.isJumping()) {
			updateDirection();
			
			float futurX=this.xPerso + .3f * delta * dx;
			float futurY=this.yPerso + .3f * delta * dy;
			
			if(!jumping) {
				base=yPerso;
				if(!map.collision(futurX+LARGEUR_PERSO/2,futurY)) { //pas la même condition pour avoir une certaine "marge"
					dy=-16*gravity;
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
			if(futurY>map.getHeight()-map.getHeightTile()) {
				futurY=map.getHeight()+1-map.getHeightTile();
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
			
			if(!map.collision(futurX+LARGEUR_PERSO/2,futurY-map.getHeightTile())) {
				this.xPerso = futurX;
				this.yPerso = futurY;
			} else {
				setDy(0);
			}
		}
	}
	
	/**
	 * Renvoie la fraction de distance encore parcourable ce tour.
	 * @return Le rapport de la distance encore parcourable sur la distance totale possible en un tour.
	 */
	public float getPourcentDistance(){
		System.out.println("raport" + (float)distanceParcourue/DISTANCE_MAX);
		return 1-(float)(distanceParcourue/DISTANCE_MAX);
	}
	
	/**
	 * Calcule la distance parcourue.
	 */
	public void checkDistance(){
		if ((this.isMoving() || this.isJumping() )&& distanceParcourue<= DISTANCE_MAX){
			this.distanceParcourue += 1;
		}
			
		if (this.isJumping() && distanceParcourue>DISTANCE_MAX){
			this.distanceParcourue += 1;
			setDx(0);
		}
		
		if (!this.isJumping() && distanceParcourue > DISTANCE_MAX) {
			setDx(0);
		}
		
	}
	
	/**
	 * Met à jour la direction du personnage en fonction des valeurs de dx et dy.
	 */
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
	
	/**
	 * Modifie la valeur de dx et de dy avec la valeur de direction.
	 * @param direction La direction du personnage.
	 */
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
	
	/**
	 * Retourne la direction du personnage.
	 * @return La direction du personnage.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Retourne si le personnage est en mouvement ou pas, en fonction de la valeur de dx et de dy.
	 * @return Le personnage est en mouvement.
	 */
	public boolean isMoving() {
		return dx != 0 || dy != 0;
	}
	
	/**
	 * Modifie la valeur de jumping, représentant si le personnage saute ou non.
	 * @param j La nouvelle valeur de jumping.
	 */
	public void setJumping(boolean j) {
		this.jumping = j;
	}
	
	/**
	 * Retourne si le personnage saute ou non.
	 * @return Le personnage saute.
	 */
	public boolean isJumping() {
		return jumping;
	}
	
	/**
	 * Retourne si le personnage va à gauche ou non.
	 * @return Le personnage va à gauche.
	 */
	public boolean toTheLeft(){
		return (dx<0);
	}
	
	/**
	 * Retourne si le personnage va à droite ou non.
	 * @return Le personnage va à droite.
	 */
	public boolean toTheRight(){
		return (dx>0);
	}
	
	
	/**
	 * Arrête le personnage.
	 */
	public void stopMoving() {
		dx = 0;
		dy = 0;
	}
	
	/**
	 * Renvoie la largeur de l'image du personnage.
	 * @return La largeur du personnage.
	 */
	public float getWidth() {
		return LARGEUR_PERSO;
	}
	
	/**
	 * Renvoie la hauteur de l'image du personnage.
	 * @return La hauteur du personnage.
	 */
	public float getHeight() {
		return HAUTEUR_PERSO;
	}
	
	/**
	 * Renvoie la coordonnée en abscisse du personnage.
	 * @return L'abscisse du personnage.
	 */
	public float getX() {
		return xPerso;
	}

	/**
	 * Renvoie la coordonnée en ordonnée du personnage.
	 * @return L'ordonnée du personnage.
	 */
	public float getY() {
		return yPerso;
	}
	
	/**
	 * Modifie la valeur du déplacement en abscisse.
	 * @param dx La nouvelle valeur du déplacement en abscisse.
	 */
	public void setDx(float dx) {
		this.dx = dx;
	}
	
	/**
	 * Modifie la valeur du déplacement en ordonnée.
	 * @param dx La nouvelle valeur du déplacement en ordonnée.
	 */
	public void setDy(float dy) {
		this.dy = dy;
	}
	
	/**
	 * Modifie la valeur de la distance parcourue.
	 * @param dist La nouvelle distance parcourue.
	 */
	public void setDistanceParcourue(int dist) {
		distanceParcourue=dist;
	}
	
	/**
	 * Renvoie le nom du personnage.
	 * @return Le nom du personnage.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Modifie le nom du personnage.
	 * @param a Le nouveau nom du personnage.
	 */
	public void setNom(String a) {
		nom=a;
		hud.setNom(nom);
	}
	
	/**
	 * Modifie la vie actuelle du personnage.
	 * @param l La nouvelle valeur de la vie du personnage.
	 */
	public void setVie(float l) {
		life=l;
	}
	
	/**
	 * Renvoie la vie actuelle du personnage.
	 * @return La vie du personnage.
	 */
	public float getVie() {
		return life;
	}
	
	/**
	 * Renvoie la fraction de vie restante.
	 * @return Le rapport de la vie restante et de la vie maximum.
	 */
	public float getPourcentVie() {
    	return life/LIFE_MAX;
    }
	
	/**
	 * Calcule la trajectoire de la grenade, la dessine et renvoie les coordonnées du point d'explosion.
	 * @param a L'angle de lancer en degrés.
	 * @param f La vitesse initiale (minimum de 15, maximum conseillé 175).
	 * @param direct La direction (0 à gauche,1 à droite).
	 * @return La position du point d'explosion.
	 */
	public float[] lancerGrenade(float a, float f,int direct) { //attention, diminution de y vers le haut, donc force<0 et g>0 et - devant la tangente
		float pas=(float)(-a/10+11); //dépend de l'angle
		float angle =(float)(a*2*Math.PI/360) ; //a en degré, angle en radians
		float force = -f ;
		int dir=direct;
		ArrayList<Float> traj=new ArrayList<Float>();
		
		float x0=(float)(xPerso+LARGEUR_PERSO/2);//valeur de départ de x
		float y0=(float)(yPerso-LARGEUR_PERSO/4);//valeur de départ de y, au milieu du corps du perso
		gr.setPosition(x0,y0);
		gr.setIsThere(true);
		
		traj.add(yPerso); //première valeur de y
		float x;
		if(dir==1) {
			x=(float)(x0+pas);
		} else { //tir à gauche
			x=(float)(x0-pas);
			angle=-angle+(float)Math.PI;
		}
		float y =(float)(((10*Math.pow(x-x0,2))/(2*Math.pow(force,2)))*(1+Math.pow(Math.tan(angle),2)) - (x-x0)*Math.tan(angle)+y0); //1ere itération
		while(x>pas && x<map.getWidth()-pas && y>pas && y<map.getHeight()-pas && !map.collision(x, y)) { //ne pas faire les tests si sortie d'écran
			traj.add(y);//ajout du précédent
			if(dir==1) {
				x=(float)(x+pas);
			} else { //tir à gauche
				x=(float)(x-pas);
			}
			y =(float)(((10*Math.pow(x-x0,2))/(2*Math.pow(force,2)))*(1+Math.pow(Math.tan(angle),2)) - (x-x0)*Math.tan(angle)+y0);
			gr.setPosition(x,y);
		}
		
		float[] finale={(int)x/map.getWidthTile(),(int)(y+1)/map.getWidthTile()}; //+1 car on s'arrête juste avant l'entier
		final int xLand = (int)x/map.getWidthTile();
		final int yLand = (int)(y+1)/map.getWidthTile();
		//timer pour dessiner le trou au bon moment
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destroy(xLand, yLand);
			}
		};
		Timer myTimer = new Timer(1000, listener);
		myTimer.setInitialDelay(0);
		myTimer.setRepeats(false);
		myTimer.start();
		grenadeLancee = true;
		return finale;	
	}
	
	/**
	 * Modifie si la grenade a été lancée ce tour ou non.
	 * @param b La grenade a été lancée.
	 */
	public void setGrenade(boolean b) {
		grenadeLancee=b;
	}
	
	/**
	 * Appelle la méthode destroy de Map.
	 * @param x L'abscisse du point d'explosion de la grenade.
	 * @param y L'ordonnée du point d'explosion de la grenade.
	 */
	public void destroy(int x, int y) {
		map.destroy(x,y);
	}
	
	/**
	 * Appelle la méthode changeLife de Map pour ce Joueur.
	 * @param x L'abscisse du point d'explosion de la grenade.
	 * @param y L'ordonnée du point d'explosion de la grenade.
	 */
	public void changeLife(int x, int y) {
		map.changeLife(x,y,this);
	}
	
	/**
	 * Translate les hud liés au Joueur à une certaine valeur sur l'axe des abscisses.
	 * @param x La valeur à laquelle les huds sont translatés.
	 */
	public void translateHud(int x) {
		hud.translate(x);
		//hud_distance.translate(x);
	}
}
