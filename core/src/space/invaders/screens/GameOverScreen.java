package space.invaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import space.invaders.SpaceInvadersGame;

public class GameOverScreen extends AbstractScreen {
	private Image gameOverImg;
	private Label scoreLabel;
	private Label levelLabel;
	
	
	public GameOverScreen(SpaceInvadersGame game) {
		super(game);
		init();
	}


	private void init(){
		initGameOverImg();
		initScoreLabel();
		initLevelLabel();
	}
	
	private void initScoreLabel() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		scoreLabel = new Label("Wynik: ", labelStyle);
		scoreLabel.setX(700);
		scoreLabel.setY(650);
		stage.addActor(scoreLabel);
	}
	
	private void initLevelLabel() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		levelLabel = new Label("Level: ", labelStyle);
		levelLabel.setX(100);
		levelLabel.setY(650);
		stage.addActor(levelLabel);
	}
	private void initGameOverImg() {
		gameOverImg = new Image(new Texture("gameover_logo.png"));
		gameOverImg.setPosition(66, 110);
		stage.addActor(gameOverImg);
	}
	
	
	@Override
	public void render(float delta){
		super.render(delta);
		update();
		scoreLabel.setText("Wynik: " + game.getScore());
		levelLabel.setText("Level: " + game.getLevel());
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
	private void update() {
		keyboardHandle();
		
	}
	private void keyboardHandle(){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
	}

}
