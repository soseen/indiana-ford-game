package gm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import gm.Game.STATE;
 /**
  * Klasa ktora definiuje obiekt gracza:okresla jego atrybuty i mozliwosci
  * @author Sosnowski
  * 
  */
public class Player extends GameObject {

	private float width = 32;
	private float height = 64;
	private float gravity = 0.45f;
	private final float MAX_SPEED = 8;
	private int jumpAnimation = 0;
	public long timeElapsed;
	private Handler handler;
	private Camera cam;
	Texture tex = Game.getInstance();
	private Animation Jump;
	private Animation JumpL;
	private Animation JumpClose;
	private Animation JumpCloseL;
	private Animation Run;
	private Animation RunLeft;
	private Animation JumpBlink;
	private Animation JumpLBlink;
	private Animation JumpCloseBlink;
	private Animation JumpCloseLBlink;
	private Animation RunBlink;
	private Animation RunLeftBlink;
	private Animation IdleBlink;
	private Animation IdleLBlink;
	private Animation ShootBlink;
	private Animation ShootLBlink;

	/**
	 * Konstruktor obiektu gracza
	 * @param x Wspó³rzêdna x Gracza
	 * @param y Wspó³rzêdna y Gracza
	 * @param handler Zmienna typu Handler przechowujaca obiekt gracza
	 * @param cam Zmienna typu Camera umozliwiajaca sledzenie pozycji gracza
	 * @param id Zmienna typu ObjectID okreslajaca identyfikator obiektu
	 */
	public Player(float x, float y,Handler handler,Camera cam,ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		this.setHealth(3);
		
		Jump = new Animation(1,tex.Jump);
		JumpL = new Animation(1,tex.JumpL);
		JumpClose = new Animation(1,tex.Jump1);
		JumpCloseL = new Animation(1,tex.Jump1L);
		Run = new Animation(3,tex.Run,tex.Run1,tex.Run2);
		RunLeft = new Animation(3,tex.RunL,tex.Run1L,tex.Run2L);
		JumpBlink = new Animation(5,tex.Blink, tex.Jump);
		JumpLBlink = new Animation(5,tex.Blink,tex.JumpL);
		JumpCloseBlink = new Animation(5,tex.Blink,tex.Jump1);
		JumpCloseLBlink = new Animation(5,tex.Blink,tex.Jump1L);
		RunBlink = new Animation(5,tex.Blink,tex.Run,tex.Blink,tex.Run1,tex.Blink,tex.Run2,tex.Blink);
		RunLeftBlink = new Animation(5,tex.Blink,tex.RunL,tex.Blink,tex.Run1L,tex.Blink,tex.Run2L,tex.Blink);
		IdleBlink = new Animation(5, tex.Blink, tex.Idle);
		IdleLBlink = new Animation(5, tex.Blink, tex.IdleL);
		ShootBlink = new Animation(5, tex.Blink, tex.Shoot);
		ShootLBlink = new Animation(5, tex.Blink, tex.ShootL);
	}
	
