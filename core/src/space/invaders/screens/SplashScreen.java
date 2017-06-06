package space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import space.invaders.SpaceInvadersGame;

public class SplashScreen extends AbstractScreen {
	
	private Texture splashTexture;
	private Texture logoTexture;
	private Image monster1;
	private Image monster2;
	private Image monster3;
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
		splashTexture = new Texture("space_invaders_by_maleiva.jpg");
		logoTexture = new Texture("spaceinvaders-logo.png");
		monster1 = new Image(splashTexture);
		monster2 = new Image(splashTexture);
		monster3 = new Image(splashTexture);
		logoImg = new Image(logoTexture);
		monster1.setSize(50, 50);
		monster2.setSize(50, 50);
		monster3.setSize(50, 50);
		monster1.setPosition(200, 100);
		monster2.setPosition(350, 100);
		monster3.setPosition(500, 100);
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
