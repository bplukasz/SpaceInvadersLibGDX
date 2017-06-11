package space.invaders.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import space.invaders.SpaceInvadersGame;
import space.invaders.domain.Rank;
import space.invaders.infrastructure.DBContext;

public class GameOverScreen extends AbstractScreen {
	private Image gameOverImg;
	private Label scoreLabel;
	private Label levelLabel;
	private Image pressStartImg;
	private Image monsterImg;
	private Image monsterSayImg;
	private TextField nickTextField;
	private boolean scoreWasSaved;
	
	
	public GameOverScreen(SpaceInvadersGame game) {
		super(game);
		Gdx.input.setInputProcessor(stage);
		init();
	}
	
	


	private void init(){
		scoreWasSaved = false;
		initGameOverImg();
		initScoreLabel();
		initLevelLabel();
		initMonsterPressEscImg();
		initPressStartImg();
		initNickTextField();
		initRankList();
	}
	
	private void initScoreLabel() {
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		scoreLabel = new Label("Wynik: ", labelStyle);
		scoreLabel.setX(700);
		scoreLabel.setY(650);
		stage.addActor(scoreLabel);
	}
	
	private void initRankList() {
	    Gdx.app.postRunnable(new Runnable(){
	        public void run(){
	        	DBContext context = new DBContext();
				List<Rank> top5Scores = context.getTop5Scores();
				LabelStyle labelStyle = new LabelStyle();
				labelStyle.font = new BitmapFont();
				int i=0;
				Label positionLabel = new Label("Position", labelStyle);
				Label nickLabel = new Label("Nick", labelStyle);
				Label levelLabel = new Label("Level", labelStyle);
				Label scoreLabel = new Label("Score", labelStyle);
				positionLabel.setPosition(325, 350-(20*i));
				nickLabel.setPosition(385, 350-(20*i));
				levelLabel.setPosition(455, 350-(20*i));
				scoreLabel.setPosition(505, 350-(20*i));
				stage.addActor(positionLabel);
				stage.addActor(nickLabel);
				stage.addActor(levelLabel);
				stage.addActor(scoreLabel);
				i++;
				for(Rank rank:top5Scores){
					positionLabel = new Label(""+i, labelStyle);
					nickLabel = new Label(""+rank.getNick(), labelStyle);
					levelLabel = new Label(""+rank.getLevel(), labelStyle);
					scoreLabel = new Label(""+rank.getScore(), labelStyle);
					positionLabel.setPosition(325, 350-(20*i));
					nickLabel.setPosition(385, 350-(20*i));
					levelLabel.setPosition(455, 350-(20*i));
					scoreLabel.setPosition(505, 350-(20*i));
					stage.addActor(positionLabel);
					stage.addActor(nickLabel);
					stage.addActor(levelLabel);
					stage.addActor(scoreLabel);
					i++;
				}
	        }
	    });
	}
	private void saveRankToDB(){
		Thread thread1 = new Thread(new Runnable() {
		    @Override
		    public void run(){
		    	Rank rank = new Rank();
		    	rank.setLevel(game.getLevel());
				rank.setScore(game.getScore());
				rank.setNick(nickTextField.getText());
				DBContext context = new DBContext();
				context.addScoreToRank(rank);}
		});
		thread1.start();
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
		gameOverImg.setPosition(0, 350);
		gameOverImg.setSize(game.WIDTH, 350);
		stage.addActor(gameOverImg);
	}
	private void initMonsterPressEscImg() {
		monsterImg = new Image(new Texture("invadergreen.png"));
		monsterImg.setPosition(50, 10);
		monsterImg.setSize(40,40);
		monsterSayImg = new Image(new Texture("pressEsc.png"));
		monsterSayImg.setPosition(70, 30);
		monsterSayImg.setSize(200,100);
		stage.addActor(monsterImg);
		stage.addActor(monsterSayImg);
	}
	
	private void initPressStartImg(){
		pressStartImg = new Image(new Texture("buttonStart.jpg"));
		pressStartImg.setSize(200,200);
		pressStartImg.setPosition(600, -40);
		stage.addActor(pressStartImg);
	}
	
	private void initNickTextField(){
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		nickTextField = new TextField("set nick and press ENTER", skin);
		nickTextField.setPosition(325, 100);		
		nickTextField.setSize(200, 50);
		stage.addActor(nickTextField);
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
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			if(!scoreWasSaved){
				scoreWasSaved=true;
				saveRankToDB();
			}
		}
	}

}
