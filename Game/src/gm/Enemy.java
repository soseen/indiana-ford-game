package gm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa ktora definiuje obiekt przeciwnika i okresla jego atrybuty
 * @author Sosnowski
 * 
 */
public class Enemy extends GameObject {
	
	private float width = 32;
	private float height = 64;
	private final float MAX_SPEED = 8;
	private boolean init = true;
	private boolean right = true;
	private int timer = 0;
	
	private Handler handler;
	
	Texture tex = Game.getInstance();
	
	private Animation BatRight;
	private Animation BatLeft;
	/**
	 * Konstruktor Przeciwnika
	 * @param x Wspolrzedna x przeciwnika
	 * @param y Wspolrzedna y przeciwnika
	 * @param handler Zmienna typu Handler przechowujaca obiekt przeciwnika
	 * @param id Zmienna typu ObjectID okreslajaca identyfikator obiektu
	 */
	public Enemy(float x, float y, Handler handler,ObjectID id) {
		super(x, y,id);
		this.handler = handler;
		this.setHealth(20);
		
		BatRight = new Animation(4,tex.Bat1,tex.Bat2);
		BatLeft = new Animation(4,tex.Bat1L, tex.Bat2L);
		
	}

	/**
	 * Aktualizacja danych o obiekcie przeciwnika
	 */
	public void tick(LinkedList<GameObject> object) {
		x+= velX;
		y+= velY;
		
		if(init==true){
			right = false;
			velX=-0.7f;
			velY=0.5f;
		}
		
		if(velY > MAX_SPEED){
			velY = MAX_SPEED;
		}
		
		if(this.getHealth() == 0){
			this.setEnemyDead(true);
			velY = 4;
			velX = 0;
		}
		
		if(this.getY()>1500)
			handler.removeObject(this);
		
		BatRight.runAnimation();
		BatLeft.runAnimation();
		Collision(object);
	}
	/**
	 * Definiuje reakcje obiektu przeciwnika na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				if(EgetBoundsLeft().intersects(tempObject.getBounds()) && right == false){
					velX = 0.7f;
					init = false;
					right = true;
				}
				
				if(EgetBoundsRight().intersects(tempObject.getBounds()) && right == true){
					velX = -0.7f;
					init = false;
					right = false;
				}
				
				if(getBounds().intersects(tempObject.getBounds()) && this.isEnemyDead() == false){
					y = tempObject.getY() - height;
					velY=0;
					jump = false;
					init = false;
					fall = false;
				}
				
				}
				
			else if(tempObject.getID() == ObjectID.Bullet){
				if(EgetBoundsLeft().intersects(tempObject.getBounds()) && this.isEnemyDead() == false){
					health = health - 10;
					handler.removeObject(tempObject);
					}
				
				if(EgetBoundsRight().intersects(tempObject.getBounds()) && this.isEnemyDead() == false){
					health = health - 10;
					handler.removeObject(tempObject);
					}
			}
				
			}
		}
	
	
			
				
	/**
	 * Stworzenie tekstur i interface'u dla obiektu przeciwnika			
	 */
	public void render(Graphics g) {
		
		if(right == true && EnemyDead == false)
			BatRight.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(right == false && EnemyDead == false)
			BatLeft.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(EnemyDead == true)
			g.drawImage(tex.BatD, (int)x,(int)y,32,64,null); 
		
		Font font = new Font("Courier New", Font.BOLD, 14);
		g.setFont(font);
		g.setColor(Color.red);
		if(health>0)
		g.fillRect((int)x , (int)y-12, 14, 6);
		if(health > 10)
		g.fillRect((int)x + 14, (int)y-12, 14, 6);
		
		
	}
	/**
	 * Definiuje obrys obiektu przeciwnika umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)),(int) ((int)y+height/2-3), (int)width/2, (int)height/2+3);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu przeciwnika na kontakt z innymi obiektami od gory
	 * @return Zwraca prawa sciane obrysu przeciwnika
	 */
	public Rectangle EgetBoundsRight() {
		return new Rectangle((int) ((int)x+(width-6)), (int)y, (int)6, (int)height-6);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu przeciwnika na kontakt z innymi obiektami od gory
	 * @return Zwraca lewa sciane obrysu przeciwnika
	 */
	public Rectangle EgetBoundsLeft() {
		return new Rectangle((int) ((int)x-1), (int)y, (int)6, (int)height-6);
	}


}



