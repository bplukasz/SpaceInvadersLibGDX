package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bullet extends Image implements Runnable {
	private int textureWidth=3;
	private int textureHeigth=6;
	private int yStepSize=4;
	private Direction direction;
	private Object monitor;
	
	public Bullet(float x, float y, Direction direction, Object monitor){
		super(new Texture("bullet.jpg"));
		this.monitor=monitor;
		this.direction=direction;
		this.setSize(textureWidth,textureHeigth);
		this.setPosition(x, y);
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
