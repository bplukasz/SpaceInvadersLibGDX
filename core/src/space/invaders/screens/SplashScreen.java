package space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import space.invaders.SpaceInvadersGame;
import space.invaders.entities.Alien;

public class SplashScreen extends AbstractScreen {
	
	private Alien monster1;
	private Alien monster2;
	private Alien monster3;
	private Texture logoTexture;
	private Image logoImg;
	public SplashScreen(final SpaceInvadersGame game){
		super(game);
		init();
		
		Timer.schedule(new Task(){
			@Override
			public void run() {
				game.setScreen(new GameplayScreen(game));
			}
		}, 8);
	}
	private void init() {
		Gdx.input.setInputProcessor(stage);
		logoTexture = new Texture("spaceinvaders-logo.png");
		monster1 = new Alien(200, 100);
		monster2 = new Alien(350, 100);
		monster3 = new Alien(500, 100);
		logoImg = new Image(logoTexture);
		logoImg.setPosition(100, 350);
		stage.addActor(logoImg);
		Timer.schedule(new Task(){
			@Override
			public void run() {
				stage.addActor(monster1);
			}
		}, 2);
		
		Timer.schedule(new Task(){
			@Override
			public void run() {
				stage.addActor(monster2);
			}
		}, 4);
		
		Timer.schedule(new Task(){
			@Override
			public void run() {
				stage.addActor(monster3);
			}
		}, 6);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
}
