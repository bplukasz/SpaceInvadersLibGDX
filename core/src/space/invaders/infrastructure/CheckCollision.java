package space.invaders.infrastructure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class CheckCollision extends Image {

	public CheckCollision(float x, float y, Texture texture, int textureWidth, int textureHeigth){
		super(texture);
		this.setSize(textureWidth, textureHeigth);
		this.setPosition(x, y);
	}
	public boolean overlaps(CheckCollision object){
		if(checkYCollision(object))
			if(checkXCollision(object))
				return true;
		return false;
	}
	
	private boolean checkXCollision(CheckCollision object) {
		return (this.getX()>object.getX() && this.getX()<object.getX()+object.getImageWidth())
				||(this.getX()+this.getImageWidth()>object.getX() 
						&& this.getX()+this.getImageWidth()<object.getX()+object.getImageWidth());
	}
	private boolean checkYCollision(CheckCollision object){
		return (this.getY()>object.getY() && this.getY()<object.getY()+object.getImageHeight())
				||(this.getY()+this.getImageHeight()>object.getY() 
						&& this.getY()+this.getImageHeight()<object.getY()+object.getImageHeight());
	}
}
