package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {
	
	public Player() {
		super(new Texture("si_ship.png"));
		this.setSize(50,50);
		this.setPosition(425, 50);
		
	}

}
