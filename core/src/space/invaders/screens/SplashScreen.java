package space.invaders.screens;

import com.badlogic.gdx.graphics.Texture;
import space.invaders.SpaceInvadersGame;

public class SplashScreen extends AbstractScreen {
	
	private Texture splashImg;
	public SplashScreen(SpaceInvadersGame game){
		super(game);
		init();
	}
	private void init() {
		splashImg= new Texture("space_invaders_by_maleiva.jpg");
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		spriteBatch.begin();
		spriteBatch.draw(splashImg,0,0);
		spriteBatch.end();
	}
}
