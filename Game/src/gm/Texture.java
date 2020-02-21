package gm;

import java.awt.image.BufferedImage;
/**
 * Klasa ktora laduje i pozwala generowac obrazy
 * @author Sosnowski
 * 
 */
public class Texture {

	SpriteSheet tl;
	SpriteSheet gr;
	SpriteSheet idl;
	SpriteSheet idlL;
	SpriteSheet jmp;
	SpriteSheet jmpL;
	SpriteSheet jmp1;
	SpriteSheet jmp1L;
	SpriteSheet rn;
	SpriteSheet rnL;
	SpriteSheet rn1;
	SpriteSheet rn1L;
	SpriteSheet rn2;
	SpriteSheet rn2L;
	SpriteSheet sht;
	SpriteSheet shtL;
	SpriteSheet blk;
	
	SpriteSheet h;
	
	SpriteSheet b1;
	SpriteSheet b1L;
	SpriteSheet b2;
	SpriteSheet b2L;
	SpriteSheet blt;
	SpriteSheet bd;
	
	SpriteSheet s;
	SpriteSheet sU;
	SpriteSheet s1;
	SpriteSheet sU1;
	
	SpriteSheet bs1;
	SpriteSheet bs1L;
	SpriteSheet bs2;
	SpriteSheet bs2L;
	SpriteSheet bsD;
	
	SpriteSheet m;
	
	SpriteSheet p;
	
	
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage tile = null;
	private BufferedImage grass = null;
	
	
	//player
	private BufferedImage idle = null;
	private BufferedImage idleL = null;
	private BufferedImage jump = null;
	private BufferedImage jumpL = null;
	private BufferedImage jump1 = null;
	private BufferedImage jump1L = null;
	private BufferedImage run = null;
	private BufferedImage runL = null;
	private BufferedImage run1 = null;
	private BufferedImage run1L = null;
	private BufferedImage run2 = null;
	private BufferedImage run2L = null;
	private BufferedImage shoot = null;
	private BufferedImage shootL = null;
	private BufferedImage heart = null;
	private BufferedImage blink = null;
	
	//Enemy
	private BufferedImage bat1 = null;
	private BufferedImage bat1L = null;
	private BufferedImage bat2 = null;
	private BufferedImage bat2L = null;
	private BufferedImage batD = null;
	
	private BufferedImage spider = null;
	private BufferedImage spiderU = null;
	private BufferedImage spider1 = null;
	private BufferedImage spiderU1 = null;
	
	private BufferedImage boss1 = null;
	private BufferedImage boss1L = null;
	private BufferedImage boss2 = null;
	private BufferedImage boss2L = null;
	private BufferedImage bossD = null;
	
	private BufferedImage bullet = null;
	private BufferedImage meteor = null;
	private BufferedImage port = null;
	
	public BufferedImage Tile;
	public BufferedImage Grass;
	public BufferedImage Idle;
	public BufferedImage IdleL;
	public BufferedImage Jump;
	public BufferedImage JumpL;
	public BufferedImage Jump1;
	public BufferedImage Jump1L;
	public BufferedImage Run;
	public BufferedImage RunL;
	public BufferedImage Run1;
	public BufferedImage Run1L;
	public BufferedImage Run2;
	public BufferedImage Run2L;
	public BufferedImage Shoot;
	public BufferedImage ShootL;
	public BufferedImage Heart;
	public BufferedImage Blink;
	public BufferedImage Bat1;
	public BufferedImage Bat1L;
	public BufferedImage Bat2;
	public BufferedImage Bat2L;
	public BufferedImage BatD;
	public BufferedImage Spider;
	public BufferedImage SpiderU;
	public BufferedImage SpiderU1;
	public BufferedImage Spider1;
	public BufferedImage Boss1;
	public BufferedImage Boss1L;
	public BufferedImage Boss2;
	public BufferedImage Boss2L;
	public BufferedImage BossD;
	public BufferedImage Bullet;
	public BufferedImage Meteor;
	public BufferedImage Port;

