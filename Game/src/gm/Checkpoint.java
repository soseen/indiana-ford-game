package gm;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa definiujaca obiekt Checkpointa pozwalajacego zmienic poziom mapy
 * @author Sosen
 *
 */
public class Checkpoint extends GameObject{
	
	private float width = 42;
	private float height = 42;
	
	
	Texture tex = Game.getInstance();
	private Handler handler;
	/**
	 * Konstruktor obiektu Checkpointa
	 * @param x Wspolrzedna x Checkpointa
	 * @param y Wspolrzedna y Checkpointa
	 * @param handler Zmienna typu handler przechowujaca obiekty gry
	 * @param id Zmienna typu ObjectID zwracajaca identyfikator obiektu
	 */
	public Checkpoint(float x, float y, Handler handler, ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		this.setVelY(0.2f);
		
	}
	/**
	 * Aktualizacja danych o obiekcie
	 */
	public void tick(LinkedList<GameObject> object) {
		y += velY;
		
		Collision(object);
	}
	/**
	 * Definiuje reakcje obiektu Checkpointa na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				if(getBounds().intersects(tempObject.getBounds())){
					velY=0;
				}
			}
		}
	}
	/**
	 * Tworzenie tekstury Checkpointa
	 */
	public void render(Graphics g) {
		g.drawImage(tex.Port, (int)x, (int)y,(int)width,(int)height, null);
	}
	
	/**
	 * Definiuje obrys obiektu Checkpointa umozliwiajac implementacje odpowiednich reakcji na srodowisko gry		
	 */
	public Rectangle getBounds() {
		return new Rectangle ((int) x, (int) y,(int)width,(int)height);
	}

}
