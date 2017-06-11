package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import space.invaders.SpaceInvadersGame;
import space.invaders.infrastructure.CheckCollision;
import space.invaders.infrastructure.Direction;

public class Alien extends CheckCollision {
	private int xStepSize=3;
	private int yStepSize=25;
	private static int textureWidth=25;
	private static int textureHeigth=25;
	public Alien(float x, float y){
		super(x,y,new Texture("space_invaders_by_maleiva.jpg"),textureWidth,textureHeigth);
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
	
	public void setAlienSpeed(int speed){
		xStepSize=speed;
	}
	
}
