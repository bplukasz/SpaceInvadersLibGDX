package space.invaders.screens;

import space.invaders.SpaceInvadersGame;
import space.invaders.entities.Player;

public class GameplayScreen extends AbstractScreen {
	
	Player player;

	public GameplayScreen(SpaceInvadersGame game) {
		super(game);
		initPlayer();
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
		
		
	}

	private void initPlayer() {
		player = new Player();
		stage.addActor(player);
	}

}
