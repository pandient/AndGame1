package com.khoa.demo1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Khoa Nguyen on 26/01/14.
 */

public class ResultScreen extends Screen{
    private final Screen parent;
    private Texture img;

    private OrthographicCamera guiCam;
    private Rectangle retryBounds;
    private Vector3 touchPoint;

    private int distance, candy;
    private SpriteBatch batchFont;

    public ResultScreen(Game game, Screen parent, int distance, int candy) {
        super(game);

        this.parent = parent;
        img = new Texture("data/board.png");

        guiCam = new OrthographicCamera(1024, 512);
        guiCam.position.set(1024/2, 512/2, 0);
        retryBounds = new Rectangle(280, 75, 512, 256);
        touchPoint = new Vector3();

        this.distance = distance;
        this.candy = candy;
        batchFont = new SpriteBatch();

        Assets.gameOver.play();
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (OverlapTester.pointInRectangle(retryBounds, touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.bounceSound);
                game.setScreen(new PlayScreen(game));
                Assets.backgroundMusic.play();
                return;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        guiCam.update();
        spriteBatch.setProjectionMatrix(guiCam.combined);

        parent.present(deltaTime);

        spriteBatch.begin();
        spriteBatch.draw(img, 275, 180);
        spriteBatch.end();

        batchFont.begin();
        Assets.font.setScale(1.2f);
        Assets.font.draw(batchFont, "Distance: " + distance + "m", 380, 400);
        Assets.font.draw(batchFont, "Candy: " + candy, 380, 365);
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