	/**
	 * Aktualizacja danych o obiekcie gracza
	 */
	public void tick(LinkedList<GameObject> object) {
		x+= velX;
		y+= velY;
		
		if(velX > 0){
			facing = 1;
		}
		else if(velX <0){
			facing = -1;
		}
		
		if(fall || jump){
		velY+= gravity;	
		}
		
		if(velY > MAX_SPEED){
			velY = MAX_SPEED;
			jumpAnimation = 2;
		}
		else
			jumpAnimation = 1;
		
		if(this.isHit() == true){
			Invulnerability();
		}
		
		
		if(y>1500){
			health = health - 1;
			x = 100;
			y = 250;
		}
		
		if(this.getHealth() == 0){
			Game.State = STATE.OVER;
			handler.resetLevel();
		}
		
	Collision(object);
	Jump.runAnimation();
	JumpL.runAnimation();
	JumpClose.runAnimation();
	JumpCloseL.runAnimation();
	Run.runAnimation();
	RunLeft.runAnimation();
	JumpBlink.runAnimation();
	JumpLBlink.runAnimation();
	JumpCloseBlink.runAnimation();
	JumpCloseLBlink.runAnimation();
	RunBlink.runAnimation();
	RunLeftBlink.runAnimation();
	IdleBlink.runAnimation();
	IdleLBlink.runAnimation();
	ShootBlink.runAnimation();
	ShootLBlink.runAnimation();
	}
	/**
	 * Definiuje reakcje obiektu gracza na kolizjê z innymi obiektami gry
	 * @param object Obiekt gry z listy obiektów
	 */
	private void Collision(LinkedList<GameObject> object){
		for(int i=0; i< handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block){
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 26;
					velY=0;
					fall = true;
				}
				
				if(getBoundsBot().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height -1;
					velY=0;
					fall = false;
					jump = false;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 28;
				}
				
			}
			else if(tempObject.getID() == ObjectID.Enemy || tempObject.getID() == ObjectID.Enemy2){
				if(getBounds().intersects(tempObject.getBounds()) && tempObject.isEnemyDead() == false && this.isHit() == false){
					this.setHit(true);
					health--;
					timeElapsed = System.nanoTime()/1000000000;	
				}
			}
		
			
			else if(tempObject.getID() == ObjectID.Checkpoint){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.switchLevel();
				}
			}
			
			if(tempObject.getID() == ObjectID.Meteor){
				if(getBounds().intersects(tempObject.getBounds())  && this.isHit() == false){
					this.setHit(true);
					health--;
					timeElapsed = System.nanoTime()/1000000000;	
				}
			}
			
			if(tempObject.getID() == ObjectID.Boss){
				if(getBounds().intersects(tempObject.getBounds())  && this.isHit() == false &&tempObject.isEnemyDead() == false){
					this.setHit(true);
					health--;
					timeElapsed = System.nanoTime()/1000000000;	
				}
			}
			
			else{
				fall = true;
				}
			}
	}
	
	/**
	 * Umozliwia obiektowi gracza, po kontakcie z przeciwnikiem, nie otrzymywanie od niego obrazen przez dany okres czasu
	 */
	private void Invulnerability(){
		if(System.nanoTime()/1000000000 - timeElapsed >= 3){
			this.setHit(false);
		}
	}
	
	/**
	 * Stworzenie tekstur i Interface'u dla obiektu gracza. Metoda definiuje kiedy i jak wyswietlac dane animacje czy obrazy.
	 */
	public void render(Graphics g) {
		
		
		/*Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLUE);
		g2d.draw(getBounds());
		g2d.draw(getBoundsBot());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());*/
		
		if(velX >0 && velY==0 && this.isShooting() == false && this.isHit() == false)
			Run.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(velX >0 && velY==0 && this.isShooting() == false && this.isHit() == true)
			RunBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velX <0 && velY==0 && this.isShooting() == false && this.isHit() == false)
			RunLeft.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(velX <0 && velY==0 && this.isShooting() == false && this.isHit() == true)
			RunLeftBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velY != 0 && facing == 1 && jumpAnimation == 1 && this.isShooting() == false && this.isHit() == false)
			Jump.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(velY != 0 && facing == 1 && jumpAnimation == 1 && this.isShooting() == false && this.isHit() == true)
			JumpBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velY != 0 && facing == -1 && jumpAnimation == 1 && this.isShooting() == false && this.isHit() == false)
			JumpL.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(velY != 0 && facing == -1 && jumpAnimation == 1 && this.isShooting() == false && this.isHit() == true)
			JumpLBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velY != 0 && velX >= 0 && jumpAnimation == 2 && this.isShooting() == false && this.isHit() == false)
			JumpClose.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(velY != 0 && velX >= 0 && jumpAnimation == 2 && this.isShooting() == false && this.isHit() == true)
			JumpCloseBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velY != 0 && velX <0 && jumpAnimation == 2 && this.isShooting() == false && this.isHit() == false)
			JumpCloseL.scaledAnimation(g, (int)x, (int)y, 32, 64);
		else if(velY != 0 && velX <0 && jumpAnimation == 2 && this.isShooting() == false && this.isHit() == true)
			JumpCloseLBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velX == 0 && facing == 1 && this.isShooting() == false && this.isHit() == false)
			g.drawImage(tex.Idle, (int)x, (int)y, 32, 64, null);
		else if(velX == 0 && facing == 1 && this.isShooting() == false && this.isHit() == true)
			IdleBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(velX == 0 && facing == -1 && this.isShooting() == false && this.isHit() == false)
			g.drawImage(tex.IdleL, (int)x, (int)y, 32, 64, null);
		else if(velX == 0 && facing == -1 && this.isShooting() == false && this.isHit() == true)
			IdleLBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(this.isShooting() == true && facing == 1 && this.isHit() == false)
			g.drawImage(tex.Shoot, (int)x, (int)y, 32, 64, null);
		else if(this.isShooting() == true && facing == 1 && this.isHit() == true)
			ShootBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		else if(this.isShooting() == true && facing == -1 && this.isHit() == false)
			g.drawImage(tex.ShootL, (int)x, (int)y, 32, 64, null);
		else if(this.isShooting() == true && facing == -1 && this.isHit() == true)
			ShootLBlink.scaledAnimation(g, (int)x, (int)y, 32, 64);
		
		
		Font font = new Font("Century Gothic", Font.BOLD, 26);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawRect((int)x - 480,  (int)y - 210, 150, 50);
		g.drawRect((int)x - 480,  (int)y - 270, 150, 50);
		g.setColor(Color.GREEN);
		g.fillRect((int)x - 481,  (int)y - 269, 151, 49);
		g.setColor(Color.GRAY);
		g.fillRect((int)x - 481,  (int)y - 209, 151, 49);
		g.setColor(Color.WHITE);
		g.drawString("Level: " +Game.level ,(int)x - 458, (int)y - 176);
		
		if(this.getHealth() >= 1){
		g.drawImage(tex.Heart, (int)x -460, (int)y - 260, 32, 32, null);
		}
		if(this.getHealth() >= 2){
		g.drawImage(tex.Heart, (int)x -420, (int)y - 260, 32, 32, null);
		}
		if(this.getHealth() >= 3){
		g.drawImage(tex.Heart, (int)x -380, (int)y - 260, 32, 32, null);
		
		
	
		}
	
	}
	/**
	 * Definiuje obrys obiektu gracza umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu gracza na kontakt z innymi obiektami od gory
	 * @return Zwraca gorna sciane obrysu gracza
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu gracza na kontakt z innymi obiektami z prawej strony
	 * @return Zwraca prawa sciane obrysu gracza
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+(width-6)), (int)y, (int)6, (int)height-6);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu gracza na kontakt z innymi obiektami z prawej storny
	 * @return Zwraca lewa sciane obrysu gracza
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x-1, (int)y, (int)6, (int)height-6);
	}
	/**
	 * Wykorzystywana do okreslenia reakcji obiektu gracza na kontakt z innymi obiektami od dolu
	 * @return Zwraca dolna sciane obrysu gracza
	 */
	public Rectangle getBoundsBot(){
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)),(int) ((int)y+height/2 + 2), (int)width/2, (int)height/2);
	}


}
