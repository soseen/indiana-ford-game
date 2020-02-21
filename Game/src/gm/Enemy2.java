package gm;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * Klasa ktora definiuje obiekt przeciwnika i okresla jego atrybuty
 * @author Sosnowski
 * 
 */
public class Enemy2 extends GameObject {
	
	private float width = 40;
	private float height = 40;
	private boolean init = true;
	private boolean down = true;
	
	private Handler handler;
	private Animation WalkUp;
	private Animation WalkDown;
	
	Texture tex = Game.getInstance();
	
	/**
	 * Konstruktor Przeciwnika
	 * @param x Wspolrzedna x przeciwnika
	 * @param y Wspolrzedna y przeciwnika
	 * @param handler Zmienna typu Handler przechowujaca obiekt przeciwnika
	 * @param id Zmienna typu ObjectID okreslajaca identyfikator obiektu
	 */
	public Enemy2(float x, float y, Handler handler,ObjectID id) {
		super(x, y,id);
		this.handler = handler;
		
		WalkUp = new Animation(5,tex.SpiderU,tex.SpiderU1);
		WalkDown = new Animation(5,tex.Spider,tex.Spider1);
	}

	/**
	 * Aktualizacja danych o obiekcie przeciwnika
	 */
	public void tick(LinkedList<GameObject> object) {
		y+= velY;
			
		if(init == true){
			down = true;
			velY = 0.8f;
		}
		Collision(object);
		WalkUp.runAnimation();
		WalkDown.runAnimation();
	}
	
	/**
	 * Definiuje reakcje obiektu gracza na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				
				if(getBounds().intersects(tempObject.getBounds())){
					init = false;
					down = false;
					velY=-0.8f;
				}
				
				else if(EgetBoundsTop().intersects(tempObject.getBounds())){
					init = false;
					down = true;
					velY=0.8f;
				}
			
			}
				else{
				fall = true;
				}
				
			
				
		}
	}
			
	/**
	 * Stworzenie tekstur dla obiektu przeciwnika			
	 */			
	public void render(Graphics g) {
		
		if(down == true)
			WalkDown.scaledAnimation(g, (int)x, (int)y, 40, 40);
		
		else if(down == false)
			WalkUp.scaledAnimation(g, (int)x, (int)y, 40, 40);
	}
	/**
	 * Definiuje obrys obiektu przeciwnika umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)),(int) ((int)y+height/2-3), (int)width/2, (int)height/2-3);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu przeciwnika na kontakt z innymi obiektami od gory
	 * @return Zwraca lewa sciane obrysu przeciwnika
	 */
	public Rectangle EgetBoundsTop() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)), (int)y-4, (int)width/2, (int)height/2-3);
	}

}



