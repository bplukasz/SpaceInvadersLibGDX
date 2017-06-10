package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import space.invaders.SpaceInvadersGame;
import space.invaders.infrastructure.CheckCollision;
import space.invaders.infrastructure.Direction;

public class Player extends CheckCollision {
	private int stepSize = 5;
	private static int textureWidth = 50;
	private static int textureHeigth = 50;
	private Object monitor;
	public Player(Object monitor) {
		super(425,50,new Texture("si_ship.png"),textureWidth,textureHeigth);
		this.monitor=monitor;
	}
	
	public void move(Direction direction){
		if(direction == Direction.Right)
			setX(getX()+stepSize);
		else if(direction == Direction.Left)
			setX(getX()-stepSize);
	}
	
	public Bullet shot(){
		Bullet bullet = new Bullet(getX()+textureWidth/2,getY()+textureHeigth,Direction.Up, monitor);
		return bullet;
	}
	
	public boolean leftBorderIsCrossed(SpaceInvadersGame game){
		if(getX()>0)
			return false;
		else return true;
	}
	
	public boolean rightBorderIsCrossed(SpaceInvadersGame game){
		if(getX()+textureWidth<game.WIDTH)
			return false;
		else return true;
	}
}
