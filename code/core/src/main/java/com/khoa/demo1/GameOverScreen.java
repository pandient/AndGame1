package com.khoa.demo1;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Khoa Nguyen on 25/01/14.
 */
public class GameOverScreen extends Screen {
    OrthographicCamera guiCam;
    SpriteBatch batcher;
    Rectangle retryBounds;
    Vector3 touchPoint;

    private int distance, candy;
    private SpriteBatch batchFont;

    public GameOverScreen(Game game, int distance, int candy) {
        super(game);

        guiCam = new OrthographicCamera(480,320);
        guiCam.position.set(480 / 2, 320 / 2, 0);
        batcher = new SpriteBatch();

        retryBounds = new Rectangle(0, 0, 1024, 512);
        touchPoint = new Vector3();

        this.distance = distance;
        this.candy = candy;
        batchFont = new SpriteBatch();




    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.justTouched()){
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (OverlapTester.pointInRectangle(retryBounds, touchPoint.x, touchPoint.y)){

                game.setScreen(new PlayScreen(game));

                Assets.backgroundMusic.play();
                return;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GLCommon gl = Gdx.gl;
        gl.glClearColor(1,0,0,1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        batcher.setProjectionMatrix(guiCam.combined);

        batcher.disableBlending();
        batcher.begin();
        batcher.draw(Assets.gameoverscreenRegion, 0, 0, 480, 320);
        batcher.end();

        batchFont.begin();
        Assets.font.setScale(1.5f);
        Assets.font.draw(batchFont, "Distance: " + distance + "m", 320, 420);
        Assets.font.draw(batchFont, "Candy: " + candy, 320, 380);
        batchFont.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int x, int y) {

    }
}
