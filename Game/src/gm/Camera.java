package gm;
/**
 * Klasa definiujaca polozenie kamery wzgledem pozycji gracza
 * @author Sosnowski
 *	
 */
public class Camera {
	
	private float x;
	private float y;
	/**
	 * Konstruktor kamery
	 * @param x Wspolrzedna x kamery
	 * @param y Wspolrzedna y kamery
	 */
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Aktualizuje pozycje kamery wzgledem pozycji gracza
	 * @param Player Obiekt gracza
	 */
	public void tick(GameObject Player){
		x = -Player.getX() + Game.WIDTH/2;
		y = -Player.getY() + Game.HEIGHT/2;
	}
	/**
	 * Ustaw wspolrzedna x kamery
	 * @param x Wspolrzedna
	 */
	public void setX(float x){
		this.x = x;
	}
	/**
	 * Ustawia wspolrzedna y kamery
	 * @param y Wspolrzedna
	 */
	public void setY(float y){
		this.y = y;
	}
	/**
	 * Odczytuje wspolrzedna x kamery
	 * @return Zwraca wspolrzedna x
	 */
	public float getX(){
		return x;
	}
	/**
	 * Odczytuje wspolrzedna y kamery
	 * @return Zwraca wspolrzedna y
	 */
	public float getY(){
		return y;
	}
	


}
