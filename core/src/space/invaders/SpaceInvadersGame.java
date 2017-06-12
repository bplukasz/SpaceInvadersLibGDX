package space.invaders;

import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import space.invaders.domain.Rank;
import space.invaders.entities.Alien;
import space.invaders.entities.Bullet;
import space.invaders.entities.Player;
import space.invaders.infrastructure.DBContext;
import space.invaders.infrastructure.Direction;
import space.invaders.infrastructure.ObjectType;
import space.invaders.screens.GameOverScreen;
import space.invaders.screens.SplashScreen;

public class SpaceInvadersGame extends Game {
	SpriteBatch batch;
	Texture img;
	public final static int WIDTH = 850;
	public final static int HEIGHT = 700;
	private int score=0;
	private int maxPlayerBulletsOnStage;
	private int maxAliensBulletsOnStage;
	private int playerBulletCounter;
	private int alienBulletCounter;
	private int level;
	private Stage stage;
	private Player player;
	private LinkedList<Alien> monsters;
	private LinkedList<Bullet> bulletsFromPlayer;
	private LinkedList<Bullet> bulletsFromMonsters;
	private Direction monstersDirection;
	private Object monitor;
	private float timeHelper;
	private float timeToRandomShot;

	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
	}
	
	public void startGame(Stage stage) {
		this.stage=stage;
		initVariables();
		initPlayer();
		initMonsters();
		
	}

	public void update() {
		keyboardHandle();
		moveAllMonsters();
		checkAndHandleCollisions();	
	}
	
	private void initVariables() {
		monitor = new Object();
		bulletsFromPlayer = new LinkedList<Bullet>();
		bulletsFromMonsters= new LinkedList<Bullet>();
		maxPlayerBulletsOnStage = 6;
		maxAliensBulletsOnStage = 7;
		playerBulletCounter=0;
		alienBulletCounter=0;
		timeToRandomShot=0;
		level=1;
	}

	private void initPlayer() {
		player = new Player(monitor);
		stage.addActor(player);
	}
	
	private void initMonsters() {
		monsters = new LinkedList<Alien>();
		for(int i=0; i<40; i++)
		{
			monsters.add(new Alien(63 + (i%10)*50, 600-(i/10)*50,monitor));
		}
		for(Alien monster: monsters){
			stage.addActor(monster);
		}
		monstersDirection=Direction.Right;
	}
	
	private void moveAllMonsters() {
		boolean widthBorderIsCrossedFlag = false;
		boolean bottomBorderIsCrossedFlag = false;
		for(Alien monster:monsters){
			if(monster.widthBorderIsCrossed(this))
			{
				widthBorderIsCrossedFlag=true;
				break;
			}
		}
		
		if(widthBorderIsCrossedFlag==true){
			if(monstersDirection==Direction.Left)
				monstersDirection=Direction.Right;
			else if(monstersDirection==Direction.Right)
				monstersDirection=Direction.Down;
			else monstersDirection=Direction.Left;
		}
		
		for(Alien monster:monsters){
			if(monster.bottomBorderIsCrossed(this))
			{
				bottomBorderIsCrossedFlag=true;
				break;
			}
		}
		if(bottomBorderIsCrossedFlag){
			gameOver();
		}
		
		for(Alien monster:monsters){
			monster.move(monstersDirection);
		}
		for(Alien monster: monsters)
		{
			timeToRandomShot += Gdx.graphics.getDeltaTime();
			if(timeToRandomShot>50){
				Bullet bullet = monster.TryToShot(this);
				if(bullet!=null)
				{
					Thread thread = new Thread(bullet);
					stage.addActor(bullet);
					bulletsFromMonsters.add(bullet);
					thread.start();
					timeToRandomShot=0;
				}
			}

		}
		if(monsters.isEmpty())nextLevel();
	}
	
	private void checkAndHandleCollisions() {
		LinkedList<Bullet> bulletsToRemove = new LinkedList<Bullet>();
		LinkedList<Alien> monstersToRemove = new LinkedList<Alien>();
		for(Bullet bullet: bulletsFromPlayer)
		{
			for(Alien monster: monsters){
				if(bullet.overlaps(monster))
				{
					bulletsToRemove.add(bullet);
					monstersToRemove.add(monster);
				}
			}
			if(bullet.gameBorderIsCrossed(this)){
				bulletsToRemove.add(bullet);
			}
		}
		
		for(Alien monster: monstersToRemove){
			this.addScore();
			monsters.remove(monster);
			monster.remove();
		}
		monstersToRemove.clear();
		for(Bullet bullet: bulletsToRemove){
			bullet.stopTheThread();
			bulletsFromPlayer.remove(bullet);
			bullet.remove();
			this.decrementBulletCounter(ObjectType.PLAYER);
		}
		bulletsToRemove.clear();
		for(Bullet bullet: bulletsFromMonsters)
		{
				if(bullet.overlaps(player))
				{
					bulletsToRemove.add(bullet);
					gameOver();
				}
			if(bullet.gameBorderIsCrossed(this)){
				bulletsToRemove.add(bullet);
			}
		}
		for(Bullet bullet: bulletsToRemove){
			bullet.stopTheThread();
			bulletsFromMonsters.remove(bullet);
			bullet.remove();
			this.decrementBulletCounter(ObjectType.ALIEN);
		}
		bulletsToRemove.clear();
		synchronized(monitor){
			monitor.notifyAll();
		}
	}
	
	private void nextLevel() {
		initMonsters();
		level++;
		for(Alien monster: monsters){
			monster.setAlienSpeed(level+2);
		}
		maxAliensBulletsOnStage=level+7;
		maxPlayerBulletsOnStage=level+6;
	}
	
	private void gameOver() {

		this.setScreen(new GameOverScreen(this));
	}
	
	private void keyboardHandle(){
		if(Gdx.input.isKeyPressed(Keys.A)){
			if(!player.leftBorderIsCrossed(this))
				player.move(Direction.Left);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			if(!player.rightBorderIsCrossed(this))
				player.move(Direction.Right);			
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			timeHelper += Gdx.graphics.getDeltaTime();
			if(this.canICreateBullet(ObjectType.PLAYER))
			{
				if(timeHelper > 0.1)
				{
					Bullet bullet;
					bullet = player.shot();
					Thread thread = new Thread(bullet);
					stage.addActor(bullet);
					bulletsFromPlayer.add(bullet);
					thread.start();
					timeHelper = 0;
					this.incrementBulletCounter(ObjectType.PLAYER);
				}
			}
		}
	}
	
	private void incrementBulletCounter(ObjectType whoCreateBullet){
		if(whoCreateBullet==ObjectType.ALIEN)alienBulletCounter++;
		if(whoCreateBullet==ObjectType.PLAYER)playerBulletCounter++;
	}
	
	private void decrementBulletCounter(ObjectType whoCreateBullet){
		if(whoCreateBullet==ObjectType.ALIEN)alienBulletCounter--;
		if(whoCreateBullet==ObjectType.PLAYER)playerBulletCounter--;
	}
	
	public boolean canICreateBullet(ObjectType who){
		if(who==ObjectType.ALIEN){
			if(alienBulletCounter<maxAliensBulletsOnStage)return true;
		}
		if(who==ObjectType.PLAYER){
			if(playerBulletCounter<maxPlayerBulletsOnStage)return true;
		}
		return false;
	}

	private void addScore() {
		score+=1;
	}
	
	private void addScore(int scoreToAdd) {
		score+=scoreToAdd;
	}
	
	//Getters and setters
	public int getAlienBulletCounter() {
		return alienBulletCounter;
	}

	public void setAlienBulletCounter(int alienBulletCounter) {
		this.alienBulletCounter = alienBulletCounter;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getScore() {
		return score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void dispose () {
	}
}