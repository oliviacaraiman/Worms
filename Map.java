import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class Map {

	private TiledMap map;
	private final int HEIGHT;
	private final int WIDTH;
	private final int HEIGHT_TILE;
	private final int WIDTH_TILE;
	private final int TILES_IN_WIDTH;
	private final int TILES_IN_HEIGHT;
	private int r; //rayon d'explosion
	
	
	/**
	 * Constructeur de la classe Map
	 * @throws SlickException
	 */
	public Map() {
		try {
			map=new TiledMap("src/main/ressources/map/map3.tmx");
		} catch (Exception e) {
			
		}
		WIDTH_TILE=map.getTileWidth();
		HEIGHT_TILE=map.getTileHeight();
		HEIGHT=map.getHeight()*HEIGHT_TILE;
		WIDTH=map.getWidth()*WIDTH_TILE;
		TILES_IN_WIDTH=map.getWidth();
		TILES_IN_HEIGHT=map.getHeight();
		solThere();
		r=4;
	}
	
	/**
	 * Dessine la carte.
	 * @param a Coordonnée x du dessin de la carte.
	 * @param b Coordonnée y du dessin de la carte.
	 */
	public void render(int a, int b) {
    	map.render(a,b);
	}
	
	/**
	 * Retourne la hauteur de la carte.
	 * @return Un entier représentant la hauteur de la carte.
	 */
	public int getHeight() {
		return HEIGHT;
	}
	
	/**
	 * Retourne la largeur de la carte.
	 * @return Un entier représentant la largeur de la carte.
	 */
	public int getWidth() {
		return WIDTH;
	}
	
	/**
	 * Retourne la hauteur d'un tile.
	 * @return Un entier représentant la hauteur d'un tile.
	 */
	public int getHeightTile() {
		return HEIGHT_TILE;
	}
	
	/**
	 * Retourne la largeur d'un tile.
	 * @return Un entier représentant la largeur d'un tile.
	 */
	public int getWidthTile() {
		return WIDTH_TILE;
	}
	
	
	/**
	 * Retourne un tableau de bouléens.
	 * Pour les indices i,j, si boolean[i][j]=true, le tile de coordonnées (i,j) est du sol.
	 * Ce sont les coordonnées en TILE, et non en PIXEL.
	 * Sinon, la case est vide.
	 * @return Un tableau de bouléens représentant la présence du sol pour chaque tile.
	 */
	public boolean[][] solThere() {
		boolean[][] b=new boolean[TILES_IN_WIDTH][TILES_IN_HEIGHT];
		for(int i=0;i<TILES_IN_WIDTH;i++) {
			for(int j=0;j<TILES_IN_HEIGHT;j++) {
				Image tile = map.getTileImage(i, j, map.getLayerIndex("Sol"));
				b[i][j]=false;
				if(tile!=null) {
					b[i][j]=true;
				}
			}
		}
		return b;
	}
	
	
	/**
	 * Retourne si le personnage est en collision avec le sol.
	 * @param x La position en abscisse (en PIXEL) du personnage.
	 * @param y La position en ordonnée (en PIXEL) du personnage.
	 * @return Un bouléen représentant la collision du personnage avec le sol.
	 */
	public boolean collision(float x,float y) {//méthode permettant de savoir si le joueur entre en collision avec le sol
		int u=(int)(x/WIDTH_TILE); //coordonnées du tile
		int v=(int)(y/HEIGHT_TILE);
		int u2=(int)(x%WIDTH_TILE); //nombres de pixels dans le tile
		int v2=(int)(y%HEIGHT_TILE);
		boolean collide=solThere()[u][v];
		Image tile = this.map.getTileImage(u,v,map.getLayerIndex("Sol"));
		if(collide && tile!=null) { //contact au pixel près
	        Color color = tile.getColor(u2,v2);
	        collide = color.getAlpha() > 0;
		} else if(collide && tile==null) { //entre dans le if si le sol a été détruit
			collide=false;
		}
		return collide;
	}
	
	/**
	 * Calcule le plus grand y tel que le tile (x,y) correspond à la surface du sol.
	 * Attention, le plus grand y correspond au y "le plus bas" sur l'écran.
	 * @param x L'abscisse à laquelle on cherche la hauteur du sol. 
	 * @return La hauteur du sol pour l'abscisse donnée.
	 */
	public float f(float x) {
		int u=(int)x/WIDTH_TILE;
		int v=TILES_IN_WIDTH;
		for (int i=0;i<TILES_IN_HEIGHT;i++) { //ce for donne une valeur approché à l'excès de y, on affine ensuite
			if(!solThere()[u][i]) {
				v=i+1;
			}
		}
		float y=v*HEIGHT_TILE;
		while (!collision(x,y+10)) { //approche grossière pour plus de rapidité
			y=y+10;
		}
		while (!collision(x,y+1)) { //approche précise
			y++;
		}
		return y;
	}
	
	/**
	 * Met à jour la vie du personnage si il est touché par la grenade.
	 * @param x L'abscisse à laquelle la grenade explose.
	 * @param y L'ordonnée à laquelle la grenade explose.
	 * @param player Le joueur dont la vie doit être modifiée.
	 */
	public void changeLife(int x, int y, Joueur player){
		if(Math.min(Math.pow((player.getX()+player.getWidth())/WIDTH_TILE-x,2),Math.pow(player.getX()/WIDTH_TILE-x,2))+Math.min(Math.pow((player.getY()-player.getHeight()/2)/HEIGHT_TILE-y,2),Math.pow(player.getY()/HEIGHT_TILE-y,2))<=Math.pow(r,2)) {
			player.setVie(player.getVie()-20);
		}
	}
	
	
	/**
	 * Supprime sur un rayon donné les tiles du calque "Sol", autour du point d'explosion de la grenade.
	 * @param x L'abscisse du point d'explosion de la grenade.
	 * @param y L'ordonnée du point d'explosion de la grenade.
	 */
	public void destroy(int x, int y) {
		for (int i=Math.max(x-r,0);i<Math.min(x+r+1,TILES_IN_WIDTH);i++) { //max et min pour les sorties d'écran
			for (int j=Math.max(y-r,0);j<Math.min(y+r+1,TILES_IN_HEIGHT);j++) {
				if(Math.pow(i-x,2)+Math.pow(j-y,2)<=Math.pow(r,2) && i>=0 && j>=0) {
					map.setTileId(i, j, map.getLayerIndex("Sol"), 0);
				}
			}
		}
	}
}
