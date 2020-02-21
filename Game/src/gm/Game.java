package gm;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Glowna klasa gry. Tutaj znajduja sie metody odpowiedzialne za inicjalizacje obiektow, ich update czy renderowanie.
 * @author Sosnowski
 *	
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1617698019725255739L;
	
	public static int WIDTH;
	public static int HEIGHT;
	public static int level = 1;
	
	private Thread thread;
	
	private MenuState Menu;
	private GameOverState Over;
	
	private boolean running = false;
	
	Handler handler;
	Camera cam;
	
	static Texture tex;
	
	private BufferedImage lvl1 = null;
	private BufferedImage background = null;
	private BufferedImage backgroundMenu = null;
	
	public static enum STATE{
		MENU,
		GAME,
		OVER
	};
	
	public static STATE State = STATE.MENU;
	
	/**
	 * Inicjalizacja obiektow gry.
	 */
	private void init(){
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		requestFocus();
		
		tex = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		lvl1 = loader.LoadImage("/lvl1.png");
		background = loader.LoadImage("/bg.jpg");
		backgroundMenu = loader.LoadImage("/menu.png");
		
		cam = new Camera(0,0);
		handler = new Handler(cam);
		
		Menu = new MenuState();
		Over = new GameOverState();
		
		handler.LoadImageLevel(lvl1);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput());	
	}

	/**
	 * Inicjalizacja watka gry
	 */
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Koñczenie dzia³ania programu
	 */
	public synchronized void stop(){
	try{
		thread.join();
		running = false;
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	}
	
	/**
	 * G³ówna pêtla gry. Metoda ta inicjuje pocz¹tkowe elementy gry a nastêpnie, dopóki w¹tek jest dzia³aj¹cy,
	 * zapewnia aktualizacje danych o obiektach wewn¹trz gry.
	 */
	public void run()
    {
		init();
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(running)
        {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while(delta >=1)
                            {
                                tick();
                                delta--;
                            }
                            if(running)
                                render();
                            
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
                            }
        }
                stop();
    }
    
	/**
	 * Metoda aktualizujaca dane o obiektach w grze.
	 */
    private void tick(){
    	if(State == STATE.GAME){
    	handler.tick();
    	for(int i=0; i < handler.object.size(); i++){
    		if(handler.object.get(i).getID() == ObjectID.Player){
    	    	cam.tick(handler.object.get(i));
    		}
    	}
    	}
    }
    /**
     * Metoda odpowiedzialna za wyœwietlanie obiektów oraz interface'u.
     */
    private void render(){
    	
    	BufferStrategy bs = this.getBufferStrategy();
    	if(bs == null){
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	Graphics2D g2d = (Graphics2D) g;
    	
    	g.drawImage(background, 0, 0, this);
    	
    	
    	if(State == STATE.MENU){
    	g.drawImage(backgroundMenu, 0, 0, this);
    	Menu.render(g);	
    	g.dispose();
    	bs.show();
    	}
    	
    	else if(State == STATE.OVER){
    	g.drawImage(backgroundMenu, 0, 0, this);
    	Over.render(g);
    	g.dispose();
    	bs.show();
    	}
    	
    	g2d.translate(cam.getX(), cam.getY());
    	
    	if(State == STATE.GAME){
    	handler.render(g);
    	g.dispose();
    	bs.show();
    	g2d.translate(-cam.getX(), -cam.getY());
    	}
    	
    	
    }
    
    /**
     * Metoda pobieraj¹ca teksturê
     * @return Zwraca dan¹ teksturê.
     */
    public static Texture getInstance(){
    	return tex;
    }
    
    /**
     * Uruchomienie okna i inicjalizacja gry.
     */
    public static void main(String[] args) {
    new Board(960, 640, "Indiana Ford", new Game());
    }
}