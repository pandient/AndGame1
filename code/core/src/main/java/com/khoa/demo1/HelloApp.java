
package com.khoa.demo1;

import com.badlogic.gdx.Game;


/*public class HelloApp extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
//		try {
//			new FreeTypeFontGenerator(Gdx.files.internal("test.fnt"));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		Bullet.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}*/

public class HelloApp extends Game{
    @Override
    public void create() {
        setScreen(new HelloScreen());
    }
}