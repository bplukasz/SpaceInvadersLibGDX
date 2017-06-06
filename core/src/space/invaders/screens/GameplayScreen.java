package space.invaders.screens;

import java.util.LinkedList;

import com.badlogic.gdx.scenes.scene2d.ui.List;

import space.invaders.SpaceInvadersGame;
import space.invaders.entities.Player;
import space.invaders.entities.Alien;

public class GameplayScreen extends AbstractScreen {
	
	Player player;
	LinkedList<Alien> monsters;

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
	}

}
