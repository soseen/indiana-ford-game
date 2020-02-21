package gm;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Klasa okreslajaca reakcje na zachowanie myszki komputerowej
 * @author Sosnowski
 * 
 */
public class MouseInput implements MouseListener{

	
	public void mouseClicked(MouseEvent e) {
		
	}
	/**
	 * Okresla co ma sie dziac po nacisnieciu przycisku myszki w danym polu
	 */
	public void mousePressed(MouseEvent e) {
		
		int moveX = e.getX();
		int moveY = e.getY();
		
		//playButton
		if (moveX >= 360 && moveX <= 560 && Game.State == Game.State.MENU){
			if (moveY >= 250 && moveY <= 350){
				Game.State = Game.State.GAME;
			}
		}
		
		//exitButton
		if (moveX >= 360 && moveX <= 560 && Game.State == Game.State.MENU){
			if (moveY >= 390 && moveY <= 490){
				System.exit(1);
			}
		}
		
		//playButton
		if (moveX >= 380 && moveX <= 580 && Game.State == Game.State.OVER){
			if (moveY >= 250 && moveY <= 350){
				Game.State = Game.State.GAME;
			}
		}
		
		//exitButton
		if (moveX >= 380 && moveX <= 580 && Game.State == Game.State.OVER){
			if (moveY >= 390 && moveY <= 490){
				System.exit(1);
			}
		}
		

			
		
	}

	public void mouseReleased(MouseEvent e) {

	}


	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {

	}

}
