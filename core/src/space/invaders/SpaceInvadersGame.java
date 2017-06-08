package space.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import space.invaders.screens.SplashScreen;

public class SpaceInvadersGame extends Game {
	SpriteBatch batch;
	Texture img;
	public final static int WIDTH = 850;
	public final static int HEIGHT = 700;
	private boolean paused;
	private int score=0;

	public int getScore() {
		return score;
	}

	@Override
	public void create () {
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
	
	private void addScore() {
		score+=1;
	}
	
	private void addScore(int scoreToAdd) {
		score+=scoreToAdd;
	}

}
