package space.invaders.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import space.invaders.SpaceInvadersGame;

public class GameplayScreen extends AbstractScreen {
	private Label scoreLabel;
	private Label levelLabel;
	
	public GameplayScreen(SpaceInvadersGame game) {
		super(game);
		initScoreLabel();
		initLevelLabel();
		game.startGame(stage);
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

	private void updateLabels(){
		scoreLabel.setText("Wynik: " + game.getScore());
		levelLabel.setText("Level: " + game.getLevel());
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		game.update();
		updateLabels();
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
}