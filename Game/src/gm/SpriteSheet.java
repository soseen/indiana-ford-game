package gm;

import java.awt.image.BufferedImage;
/**
 * Klasa umozliwiajaca generowanie pojedynczych obrazow badz obrazow z tzw. SpriteSheetow
 * @author Sosnowski
 * 
 */
public class SpriteSheet {
	
	private BufferedImage image;
	/**
	 * Konstruktor 
	 * @param image Obraz
	 */
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	/**
	 * Generuje obraz pozyskany z danego piksela w pliku zrodlowym
	 * @param col Kolumna obrazu w pliku
	 * @param row Wiersz obrazu w pliku
	 * @param width Szerokosc obrazu
	 * @param height Wysokosc obrazu
	 * @return Obraz 
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = image.getSubimage((col*width) - width,(row*height)-height,width,height);
		return img;
	}
	/**
	 * Generuje obraz z pliku
	 * @param width Szerokosc obrazu
	 * @param height Wysokosc obrazu
	 * @return Obraz
	 */
	public BufferedImage genImage(int width, int height){
		BufferedImage img = image;
		return img;
	}
}
