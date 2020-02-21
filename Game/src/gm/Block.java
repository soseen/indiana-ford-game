package gm;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa definiuja kwadratowe Bloki wyznaczajace obszar gry
 * @author Sosnowski
 *	
 */
public class Block extends GameObject {

	Texture tex = Game.getInstance();
	Handler handler;
	private int type;
	
	/**
	 * Konstruktor Bloczka
	 * @param x Wspolrzedna x Bloczka
	 * @param y Wspolrzedna y Bloczka
	 * @param type Parametr okreslajacy typ(graficzny) bloczka
	 * @param handler Zmienna typu handler przechowujaca pojedyczny obiekt gry
	 * @param id Zmienna typu ObjectID zwracajaca identyfikator obiektu
	 */
	public Block(int x, int y,int type, Handler handler,ObjectID id) {
		super(x, y, id);
		this.type = type;
		this.handler = handler;

	}
	
	/**
	 * Aktualizacja danych o obiekcie Bloczka
	 */
	public void tick(LinkedList<GameObject> object) {
		Collision(object);
	}
	
	/**
	 * Stworzenie grafiki dla Bloczka o danym typie
	 */
	public void render(Graphics g) {
		if(type==0)
			g.drawImage(tex.Tile, (int)x,(int)y, 25, 25,null);
		if(type==1)
			g.drawImage(tex.Grass, (int)x,(int)y,25, 25, null);
	}



	/**
	 * Definiuje reakcje obiektu Bloczka na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}
			}
		}
	}
	
	/**
	 * Definiuje obrys obiektu Bloczka umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)25, (int)25);
	}



	
	

}
