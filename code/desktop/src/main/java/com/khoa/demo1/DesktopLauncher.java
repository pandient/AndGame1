
package com.khoa.demo1;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Candy Panda 2";
        config.width = 1024;
        config.height = 512;
		new LwjglApplication(new Pong(), config);
	}
}
