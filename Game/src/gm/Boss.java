package gm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa definiuja obiekt bossa i jego atrybuty
 * @author Sosnowski
 *
 */
public class Boss extends GameObject {
	
	private float width = 300;
	private float height = 150;
	private boolean right = false;
	private int MeteorCounter = 0;
	private boolean GameEnd = false;
	
	private Handler handler;
	private Animation FlyRight;
	private Animation FlyLeft;
	
	
	Texture tex = Game.getInstance();

	/**
	 * Konstruktor obiektu bossa
	 * @param x Wspolrzedna x
	 * @param y Wspolrzedna y 
	 * @param handler Zmienna typu handler przechowujaca obiekty gry
	 * @param id Zmienna typu ObjectID zwracajaca identyfikator obiektu
	 */
	public Boss(float x, float y, Handler handler,ObjectID id) {
		super(x,y,id);
		this.handler = handler;
		this.setHealth(900);
		this.setEnemyDead(false);
		
		FlyRight = new Animation(8,tex.Boss1,tex.Boss2);
		FlyLeft = new Animation(8,tex.Boss1L,tex.Boss2L);
	}

	/**
	 * Aktualizacja danych o obiekcie bossa
	 */
	public void tick(LinkedList<GameObject> object) {
		x+=velX;
		y+=velY;
		
		
		if(this.getHealth()==0 && GameEnd == false){
			GameEnd = true;
			this.setEnemyDead(true);
			handler.SpawnLoot();
			velY = 2;
			velX = 0;
		}
		
		
		if(this.getY()>920)
			handler.removeObject(this);
		
		if(this.getHealth()<900){
			while(MeteorCounter < 11){
			handler.SpawnMeteor();
			MeteorCounter++;
			}
		}
		BossMovement(object);
		Collision(object);
		FlyRight.runAnimation();
		FlyLeft.runAnimation();
	}
	/**
	 * Definiuje reakcje obiektu bossa na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			 if(tempObject.getID() == ObjectID.Bullet){
					if(getBounds().intersects(tempObject.getBounds()) && this.isEnemyDead() == false){
						handler.removeObject(tempObject);
						health = health - 10;
					}
						}
			 if(tempObject.getID() == ObjectID.Meteor){
				 if(tempObject.getY()>840){
					handler.removeObject(tempObject);
					MeteorCounter--;
				 }
			}
		
		}
							
	}
	/**
	 * Definiuje sztuczna inteligencje obiektu bossa
	 * @param object Obiekt gry z listy obiektów
	 */
	private void BossMovement(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Player){
	
			if(x >tempObject.getX() + 16 && this.isEnemyDead() == false){
					velX=-0.65f;
					right = false;
				}
			
			else if(x < tempObject.getX() - 284 && this.isEnemyDead() == false){
				velX=0.65f;
				right = true;
			}
			
			else if(this.isEnemyDead() == false)
				velX=0;
			
			if(y<tempObject.getY() - 75 && this.isEnemyDead() == false){
				velY=0.4f;
			}
			
			else if(y>tempObject.getY() && this.isEnemyDead() == false){
			velY=-0.4f;
			}
			
			else if(this.isEnemyDead() == false)
				velY=0;				
			}
		}
	}
	
	
			
				
	
	/**
	 * Stworzenie tekstur i interface'u dla obiektu przeciwnika
	 */
	public void render(Graphics g) {
	
		if(right == true && this.isEnemyDead() == false)
			FlyRight.scaledAnimation(g, (int)x, (int)y, 300, 150);
		else if(right == false && this.isEnemyDead() == false)
			FlyLeft.scaledAnimation(g, (int)x, (int)y, 300, 150);
		else if(this.isEnemyDead() == true){
			g.drawImage(tex.BossD, (int)x, (int)y, 300, 150, null);
		}
				
		Font font = new Font("Courier New", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.red);
		if(this.getHealth() > 0)
		g.drawString("HP:" +health ,(int)x+100, (int)y-15);

	}
	/**
	 * Definiuje obrys obiektu bossa umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x+8,(int)y,(int)width, (int)height-8);
	}
}



