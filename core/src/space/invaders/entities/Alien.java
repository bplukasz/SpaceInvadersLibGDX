package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import space.invaders.SpaceInvadersGame;

public class Alien extends Image {
	private int xStepSize=3;
	private int yStepSize=25;
	private int textureWidth=25;
	private int textureHeigth=25;
	public Alien(int x, int y){
		super(new Texture("space_invaders_by_maleiva.jpg"));
		this.setSize(textureWidth,textureHeigth);
		this.setPosition(x, y);
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
}
