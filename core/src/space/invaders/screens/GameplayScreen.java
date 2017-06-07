package space.invaders.screens;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import space.invaders.SpaceInvadersGame;
import space.invaders.entities.Player;
import space.invaders.entities.Alien;
import space.invaders.entities.Direction;

public class GameplayScreen extends AbstractScreen {
	
	Player player;
	LinkedList<Alien> monsters;
	Direction monstersDirection;
	
	public GameplayScreen(SpaceInvadersGame game) {
		super(game);
		initPlayer();
		initMonsters();
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
	}

	private void moveAllMonsters() {
		boolean borderIsCrossedFlag = false;
		for(Alien monster:monsters){
			if(monster.borderIsCrossed(game))
			{
				borderIsCrossedFlag=true;
				break;
			}
		}
		
		if(borderIsCrossedFlag==true){
			if(monstersDirection==Direction.Left)
				monstersDirection=Direction.Right;
			else monstersDirection=Direction.Left;
		}
		
		for(Alien monster:monsters){
			monster.move(monstersDirection);
		}
	}

	private void initPlayer() {
		player = new Player();
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
	}
}
