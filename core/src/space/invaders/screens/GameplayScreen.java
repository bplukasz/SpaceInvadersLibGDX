package space.invaders.screens;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import space.invaders.SpaceInvadersGame;
import space.invaders.entities.Player;
import space.invaders.infrastructure.Direction;
import space.invaders.infrastructure.ObjectType;
import space.invaders.entities.Alien;
import space.invaders.entities.Bullet;

public class GameplayScreen extends AbstractScreen {
	
	private Player player;
	private LinkedList<Alien> monsters;
	private LinkedList<Bullet> bullets;
	private Direction monstersDirection;
	private Label scoreLabel;
	private Label levelLabel;
	private Object monitor;
	private float timeHelper;
	
	
	public GameplayScreen(SpaceInvadersGame game) {
		super(game);
		monitor = new Object();
		bullets = new LinkedList<Bullet>();
		initPlayer();
		initMonsters();
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
		moveAllMonsters();
		scoreLabel.setText("Wynik: " + game.getScore());
		levelLabel.setText("Level: " + game.getLevel());
		checkAndHandleCollisions();	
	}

	private void checkAndHandleCollisions() {
		LinkedList<Bullet> bulletsToRemove = new LinkedList<Bullet>();
		LinkedList<Alien> monstersToRemove = new LinkedList<Alien>();
		for(Bullet bullet: bullets)
		{
			for(Alien monster: monsters){
				if(bullet.overlaps(monster))
				{
					bulletsToRemove.add(bullet);
					monstersToRemove.add(monster);
				}
			}
			if(bullet.gameBorderIsCrossed(game)){
				bulletsToRemove.add(bullet);
			}
		}
		for(Alien monster: monstersToRemove){
			game.addScore();
			monsters.remove(monster);
			monster.remove();
		}
		for(Bullet bullet: bulletsToRemove){
			bullets.remove(bullet);
			bullet.remove();
			game.decrementBulletCounter(ObjectType.PLAYER);
		}
		
		synchronized(monitor){
			monitor.notifyAll();
		}
	}

	private void moveAllMonsters() {
		boolean widthBorderIsCrossedFlag = false;
		boolean bottomBorderIsCrossedFlag = false;
		for(Alien monster:monsters){
			if(monster.widthBorderIsCrossed(game))
			{
				widthBorderIsCrossedFlag=true;
				break;
			}
		}
		
		if(widthBorderIsCrossedFlag==true){
			if(monstersDirection==Direction.Left)
				monstersDirection=Direction.Right;
			else if(monstersDirection==Direction.Right)
				monstersDirection=Direction.Down;
			else monstersDirection=Direction.Left;
		}
		
		for(Alien monster:monsters){
			if(monster.bottomBorderIsCrossed(game))
			{
				bottomBorderIsCrossedFlag=true;
				break;
			}
		}
		if(bottomBorderIsCrossedFlag){
			gameOver();
		}
		
		for(Alien monster:monsters){
			monster.move(monstersDirection);
		}
		
		if(monsters.isEmpty())nextLevel();
	}

	private void gameOver() {
		game.setScreen(new GameOverScreen(game));
	}

	private void nextLevel() {
		initMonsters();
		game.setLevel(game.getLevel()+1);
		for(Alien monster: monsters){
			monster.setAlienSpeed(game.getLevel()+2);
		}
		game.setMaxAliensBulletsOnStage(game.getLevel()+7);
		game.setMaxPlayerBulletsOnStage(game.getLevel()+6);
	}

	private void initPlayer() {
		player = new Player(monitor);
		stage.addActor(player);
	}
	
	private void initMonsters() {
		monsters = new LinkedList<Alien>();
		for(int i=0; i<40; i++)
		{
			monsters.add(new Alien(63 + (i%10)*50, 600-(i/10)*50));
		}
		for(Alien monster: monsters){
			stage.addActor(monster);
		}
		monstersDirection=Direction.Right;
	}
	
	private void keyboardHandle(){
		if(Gdx.input.isKeyPressed(Keys.A)){
			if(!player.leftBorderIsCrossed(game))
				player.move(Direction.Left);
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			if(!player.rightBorderIsCrossed(game))
				player.move(Direction.Right);			
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			timeHelper += Gdx.graphics.getDeltaTime();
			if(game.canICreateBullet(ObjectType.PLAYER))
			{
				if(timeHelper > 0.1)
				{
					Bullet bullet;
					bullet = player.shot();
					Thread thread = new Thread(bullet);
					stage.addActor(bullet);
					bullets.add(bullet);
					thread.start();
					timeHelper = 0;
					game.incrementBulletCounter(ObjectType.PLAYER);
				}
			}
		}
	}
}