package space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import space.invaders.SpaceInvadersGame;

public abstract class AbstractScreen implements Screen {
	protected SpaceInvadersGame game;
	protected Stage stage;
	protected SpriteBatch spriteBatch;
	
	public AbstractScreen(SpaceInvadersGame game){
		this.game=game;
	    spriteBatch = new SpriteBatch();
	    stage = new Stage(new StretchViewport(game.WIDTH, game.HEIGHT));
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		clearScreen();
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		game.setPaused(true);
	}

	@Override
	public void resume() {
		game.setPaused(false);
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
