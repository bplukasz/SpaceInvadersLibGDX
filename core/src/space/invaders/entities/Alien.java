package space.invaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Alien extends Image {
	
	public Alien(int x, int y){
		super(new Texture("space_invaders_by_maleiva.jpg"));
		this.setSize(25,25);
		this.setPosition(x, y);
	}

}
