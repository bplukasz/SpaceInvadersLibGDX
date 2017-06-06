package space.invaders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import space.invaders.SpaceInvadersGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Space Invaders";
		config.width=SpaceInvadersGame.WIDTH;
		config.height=SpaceInvadersGame.HEIGHT;
		config.resizable=false;
		
		new LwjglApplication(new SpaceInvadersGame(), config);
	}
}
