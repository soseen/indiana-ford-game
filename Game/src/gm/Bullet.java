package gm;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa definiujaca obiekt pocisku i jego atrybuty
 * @author Sosnowski
 * 
 */
public class Bullet extends GameObject{
	
	private float width = 12;
	private float height = 12;
	
	
	Texture tex = Game.getInstance();
	private Handler handler;
	private Animation Shoot;
	/**
	 * Konstruktor pocisku
	 * @param x Wspolrzedna x pojedynczego pocisku
	 * @param y Wspolrzedna y pojedynczego pocisku
	 * @param handler Zmienna typu handler przechowujaca obiekty gry
	 * @param id Zmienna typu ObjectID zwracajaca identyfikator obiektu
	 * @param velX Zmienna okreslajaca predkosc pocisku
	 */
	public Bullet(float x, float y,Handler handler, ObjectID id, int velX) {
		super(x, y, id);
		this.velX = velX;
		this.handler = handler;
		
		Shoot = new Animation(1,tex.Bullet);
	}
	/**
	 * Aktualizuje dane o obiekcie pocisku
	 */
	public void tick(LinkedList<GameObject> object) {
		x += velX;
			
		Shoot.runAnimation();
	}

	/**
	 * Tworzy tekstury dla obiektu pocisku
	 */
	public void render(Graphics g) {	
		Shoot.scaledAnimation(g, (int)x, (int)y, (int)width, (int)height);
	}
	
	/**
	 * Definiuje obrys obiektu pocisku umozliwiajac implementacje odpowiednich reakcji na srodowisko gry		
	 */
	public Rectangle getBounds() {
		return new Rectangle ((int) x, (int) y, 12, 12);
	}

}
