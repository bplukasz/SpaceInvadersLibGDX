package space.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import space.invaders.infrastructure.ObjectType;
import space.invaders.screens.SplashScreen;

public class SpaceInvadersGame extends Game {
	SpriteBatch batch;
	Texture img;
	public final static int WIDTH = 850;
	public final static int HEIGHT = 700;
	private boolean paused;
	private int score=0;
	private int maxPlayerBulletsOnStage;
	private int maxAliensBulletsOnStage;
	private int playerBulletCounter;
	private int alienBulletCounter;
	private int level;
	

	public int getScore() {
		return score;
	}

	@Override
	public void create () {
		maxPlayerBulletsOnStage = 6;
		maxAliensBulletsOnStage = 7;
		playerBulletCounter=0;
		alienBulletCounter=0;
		level=1;
		this.setScreen(new SplashScreen(this));
	}
	
	@Override
	public void dispose () {
	}
	
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public void addScore() {
		score+=1;
	}
	
	public void addScore(int scoreToAdd) {
		score+=scoreToAdd;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setMaxPlayerBulletsOnStage(int maxPlayerBulletsOnStage) {
		this.maxPlayerBulletsOnStage = maxPlayerBulletsOnStage;
	}

	public void setMaxAliensBulletsOnStage(int maxAliensBulletsOnStage) {
		this.maxAliensBulletsOnStage = maxAliensBulletsOnStage;
	}

	
	public void incrementBulletCounter(ObjectType whoCreateBullet){
		if(whoCreateBullet==ObjectType.ALIEN)alienBulletCounter++;
		if(whoCreateBullet==ObjectType.PLAYER)playerBulletCounter++;
	}
	
	public void decrementBulletCounter(ObjectType whoCreateBullet){
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
}
