package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import space.invaders.infrastructure.CheckCollision;
import space.invaders.infrastructure.Direction;

public class Bullet extends CheckCollision implements Runnable {
	private static int textureWidth=3;
	private static int textureHeigth=6;
	private int yStepSize=4;
	private Direction direction;
	private Object monitor;
	
	public Bullet(float x, float y, Direction direction, Object monitor){
		super(x,y,new Texture("bullet.jpg"),textureWidth,textureHeigth);
		this.monitor=monitor;
		this.direction=direction;
	}
	
	public void move(){
		if(direction==Direction.Down)
		{
			this.setPosition(getX(), getY()-yStepSize);
		}
		else this.setPosition(getX(), getY()+yStepSize);
	}
	
	@Override
	public void run() {
		while(true){
			synchronized(monitor){
				try{
					move();
					monitor.wait();
				}
				catch (Exception e){
					
				}
			}
		}
	}	

}
