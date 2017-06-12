package space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import space.invaders.SpaceInvadersGame;
import space.invaders.entities.Alien;
import space.invaders.entities.Bullet;
import space.invaders.infrastructure.Direction;
import space.invaders.infrastructure.ObjectType;

public class SplashScreen extends AbstractScreen {
	
	private Image logoImg;
	
	public SplashScreen(final SpaceInvadersGame game){
		super(game);
		init();
	}
	private void init() {
		Gdx.input.setInputProcessor(stage);
		logoImg = new Image(new Texture("splashscreen.jpg"));
		logoImg.setPosition(0, 0);
		logoImg.setSize(game.WIDTH, game.HEIGHT);
		logoImg.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				game.setScreen(new GameplayScreen(game));
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		stage.addActor(logoImg);
	} 
	
	@Override
	public void render(float delta){
		super.render(delta);
		update();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
	private void update() {
		keyboardHandle();
		
	}
	private void keyboardHandle(){
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			game.setScreen(new GameplayScreen(game));
		}
		
	}
}
