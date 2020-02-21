package gm;


import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * Definiuje rozmiar i wyglad okna wyswietlajacego gre
 * @author Sosnowski
 *	
 */
public class Board extends Canvas {
	
	
	private static final long serialVersionUID = 7861092443092673490L;
	/**
	 * Stworzenie okna o danych parametrach
	 * @param width Szerokosc okna
	 * @param height Wysokosc okna
	 * @param title Tytul
	 * @param game Inicjacja gry
	 */
	public Board(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}