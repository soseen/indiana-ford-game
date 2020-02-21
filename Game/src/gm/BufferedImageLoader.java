package gm;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Klasa definiujaca sposob pobierania obrazu z zewnatrz.
 * @author Sosnowski
 *	
 */
public class BufferedImageLoader {

	private BufferedImage image;
	/**
	 * 
	 * @param path Sciezka do pliku graficznego
	 * @return Zwraca i implementuje obraz
	 */
	public BufferedImage LoadImage(String path){
		try{
			image = ImageIO.read(getClass().getResource(path));
		}catch(IOException e){
			e.printStackTrace();
		}
		return image;
	}
}
