import org.newdawn.slick.*;

public class Player {

	// concerne la signature du personnage
	private String nom;
	private static int num = 1;
	private int numeroJoueur;
	// concerne le placement du personnage sur la carte
	private Map map;
	private Animation[] animations;
	private float xPerso, yPerso;
	private float X_BEGIN, Y_BEGIN;
	private float dx, dy;
	private int direction;
	private boolean moving;
	private boolean jumping;
	private int JUMP_HEIGHT = 150;
	private Boolean goingUp = false;
	private Boolean goingDown = false;
	private int highestPoint = 10000;
	float gravity = -.2f;

	private final float LARGEUR_PERSO;
	private final float HAUTEUR_PERSO;
	// concerne l'affichage de la vie sur la carte
	private Hud hud;
	private final float LIFE_MAX;
	private float life;
	float base = 0;
	

	public Player(String nom, Map map) throws SlickException {
		// initialisation des variables simples
		this.nom = nom;
		this.map = map;
		LIFE_MAX = 100;
		life = LIFE_MAX;
		moving = false;
		dx = 0;
		dy = 0;

		// différence entre les deux personnages
		direction = 2 * (num - 1);
		LARGEUR_PERSO = 64;
		HAUTEUR_PERSO = 128;
		X_BEGIN=200+(num-1)*(this.map.getWidth()-400-LARGEUR_PERSO); //200 pour l'écart au bord
		Y_BEGIN=map.f(X_BEGIN+LARGEUR_PERSO/2);//hauteur du sol
		xPerso = X_BEGIN;
		yPerso = Y_BEGIN;

		// Création du HUD
		hud = new Hud(num, this.map.getWidth(), nom);

		// Augmentation du numéro du joueur
		numeroJoueur = num;
		num++;
	}

	public void init() throws SlickException {// Chargement des animations
		SpriteSheet face = null;
		SpriteSheet marcheGauche0 = null;
		SpriteSheet marcheGauche1 = null;
		SpriteSheet marcheDroite0 = null;
		SpriteSheet marcheDroite1 = null;
		SpriteSheet sautGauche = null;
		SpriteSheet sautDroite = null;
		try {
			face = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_front.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
			marcheGauche0 = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_walk1_0.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
			marcheGauche1 = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_walk2_0.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
			marcheDroite0 = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_walk1_1.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
			marcheDroite1 = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_walk2_1.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
			sautGauche = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_jump_0.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
			sautDroite = new SpriteSheet(
					"src/main/ressources/PNG/Players/64x128/" + numeroJoueur + "/" + numeroJoueur + "_jump_1.png",
					(int) LARGEUR_PERSO, (int) HAUTEUR_PERSO);
		} catch (Exception e) {

		}
		animations = new Animation[5];
		for (int i = 0; i < 5; i++) {
			animations[i] = new Animation();
		}
		animations[0].addFrame(face.getSprite(0, 0), 150); // face
		animations[1].addFrame(marcheGauche0.getSprite(0, 0), 150); // marche
																	// gauche
		animations[1].addFrame(marcheGauche1.getSprite(0, 0), 150);
		animations[2].addFrame(marcheDroite0.getSprite(0, 0), 150); // marche
																	// droite
		animations[2].addFrame(marcheDroite1.getSprite(0, 0), 150);
		animations[3].addFrame(sautGauche.getSprite(0, 0), 150); // saut gauche
		animations[4].addFrame(sautDroite.getSprite(0, 0), 150); // saut droite
	}

	public void render(Graphics g) throws SlickException {
		// ombre personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(xPerso + 10, yPerso - 4, 45, 8);
		// dessin du personnage animé
		g.drawAnimation(animations[(isMoving() ? 1 + (int) direction / 2
				: 0)/* +(isJumping() ? 2:0) */], xPerso, yPerso - HAUTEUR_PERSO); // 256
																	// du
										// frame
		// dessin de la barre de vie
		hud.paintComponent(g, this.getPourcentVie());
		base = map.f(this.xPerso);
	}

	public void update(int delta) throws SlickException {
		// mouvement du personnage

		if (this.isMoving()) {

			updateDirection();
			float futurX = this.xPerso + .3f * delta * dx;
			float futurY = this.yPerso + .3f * delta * dy;
			
			if (futurX < 0) {
				futurX = 0;
			}
			if (futurY < HAUTEUR_PERSO / 2) {
				futurY = HAUTEUR_PERSO / 2;
			}
			if (futurX > map.getWidth() - LARGEUR_PERSO) {
				futurX = map.getWidth() - LARGEUR_PERSO;
			}
			if (!map.collision(futurX + LARGEUR_PERSO / 2, futurY - map.getHeightTile())) {
				this.xPerso = futurX;
				this.yPerso = futurY;

//				if (jumping || goingUp || goingDown) {
//					if (this.yPerso < highestPoint || goingUp) {
//						goingUp = true;
//						highestPoint = (int) this.yPerso;
//						this.dy = gravity * delta;
//					}
//
//					if (highestPoint < base - JUMP_HEIGHT|| goingDown) {
//						goingUp = false;
//						goingDown = true;
//						this.dy = -gravity * delta;
//					}
//					if (this.yPerso > base) {
//						goingDown = false;
//						goingUp = false;
//						this.jumping = false;
//						highestPoint = 10000;
//						this.yPerso = base;
//						System.out.println("base:" + base);
//						this.dy = 0;
//					}
//				}
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
		case 0:
			dx = 0;
			dy = -1;
			break;
		case 1:
			dx = -1;
			dy = 0;
			break;
		case 2:
			dx = 0;
			dy = 1;
			break;
		case 3:
			dx = 1;
			dy = 0;
			break;
		default:
			dx = 0;
			dy = 0;
			break;
		}
	}

	public int getDirection() {
		return direction;
	}

	public boolean getMoving() {
		return moving;
	}

	public boolean isMoving() {
		return dx != 0 || dy != 0 || jumping == true;
	}

	public void setJumping(boolean j) {
		this.jumping = j;
	}

	public boolean isJumping() {
		return jumping;
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
		life = v;
	}

	public float getVie() {
		return life;
	}

	public float getPourcentVie() {
		return life / LIFE_MAX;
	}

	public void destroy(int x, int y) {
		map.destroy(x, y, this);
	}

	public float getWidth() {
		return LARGEUR_PERSO;
	}
}
