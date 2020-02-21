package gm;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import gm.Game.STATE;
/**
 * Klasa przechowujaca obiekty i umozliwiajaca zarzadzanie nimi
 * @author Sosnowski
 * 
 */
public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private BufferedImage lvl1 = null, lvl2 = null, lvl3 = null;
	private int currentHP = 0;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	private GameObject tempObject;
	private Camera cam;
	/**
	 * Konstruktor
	 * @param cam Zmienna typu Camera
	 */
	public Handler(Camera cam){
		this.cam = cam;
		lvl1 = loader.LoadImage("/lvl1.png");
		lvl2 = loader.LoadImage("/lvl2.png");
		lvl3 = loader.LoadImage("/lvl3.png");
	}
	/**
	 * Aktualizacja danych o obiektach
	 */
	public void tick(){
		for(int i=0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	/**
	 * Tworzenie grafiki dla obiektow
	 * @param g Parametr umozliwiajacy implementacje grafiki
	 */
	public void render(Graphics g){
		for(int j=0; j<object.size(); j++){
			tempObject = object.get(j);
			tempObject.render(g);
		}
	}
	/**
	 * Metoda umozliwiajaca stworzenie Mapy. Odczytuje ona pixele z pliku graficznego i tworzy obiekty zgodnie z danymi w nim zawartymi
	 * @param image Obraz zewnetrzny
	 */
	public void LoadImageLevel(BufferedImage image){
    	
		
		int w = image.getWidth();
    	int h = image.getHeight();
    	
    	for(int xx=0;xx<h;xx++){
    		for(int yy=0;yy<w;yy++){
    			int pixel = image.getRGB(xx,yy);
    			int red = (pixel>>16) & 0xff;
    			int green = (pixel>>8) & 0xff;
    			int blue = (pixel) & 0xff;
    			
    			if(red == 255 & green == 255 & blue == 255){
    				this.addObject(new Block(xx*25, yy*25,0,this,ObjectID.Block));	
    			}
    			
    			else if(red == 255 & green == 0 & blue == 0){
    				this.addObject(new Block(xx*25, yy*25,1,this,ObjectID.Block));	
    			}
    			
    			else if(red == 255 & green == 128 & blue == 0){
    				this.addObject(new Player(xx*25, yy*25, this, cam, ObjectID.Player));	
    			}
    			
    			else if(red == 0 & green == 0 & blue == 255){
    				this.addObject(new Enemy(xx*25,yy*25,this,ObjectID.Enemy));	
    			}
    			
    			else if(red == 0 & green == 255 & blue == 0){
    				this.addObject(new Enemy2(xx*25,yy*25,this,ObjectID.Enemy2));	
    			}
    			
    			else if(red == 255 & green == 255 & blue == 0){
    				this.addObject(new Checkpoint(xx*25, yy*25, this, ObjectID.Checkpoint));	
    			}
    			
    			else if(red == 64 & green == 0 & blue == 64){
    				this.addObject(new Boss(xx*25, yy*25,this, ObjectID.Boss));	
    			}
    				
    		}
    	}
    }
	/**
	 * Tworzy obiekt umozliwiajacy zakonczenie gry po pokonaniu ostatniego przeciwnika
	 */
	public void SpawnLoot(){
		this.addObject(new Checkpoint(900,800, this, ObjectID.Checkpoint));
	}
	/**
	 * Generuje obiekty spadajacych meteorow w losowych punktach na mapie
	 */
	public void SpawnMeteor(){
		Random random = new Random();
		int X;
		int Y;
		X = random.nextInt(900) + 50;
		Y = random.nextInt(100) + 285;
		addObject(new Meteor(X,Y,this,ObjectID.Meteor));
	}
	/**
	 * Zmienia aktualny poziom mapy poprzez wyczyszczenie starego i zaladowanie nowej mapy
	 */
	public void switchLevel(){
		for(int i=0; i< this.object.size(); i++){
			
			GameObject tempObject = this.object.get(i);
			
			if(tempObject.getID() == ObjectID.Player){
				currentHP = tempObject.getHealth();
			}
		}
		
		clearLevel();
		cam.setX(0);
		switch(Game.level)
		{
		case 1:
			LoadImageLevel(lvl2);
			break;
		case 2:
			LoadImageLevel(lvl3);
			break;
		case 3:
			Game.State = STATE.MENU;
			resetLevel();
			currentHP = 3;
			Game.level = 0;
		}
		
for(int i=0; i< this.object.size(); i++){
			
			GameObject tempObject = this.object.get(i);
			
			if(tempObject.getID() == ObjectID.Player){
				tempObject.setHealth(currentHP);
			}
		}

		Game.level++;
	}
	/**
	 * Definiuje sposob resetowania poziomu mapy
	 */
	public void resetLevel(){
		clearLevel();
		cam.setX(0);
		Game.level = 1;
		LoadImageLevel(lvl1);
	}
	/**
	 * Dodaje obiekt do listy
	 * @param object Obiekt na liscie obiektow gry
	 */
	public void addObject(GameObject object){
		this.object.add(object);
	}
	/**
	 * Usuwa obiekt z list 
	 * @param object Obiekt na liscie obiektow gry
	 */
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	/**
	 * Usuwa wszystkie obiekty z listy
	 */
	private void clearLevel(){
		this.object.clear();
	}
	
}
