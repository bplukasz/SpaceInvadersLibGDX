package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import space.invaders.SpaceInvadersGame;
import space.invaders.infrastructure.CheckCollision;
import space.invaders.infrastructure.Direction;
import space.invaders.infrastructure.ObjectType;

public class Alien extends CheckCollision {
	private int xStepSize=3;
	private int yStepSize=25;
	private static int textureWidth=25;
	private static int textureHeigth=25;
	private Object monitor;
	public Alien(float x, float y, Object monitor){
		super(x,y,new Texture("invadergreen.png"),textureWidth,textureHeigth);
		this.monitor=monitor;
	}
	
	public void move(Direction direction){
		if(direction == Direction.Right)
			setX(getX()+xStepSize);
		else if(direction == Direction.Left)
			setX(getX()-xStepSize);
		else if(direction == Direction.Up)
			setY(getY()+yStepSize);
		else if(direction == Direction.Down)
			setY(getY()-yStepSize);
	}
	
	public boolean widthBorderIsCrossed(SpaceInvadersGame game){
		if(getX()+textureWidth<game.WIDTH&&getX()>0)
			return false;
		else return true;
	}
	
	public boolean bottomBorderIsCrossed(SpaceInvadersGame game){
		if(this.getY()<=100)
			return true;
		else return false;
	}
	
	public Bullet TryToShot(SpaceInvadersGame game){
		if(game.canICreateBullet(ObjectType.ALIEN)){
			float rand = MathUtils.random(0,100);
			if(rand<0.01){
				game.setAlienBulletCounter(game.getAlienBulletCounter()+1);
				return new Bullet(getX()+textureWidth/2, getY(),Direction.Down,monitor);
			}
		}
		return null;
	}
	
	public void setAlienSpeed(int speed){
		xStepSize=speed;
	}
	
}
