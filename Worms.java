import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Worms extends BasicGameState{
	private StateBasedGame game;
	private GameContainer container;
	private Map map;
	private static int dimX;
	private static int dimY;
	public static final int ID =2;

	private Joueur j1;
	private Joueur j2;
	private Joueur jCourant;
	private ControlPlayer controller;
	private TextNextTurn tnt;
	private ControlHud control;
	private Camera cam;
	private HudPlayController hudPlay;
	public String nomGagnant, nomPerdant;
	

	public Worms(int a,int b) {
		dimX=a;
		dimY=b;
	}

    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	this.container=container;
    	this.game = game;
    	map=new Map();
    	this.hudPlay = new HudPlayController(container, game);
    	j1=new Joueur(hudPlay.getNameJoueur1().getText(),map,dimX);
    	j2=new Joueur(hudPlay.getNameJoueur2().getText(),map,dimX);
    	jCourant=j1;
    	j1.init();
    	j2.init();
    	control=new ControlHud(container,dimX);
    	controller = new ControlPlayer(j1,j2,control);
    	container.getInput().addKeyListener(controller);
    	tnt=new TextNextTurn(dimX/2,dimY/4,j1);
    	cam=new Camera(jCourant,this);
    }
    

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	cam.render(dimX,map.getWidth(), g);
    	map.render(0, 0);
    	j1.render(g);
    	j2.render(g);
    	tnt.paintComponent(g);
    	control.paintComponent(container, g);
    }
    

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	jCourant.update(delta);
    	if (jCourant.getDistanceParcourue() >= jCourant.DISTANCE_MAX && jCourant.grenadeLancee && !jCourant.isJumping()
    			&& !jCourant.isMoving()){
    		changeJoueurCourant();
    		tnt.setIsThere(true, jCourant);
    	}
    	if (exit()) {
    		game.enterState(FinalScreenGameState.ID);
    		
    	}
    	
    }
    
    
    /**
     * Determine si un personnage est mort et met a jour les noms de gagnant/perdant.
     */
    public boolean exit(){
    	if (jCourant.isDead()) {
    		 if (jCourant == j1){
    			 setNoms(j2.getNom(),jCourant.getNom());
    		 } else {
    			 setNoms(j1.getNom(),jCourant.getNom());
    		 }
 
    		return true;
    	}
    	return false;
    }
    
    /**
     * Met à jour les noms de gagnant et perdant.
     * @param gagnant Le nom du gagnant.
     * @param perdant Le nom du perdant.
     */
    public void setNoms(String gagnant, String perdant){
    	this.nomGagnant = gagnant;
    	this.nomPerdant = perdant;
    }
    
    /**
     * Change le joueur et reinitialize la distance parcourue.
     */
    
    public void changeJoueurCourant() {
    	controller.setJoueurCourant();
    	jCourant=controller.getJoueurCourant();
    	jCourant.setDistanceParcourue(0);
    	jCourant.grenadeLancee = false;
    	cam.setJoueur(jCourant);
    }
    
    public void keyReleased(int key, char c) {
    	controller.keyReleased(key, c);
    	if (Input.KEY_ENTER==key) {
    		jCourant.stopMoving();
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
    
    /**
     * Met à jour les noms des personnages.
     * @param a Le nouveau nom du premier personnage.
     * @param b Le nouveau nom du second personnage.
     */
    public void setNom(String a, String b) {
    	j1.setNom(a);
    	j2.setNom(b);
    }

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	public String getNomGagnant() {
		return nomGagnant;
	}

	public String getNomPerdant() {
		return nomPerdant;
	}
}
	