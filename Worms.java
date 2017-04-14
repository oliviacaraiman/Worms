import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Worms extends BasicGameState {

	private GameContainer container;
	private Map map;
	private static int dimX;
	private static int dimY;
	public static final int ID=2;

	private Joueur j1;
	private Joueur j2;
	private Joueur jCourant;
	public String nomGagnant,nomPerdant;
	private ControlPlayer controller;
	private TextNextTurn tnt;
	private ControlHud control;
	private Camera cam;
	
	/**
	 * Constructeur de la classe Worms, correspondant à la phase de jeu.
	 * @param a Largeur de la fenêtre de jeu.
	 * @param b Hauteur de la fenêtre de jeu.
	 */
	public Worms(int a,int b) {
		dimX=a;
		dimY=b;
	}
	
	/**
	 * Initialise les variables de la classe.
	 * @param container Le GameContainer du jeu.
	 * @param game L'instance StateBasedGame du jeu.
	 */
    public void init(GameContainer container,StateBasedGame game) throws SlickException {
    	this.container=container;
    	map=new Map();
    	j1=new Joueur("",map,dimX);
    	j2=new Joueur("",map,dimX);
    	jCourant=j1;
    	j1.init();
    	j2.init();
    	control=new ControlHud(container,dimX);
    	controller = new ControlPlayer(j1,j2,control);
    	container.getInput().addKeyListener(controller);
    	tnt=new TextNextTurn(dimX/2,dimY/4,j1);
    	cam=new Camera(jCourant,this);
    }
    
    /**
     * Dessine/met en fonction l'ensemble des variables de la classe.
     * @param container Le GameContainer du jeu.
	 * @param game L'instance StateBasedGame du jeu.
	 * @param g L'instance Graphics liée à la fenêtre.
     */
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	cam.render(dimX,map.getWidth(), g);
    	map.render(0, 0);
    	j1.render(g);
    	j2.render(g);
    	tnt.paintComponent(g);
    	control.paintComponent(container, g);
    }
    
    /**
     * Met à jour le joueur à chaque pas de temps.
     * @param container Le GameContainer du jeu.
	 * @param game L'instance StateBasedGame du jeu.
	 * @param delta	Le pas de temps.
     */
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	jCourant.update(delta);
    	if(jCourant.getDistanceParcourue() >= jCourant.DISTANCE_MAX && jCourant.grenadeLancee && !jCourant.isJumping() && ! jCourant.isMoving()) {
    		nextTurn();
    	}
    	if(j1.exit()) {
    		setNomsGagnantPerdant(j2.getNom(),jCourant.getNom());
    		game.enterState(FinalScreenGameState.ID);
    	} else if(j2.exit()) {
    		setNomsGagnantPerdant(j1.getNom(),jCourant.getNom());
    		game.enterState(FinalScreenGameState.ID);
    	}
    }
    
    
    /** Met à jour les noms de gagnant et perdant.
     * @param gagnant Le nom du gagnant.
     * @param perdant Le nom du perdant.
     */
    public void setNomsGagnantPerdant(String gagnant, String perdant){
    	this.nomGagnant = gagnant;
    	this.nomPerdant = perdant;
    }
    
    /**
     * Change le joueur actuellement controllé au clavier et suivi par la caméra.
     */
    public void changeJoueurCourant() {
    	controller.setJoueurCourant();
    	jCourant=controller.getJoueurCourant();
    	cam.setJoueur(jCourant);
    }
    
    /**
     * Réagit aux entrées clavier.
     * ENTREE fait passer au tour suivant.
     * ECHAP quitte le jeu.
     */
    public void keyPressed(int key, char c) {
    	controller.keyPressed(key, c);
    	if (Input.KEY_ENTER==key) {
    		nextTurn();
    	} else if (Input.KEY_ESCAPE==key) {
            container.exit();
        }
    }
    
    /**
     * Fait passer au tour suivant.
     */
    public void nextTurn() {
    	jCourant.stopMoving();
		jCourant.setDistanceParcourue(0);
		jCourant.setGrenade(false);
		changeJoueurCourant();
		control.emptyTextField();
		tnt.setIsThere(true,jCourant);
    }
    
    /**
     * Réagit aux entrées clavier.
     * Arrête le joueur si on relâche les touches directionnelles.
     */
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
    }
    
    /**
     * Translate tous les éléments (joueurs, texte de tour suivant et hud de contrôle) à une certaine valeur entière.
     * @param x La valeur à laquelle les éléments doivent être translatés.
     */
    public void translate(int x) {
    	j1.translateHud(x);
    	j2.translateHud(x);
    	tnt.translate(x);
    	control.translate(x);
    }
    
    /**
     * Retourne l'identifiant de la phase pour la classe.
     * @return L'identifiant de la classe Worms.
     */
    public int getID() {
    	return ID;
    }
    
    /**
     * Met à jour les noms des personnages.
     * @param a Le nouveau nom du premier personnage.
     * @param b Le nouveau nom du second personnage.
     */
    public void setNom(String a, String b) {
    	j1.setNom(a);
    	j2.setNom(b);
    }
}
