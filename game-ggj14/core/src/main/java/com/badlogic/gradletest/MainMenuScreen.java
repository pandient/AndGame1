package com.badlogic.gradletest;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
/**
 * Created by Saad Shaharyar.
 */
public class MainMenuScreen extends Screen {
    OrthographicCamera guiCam;
    SpriteBatch batcher;
    Rectangle newgameBounds;
    Rectangle aboutBounds;
    Rectangle helpBounds;
    Vector3 touchPoint;

    public MainMenuScreen(Game game){
        super(game);
        guiCam = new OrthographicCamera(480,320);
        guiCam.position.set(480 / 2, 320 / 2, 0);
        batcher = new SpriteBatch();
        newgameBounds = new Rectangle(157, 125, 170, 34);
        helpBounds = new Rectangle(157, 75, 168, 36);
        aboutBounds = new Rectangle(157, 23, 161, 39);
        touchPoint = new Vector3();

    }

    @Override
    public void update(float deltaTime) {
        guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(OverlapTester.pointInRectangle(newgameBounds, touchPoint.x, touchPoint.y)){
                Assets.playSound(Assets.bounceSound);
                game.setScreen(new PlayScreen(game));
                return;
            }
//            if (OverlapTester.pointInRectangle(helpBounds, touchPoint.x, touchPoint.y)){
//                Assets.playSound(Assets.bounceSound);
//                game.setScreen(new Help1Screen(game));
//                return;
//            }
        if (OverlapTester.pointInRectangle(aboutBounds, touchPoint.x, touchPoint.y)){
            Assets.playSound(Assets.bounceSound);
            game.setScreen(new AboutScreen(game));
            return;
        }
    }

    @Override
    public void present(float deltaTime) {
        GLCommon gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        batcher.setProjectionMatrix(guiCam.combined);

        batcher.disableBlending();
        batcher.begin();
        batcher.draw(Assets.mainMenuScreenRegion, 0, 0, 480, 320);
        batcher.end();
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

}