	/**
	 * Zaladowanie obrazow
	 */
	public Texture(){
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			grass = loader.LoadImage("/StoneGrass.png");
			tile = loader.LoadImage("/Stone.png");
			idle = loader.LoadImage("/Idle.png");
			idleL = loader.LoadImage("/Idle_left.png");
			jump = loader.LoadImage("/Jump.png");
			jumpL = loader.LoadImage("/Jump_left.png");
			jump1 = loader.LoadImage("/Jump1.png");
			jump1L = loader.LoadImage("/Jump1_left.png");
			run = loader.LoadImage("/Run.png");
			runL = loader.LoadImage("/Run_left.png");
			run1 = loader.LoadImage("/Run1.png");
			run1L = loader.LoadImage("/Run1_left.png");
			run2 = loader.LoadImage("/Run2.png");
			run2L = loader.LoadImage("/Run2_left.png");
			heart = loader.LoadImage("/Heart.png");
			shoot = loader.LoadImage("/Shoot1.png");
			shootL = loader.LoadImage("/Shoot1_left.png");
			blink = loader.LoadImage("/Blink.png");
			bat1 = loader.LoadImage("/bat1.png");
			bat1L = loader.LoadImage("/bat1_left.png");
			bat2 = loader.LoadImage("/bat2.png");
			bat2L = loader.LoadImage("/bat2_left.png");
			batD = loader.LoadImage("/bat_dead.png");
			spider = loader.LoadImage("/Spider.png");
			spiderU = loader.LoadImage("/SpiderU.png");
			spider1 = loader.LoadImage("/Spider1.png");
			spiderU1 = loader.LoadImage("/Spider1U.png");
			boss1 = loader.LoadImage("/Dragon1.png");
			boss1L = loader.LoadImage("/Dragon1_left.png");
			boss2 = loader.LoadImage("/Dragon2.png");
			boss2L = loader.LoadImage("/Dragon2_left.png");
			bossD = loader.LoadImage("/DragonD.png");
			bullet = loader.LoadImage("/Bullet.png");
			meteor = loader.LoadImage("/Meteor.png");
			port = loader.LoadImage("/Port.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		tl = new SpriteSheet(tile);
		gr = new SpriteSheet(grass);
		idl = new SpriteSheet(idle);
		idlL = new SpriteSheet(idleL);
		jmp = new SpriteSheet(jump);
		jmpL = new SpriteSheet(jumpL);
		jmp1 = new SpriteSheet(jump1);
		jmp1L = new SpriteSheet(jump1L);
		rn = new SpriteSheet(run);
		rnL = new SpriteSheet(runL);
		rn1 = new SpriteSheet(run1);
		rn1L = new SpriteSheet(run1L);
		rn2 = new SpriteSheet(run2);
		rn2L = new SpriteSheet(run2L);
		h = new SpriteSheet(heart);
		sht = new SpriteSheet(shoot);
		shtL = new SpriteSheet(shootL);
		blk = new SpriteSheet(blink);
		b1 = new SpriteSheet(bat1);
		b1L = new SpriteSheet(bat1L);
		b2 = new SpriteSheet(bat2);
		b2L = new SpriteSheet(bat2L);
		bd = new SpriteSheet(batD);
		s = new SpriteSheet(spider);
		sU = new SpriteSheet(spiderU);
		s1 = new SpriteSheet(spider1);
		sU1 = new SpriteSheet(spiderU1);
		bs1 = new SpriteSheet(boss1);
		bs1L = new SpriteSheet(boss1L);
		bs2 = new SpriteSheet(boss2);
		bs2L = new SpriteSheet(boss2L);
		bsD = new SpriteSheet(bossD);
		blt = new SpriteSheet(bullet);
		m = new SpriteSheet(meteor);
		p = new SpriteSheet(port);
		
		
		getTextures();
	}
	private void getTextures(){
		Run = rn.genImage(32, 64);
		RunL = rnL.genImage(32, 64);
		Run1 = rn1.genImage(32, 64);
		Run1L = rn1L.genImage(32, 64);
		Run2 = rn2.genImage(32, 64);
		Run2L = rn2L.genImage(32, 64);
		Shoot = sht.genImage(32, 64);
		ShootL = shtL.genImage(32, 64);
		
		Jump = jmp.genImage(32, 64);
		JumpL = jmpL.genImage(32, 64);
		Jump1 = jmp1.genImage(32, 64);
		Jump1L = jmp1L.genImage(32, 64);
		
		Idle = idl.genImage(32, 64);
		IdleL = idlL.genImage(32, 64);
		
		Heart = h.genImage(32, 32);
		
		Blink = blk.genImage(32, 64);
		
		Bat1 = b1.genImage(32, 64);
		Bat1L = b1L.genImage(32, 64);
		Bat2 = b2.genImage(32, 64);
		Bat2L = b2L.genImage(32, 64);
		BatD = bd.genImage(32, 64);
		
		Boss1 =  bs1.genImage(300, 150);
		Boss1L =  bs1L.genImage(300, 150);
		Boss2 =  bs2.genImage(300, 150);
		Boss2L =  bs2L.genImage(300, 150);
		BossD =  bsD.genImage(300, 150);
		
		Spider = s.genImage(40, 40);
		SpiderU = sU.genImage(40, 40);
		Spider1 = s1.genImage(40, 40);
		SpiderU1 = sU1.genImage(40, 40);
		
		Bullet = blt.genImage(10, 10);
		Meteor = m.genImage(17, 18);
		Port = p.genImage(42, 42);
		
		Grass = gr.genImage(25,25);
		Tile = tl.genImage(25,25);
	}
}
