package gm;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * Klasa ktora definiuje obiekt meteora i okresla jego atrybuty
 * @author Sosnowski
 * 
 */
public class Meteor extends GameObject {
	
	private float width = 20;
	private float height = 21;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	/**
	 * Kostruktor obiektu meteora
	 * @param x Wspolrzedna x przeciwnika
	 * @param y Wspolrzedna y przeciwnika
	 * @param handler Zmienna typu Handler przechowujaca obiekt meteora
	 * @param id Zmienna typu ObjectID okreslajaca identyfikator obiektu
	 */
	public Meteor(float x, float y, Handler handler,ObjectID id) {
		super(x, y,id);
		this.handler = handler;
		this.velY = 1.3f;
		
	}
	/**
	 * Aktualizacja danych o obiekcie przeciwnika
	 */
	public void tick(LinkedList<GameObject> object) {
		x+= velX;
		y+= velY;
		
		Collision(object);
	}
	/**
	 * Definiuje reakcje obiektu meteora na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Player){
				if(getBounds().intersects(tempObject.getBounds()) && tempObject.isHit() == true){
					handler.removeObject(this);
				}
			}	
		}
	}					
	/**
	 * Stworzenie tekstury meteora			
	 */	
	public void render(Graphics g) {
		g.drawImage(tex.Meteor, (int)x, (int)y, 17, 18, null);
	}
	/**
	 * Definiuje obrys obiektu Meteora umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

}



