package gm;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
/**
 * Klasa, z ktorej dziedzicza obiekty w grze
 * @author Sosnowski
 * 
 */
public abstract class GameObject {
	
	protected ObjectID id;
	
	protected float x;
	protected float y;
	protected float velX;
	protected float velY;
	
	protected int health;
	protected int facing = 1;

	protected boolean fall = true;
	protected boolean jump = false;
	protected boolean hit = false;
	protected boolean shooting = false;
	protected boolean EnemyDead = false;
	/**
	 * Konstruktor obiektu gry
	 * @param x Wspolrzedna x obiektu
	 * @param y Wspolrzedna y obiektu
	 * @param id Zmienna typu ObjectID okreslajaca identyfikator obiektu
	 */
	public GameObject(float x, float y, ObjectID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	/**
	 * Aktualizacja danych obiektu
	 * @param object
	 */
	public abstract void tick(LinkedList<GameObject> object);
	/**
	 * Stworzenie graficznego modelu obiektu czy elementow z nim zwiazanych
	 * @param g Parametr umozliwiajacy wykonywanie operacji graficznych
	 */
	public abstract void render(Graphics g);
	/**
	 * Definiuje obrys obiektu gracza umozliwiajac implementacje odpowiednich reakcji na srodowisko gry
	 * @return Zwraca obrys obiektu
	 */
	public abstract Rectangle getBounds();
	/**
	 * Ustawienie wspolrzednej x obiektu
	 * @param x
	 */
	public void setX(float x){
		this.x = x;
	}
	/**
	 * Ustawienie wspolrzednej y obiektu
	 * @param y
	 */
	public void setY(float y){
		this.y = y;
	}
	/**
	 * Sprawdzenie wspolrzednej x obiektu
	 * @return Wspolrzedna x
	 */
	public float getX(){
		return x;
	}
	/**
	 * Sprawdzenie wspolrzednej y obiektu
	 * @return Wspolrzedna y
	 */
	public float getY(){
		return y;
	}
	/**
	 * Ustawienie wspolrzednej velX obiektu
	 * @param velX
	 */
	public void setVelX(float velX){
		this.velX = velX;
	}
	/**
	 * Ustawienie wspolrzednej velY obiektu
	 * @param velY
	 */
	public void setVelY(float velY){
		this.velY = velY;
	}
	/**
	 * Sprawdzenie wspolrzednej velX obiektu
	 * @return Wspolrzedna velX
	 */
	public float getVelX(){
		return velX;
	}
	/**
	 * Sprawdzenie wspolrzednej velY obiektu
	 * @return Wspolrzedna velY
	 */
	public float getVelY(){
		return velY;
	}
	/**
	 * Ustawienie identyfikatora
	 * @param id
	 */
	public void setID(ObjectID id){
		this.id = id;
	}
	/**
	 * Sprawdzenie wartosci identyfikatora
	 * @return Wartosc id
	 */
	public ObjectID getID(){
		return id;
	}
	/**
	 * Sprawdzenie czy obiekt opada
	 * @return Wartosc zmiennej fall
	 */
	public boolean isFall() {
		return fall;
	}
	/**
	 * Ustawienie zmiennej fall definiujacej opadanie
	 * @param fall
	 */
	public void setFall(boolean fall) {
		this.fall = fall;
	}
	/**
	 * Sprawdzenie czy obiekt skacze
	 * @return Wartosc zmiennej fall
	 */
	public boolean isJump() {
		return jump;
	}
	/**
	 * Ustawienie wartosci dla zmiennej jump
	 * @param jump
	 */
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	/**
	 * Sprawdzenie kierunku ustawienia obiektu
	 * @return Wartosc zmiennej facing
	 */
	public int getFacing() {
		return facing;
	}
	/**
	 * Ustawienie kierunku obiektu
	 * @param facing
	 */
	public void setFacing(int facing) {
		this.facing = facing;
	}
	
	/**
	 * Sprawdzenie czy obiekt zyje
	 * @return Wartosc zmiennej EnemyDead
	 */
	public boolean isEnemyDead() {
		return EnemyDead;
	}
	/**
	 * Ustawienie zmiennej EnemyDead
	 * @param enemyDead
	 */
	public void setEnemyDead(boolean enemyDead) {
		EnemyDead = enemyDead;
	}
	/**
	 * Sprawdzenie czy obiekt strzela
	 * @return Wartosc zmiennej shooting
	 */
	public boolean isShooting() {
		return shooting;
	}
	/** 
	 * Ustawienie zmiennej shooting
	 * @param shooting
	 */
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	/**
	 * Sprawdzenie pozostalej ilosci zycia obiektu
	 * @return Wartosc zmiennej health;
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Ustawienie zmiennej health
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * Sprawdzenie czy obiekt zostal trafiony przez przeciwnika
	 * @return Wartosc zmiennej hit
	 */
	public boolean isHit() {
		return hit;
	}
	/**
	 * Ustawienie zmiennej hit
	 * @param hit
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}


}
