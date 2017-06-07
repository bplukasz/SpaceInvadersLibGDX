package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {
	private int stepSize = 5;
	public Player() {
		super(new Texture("si_ship.png"));
		this.setSize(50,50);
		this.setPosition(425, 50);
	}
	
	public void move(Direction left){
		if(left == Direction.Right)
			setX(getX()+stepSize);
		else if(left == Direction.Left)
			setX(getX()-stepSize);
	}

}
