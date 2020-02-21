package gm;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gm.Game.STATE;
/**
 * Klasa okreslajaca reakcje na polecenia z klawiatury
 * @author Sosnowski
 *	
 */
public class KeyInput extends KeyAdapter {
	
	Handler handler;
	/**
	 * Konstruktor
	 * @param handler Zmienna typu handler przechowujaca obiekty gry
	 */
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	/**
	 * Okresla co ma sie dziac po nacisnieciu danego klawisza z klawiatury
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(Game.State == STATE.GAME){
		for(int i=0; i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
		
		
			if(tempObject.getID() == ObjectID.Player){
				if(key == KeyEvent.VK_RIGHT && key != KeyEvent.VK_UP|| key == KeyEvent.VK_D && key != KeyEvent.VK_UP){
				tempObject.setVelX(7);
				tempObject.setFacing(1);
				}
				else if(key == KeyEvent.VK_LEFT && key != KeyEvent.VK_UP || key == KeyEvent.VK_A && key != KeyEvent.VK_UP){
				tempObject.setVelX(-7);
				tempObject.setFacing(-1);
				}
				else if(key == KeyEvent.VK_UP && tempObject.isJump() == false || key == KeyEvent.VK_W && tempObject.isJump() == false){
					tempObject.setJump(true);
					//tempObject.setMidAir(true);
					tempObject.setVelY(-11);
					}
				else if(key == KeyEvent.VK_SPACE && tempObject.isShooting() == false){
					tempObject.setShooting(true);
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 19, handler, ObjectID.Bullet, tempObject.getFacing() * 8));
				}
				else if(key == KeyEvent.VK_ESCAPE){
					//Game.State = STATE.MENU;
					System.exit(1);
				}
			}
			}
		}
	}
	/**
	 * Okresla co ma sie dziac po puszczeniu danego klawisza na klawiaturze
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i=0; i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
		
		
			if(tempObject.getID() == ObjectID.Player){
				if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
				tempObject.setVelX(0);
				
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
				tempObject.setVelX(0);
				
				if(key == KeyEvent.VK_SPACE && tempObject.isShooting() == true){
				tempObject.setShooting(false);
				}
			}
		}
	}

}
