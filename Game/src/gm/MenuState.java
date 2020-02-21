package gm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 * Klasa okreslajaca wyglad Menu
 * @author Sosnowski
 * 
 */
public class MenuState {
	
	
	public Rectangle playButton = new Rectangle(359, 249, 200, 100);
	public Rectangle exitButton = new Rectangle(359, 389, 200, 100);
	public Rectangle playButton1 = new Rectangle(360, 250, 199, 99);
	public Rectangle exitButton1 = new Rectangle(360, 390, 199, 99);
	/** 
	 * Stworzenie grafiki dla przycisku i tytulu
	 * @param g Parametr umozliwiajacy implementacje graficzne
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Courier New", Font.BOLD, 80);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("INDIANA FORD",170, 150);
		
		g.setColor(Color.white);
		g2d.draw(playButton);
		g2d.draw(exitButton);
		g.setColor(Color.DARK_GRAY);
		g2d.fill(playButton1);
		g2d.fill(exitButton1);
		
		g.setColor(Color.WHITE);
		Font font1 = new Font("Courier New", Font.BOLD, 40);
		g.setFont(font1);
		g.drawString("PLAY",playButton.x + 55, playButton.y + 60);
		g.drawString("EXIT",exitButton.x + 55, exitButton.y + 60);
	}

}
