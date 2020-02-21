package gm;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * Klasa definiujaca animacje w grze. Umozliwia wczytywanie pewnej sekwencji obrazow w danym odstepie czasowym
 * @author Sosnowski
 *	
 */
public class Animation {
	
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImage;
	
	public Animation(int speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i=0; i< args.length; i++){
			images[i]= args[i];
		}
		frames = args.length;
	}
	
	/**
	 * Inicjuje animacje z dana szybkoscia
	 */
	public void runAnimation(){
		index++;
		if(index>speed){
			index = 0;
			nextFrame();
		}
	}
	/**
	 * Zmienia klatke, tj. obraz w animacji
	 */
	private void nextFrame(){
		for(int i=0;i<frames;i++){
			if(count==i)
				currentImage = images[i];
		}
		count++;
		
		if(count>frames)
			count=0;
	}
	/**
	 * Stworzenie animacji na ekranie
	 * @param g Parametr umozliwiajacy implementacje grafiki
	 * @param x	Wspolrzedna x tworzonej sekwencji obrazow
	 * @param y Wspolrzedna y tworzonej sekwencji obrazow
	 */
	public void drawAnimation(Graphics g, int x, int y){
		g.drawImage(currentImage, x, y,null);
	}
	/**
	 * Stworzenie animacji na ekranie z mozliwoscia skalowania
	 * @param g Parametr umozliwiajacy implementacje grafiki
	 * @param x Wspolrzedna x tworzonej sekwencji obrazow
	 * @param y Wspolrzedna y tworzonej sekwencji obrazow
	 * @param width Szerokosc obrazow w animacji
	 * @param height Wysokosc obrazow w animacji
	 */
	public void scaledAnimation(Graphics g, int x, int y, int width, int height){
		g.drawImage(currentImage, x, y,width, height, null);
	}
	
	

}
