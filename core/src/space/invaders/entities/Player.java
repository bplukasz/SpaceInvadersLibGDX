package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import space.invaders.SpaceInvadersGame;

public class Player extends Image {
	private int stepSize = 5;
	private int textureWidth = 50;
	private int textureHeight = 50;
	private Object monitor;
	public Player(Object monitor) {
		super(new Texture("si_ship.png"));
		this.monitor=monitor;
		this.setSize(textureWidth,textureHeight);
		this.setPosition(425, 50);
	}
	
	public void move(Direction direction){
		if(direction == Direction.Right)
			setX(getX()+stepSize);
		else if(direction == Direction.Left)
			setX(getX()-stepSize);
	}
	
	public Bullet shot(){
		Bullet bullet = new Bullet(getX()+textureWidth/2,getY()+textureHeight,Direction.Up, monitor);
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
