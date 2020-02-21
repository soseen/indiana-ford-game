package gm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * 
 * @author Sosnowski
 * Klasa okreslajaca wyglad menu po niepowodzeniu gracza
 */
public class GameOverState {

	private Rectangle playButtonO = new Rectangle(379, 249, 200, 100);
	private Rectangle exitButtonO = new Rectangle(379, 389, 200, 100);
	private Rectangle playButton1O = new Rectangle(380, 250, 199, 99);
	private Rectangle exitButton1O = new Rectangle(380, 390, 199, 99);
	/** 
	 * Stworzenie grafiki dla przycisku i tytulu
	 * @param g Parametr umozliwiajacy implementacje graficzne
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Courier New", Font.BOLD, 80);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString("GAME OVER", 250, 150);
		
		g.setColor(Color.white);
		g2d.draw(playButtonO);
		g2d.draw(exitButtonO);
		g.setColor(Color.DARK_GRAY);
		g2d.fill(playButton1O);
		g2d.fill(exitButton1O);
		
		g.setColor(Color.WHITE);
		Font font1 = new Font("Courier New", Font.BOLD, 40);
		g.setFont(font1);
		g.drawString("PLAY",playButtonO.x + 55, playButtonO.y + 60);
		g.drawString("EXIT",exitButtonO.x + 55, exitButtonO.y + 60);

	}
}
