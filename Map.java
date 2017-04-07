import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class Map {

	private TiledMap map;
	private int height;
	private int width;
	private int heightTile;
	private int widthTile;
	private int tilesInWidth;
	private int tilesInHeight;
	private int r; //rayon d'explosion
	
	public Map() throws SlickException {
		try {
		map=new TiledMap("src/main/ressources/map/map2.tmx");
		} catch (Exception e) {
			
		}
		widthTile=map.getTileWidth();
		heightTile=map.getTileHeight();
		height=map.getHeight()*heightTile;
		width=map.getWidth()*widthTile;
		tilesInWidth=map.getWidth();
		tilesInHeight=map.getHeight();
		solThere();
		r=4;
	}
	
	public void render(int a, int b) {
    	map.render(a,b);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeightTile() {
		return heightTile;
	}
	
	public int getWidthTile() {
		return widthTile;
	}
	
	
	//tableau où "true" signifie qu'il y a du sol
	public boolean[][] solThere() {
		boolean[][] b=new boolean[tilesInWidth][tilesInHeight];
		for(int i=0;i<tilesInWidth;i++) {
			for(int j=0;j<tilesInHeight;j++) {
				Image tile = map.getTileImage(i, j, map.getLayerIndex("Sol"));
				b[i][j]=false;
				if(tile!=null) {
					//b[i][j]=(tile.getColor(i, j)).getAlpha()>0;
					b[i][j]=true;
				}
			}
		}
		return b;
	}
	
	public boolean collision(float x,float y) {//méthode permettant de savoir si le joueur entre en collision avec le sol
		int u=(int)(x/widthTile); //coordonnées du tile
		int v=(int)(y/heightTile);
		int u2=(int)(x%widthTile); //nombres de pixels dans le tile
		int v2=(int)(y%heightTile);
		boolean collide=solThere()[u][v];
		Image tile = this.map.getTileImage(u,v,map.getLayerIndex("Sol"));
		if(collide && tile!=null) { //contact au pixel près
	        Color color = tile.getColor(u2,v2);
	        collide = color.getAlpha() > 0;
		} else if(collide && tile==null) { //possible si le sol a été détruit
			collide=false;
		}
		return collide;
	}
	
	//permet de calculer le ySol le plus bas pour un x donné
	public float f(float x) {
		int u=(int)x/widthTile;
		int v=tilesInWidth;
		for (int i=0;i<tilesInHeight;i++) { //ce for donne une valeur approché à l'excès de y, on affine ensuite
			if(!solThere()[u][i]) {
				v=i+1;
			}
		}
		float y=v*heightTile;
		while (!collision(x,y+10)) { //approche grossière pour plus de rapidité
			y=y+10;
		}
		while (!collision(x,y+1)) { //approche précise
			y++;
		}
		return y;
	}
	
	public void changeLife(int x, int y, Joueur player){
		if(Math.min(Math.pow((player.getX()+player.getWidth())/widthTile-x,2),Math.pow(player.getX()/widthTile-x,2))+Math.min(Math.pow((player.getY()-player.getHeight()/2)/heightTile-y,2),Math.pow(player.getY()/heightTile-y,2))<=Math.pow(r,2)) {
			player.setVie(player.getVie()-10);
		}
	}
	
	//détruit sur le rayon défini le sol, et fait des dégâts aux persos dans la zone
	public void destroy(int x, int y, Joueur player) {
		for (int i=Math.max(x-r,0);i<Math.min(x+r+1,tilesInWidth);i++) { //max et min pour les sorties d'écran
			for (int j=Math.max(y-r,0);j<Math.min(y+r+1,tilesInHeight);j++) {
				if(Math.pow(i-x,2)+Math.pow(j-y,2)<=Math.pow(r,2) && i>=0 && j>=0) {
					map.setTileId(i, j, map.getLayerIndex("Sol"), 0);
				}
			}
		}
//		if(Math.min(Math.pow((player.getX()+player.getWidth())/widthTile-x,2),Math.pow(player.getX()/widthTile-x,2))+Math.min(Math.pow((player.getY()-player.getHeight()/2)/heightTile-y,2),Math.pow(player.getY()/heightTile-y,2))<=Math.pow(r,2)) {
//			player.setVie(player.getVie()-10);
//		}
	}
}
