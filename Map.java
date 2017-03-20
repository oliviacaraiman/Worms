import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class Map {

	private TiledMap map;
	private int height;
	private int width;
	private int heightTile;
	private int widthTile;
	private boolean[][] solThere;
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
		solThere=new boolean[tilesInWidth][tilesInHeight];
		setSolThere(solThere);
		r=4;
	}
	
	public void render(int a, int b, int c) {
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
	public void setSolThere(boolean[][] b) {
		for(int i=0;i<tilesInWidth;i++) {
			for(int j=0;j<tilesInHeight;j++) {
				Image tile=map.getTileImage(i, j, map.getLayerIndex("Sol"));
				b[i][j]=false;
				if(tile!=null) {
					b[i][j]=true;
				}
			}
		}
	}
	
	public boolean[][] getSol() {
		return solThere;
	}
	
	//permet de calculer le ySol le plus bas pour un x donné
	public float f(float x) {
		int u=(int)x/widthTile;
		int y=800;
		for (int i=0;i<tilesInHeight;i++) {
			if(!solThere[u][i]) {
				y=(i+1)*heightTile;
			}
		}
		return y;
	}
	
	public boolean collision(float x,float y) {//méthode permettant de savoir si le joueur entre en collision avec le sol
		int u=(int)(x/widthTile); //coordonnées du tile
		int v=(int)(y/heightTile);
		int u2=(int)(x%widthTile); //nombres de pixels dans le tile
		int v2=(int)(y%heightTile);
		boolean collide=getSol()[u][v];
		Image tile = this.map.getTileImage(u,v,map.getLayerIndex("Sol"));
		if(collide && tile!=null) { //contact au pixel près
	        Color color = tile.getColor(u2,v2);
	        collide = color.getAlpha() > 0;
		} else if(collide && tile==null) { //possible si le sol a été détruit
			collide=false;
		}
		return collide;
	}
	
	//détruit sur le rayon défini le sol, et fait des dégâts aux persos dans la zone
	public void destroy(int x, int y, Joueur player) {
		for (int i=x-r;i<x+r+1;i++) {
			for (int j=y-r;j<y+r+1;j++) {
				if(Math.pow(i-x,2)+Math.pow(j-y,2)<=Math.pow(r,2) && i>=0 && j>=0) {
					map.setTileId(i, j, map.getLayerIndex("Sol"), 0);
				}
			}
		}
		if(Math.pow((player.getX()+player.getWidth()/2)/widthTile-x,2)+Math.pow(player.getY()/heightTile-y,2)<=Math.pow(r,2)) {
			player.setVie(player.getVie()-10);
		}
	}
}
