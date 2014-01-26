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

    private String distanceStr, candyStr;
    private SpriteBatch batchFont;

//    private float screenWidth;
//    private float screenHeight;

    public ResultScreen(Game game, Screen parent, int distance, int candy) {
        super(game);

        this.parent = parent;
        img = new Texture("data/board.png");

        guiCam = new OrthographicCamera(1024, 512);
        guiCam.position.set(1024/2, 512/2, 0);
        retryBounds = new Rectangle(280, 75, 512, 256);
        touchPoint = new Vector3();

        distanceStr = "Distance: " + distance;
        candyStr = "Candy: " + candy;
        batchFont = new SpriteBatch();

        Assets.gameOver.play();
        System.out.println("RESULT SCREEN");
//        screenWidth = Gdx.graphics.getWidth();
//        screenHeight = Gdx.graphics.getHeight();
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
        spriteBatch.draw(img, screenWidth/2 - img.getWidth()/2, screenHeight/2 - img.getHeight()/2);
        spriteBatch.end();

        batchFont.begin();
        Assets.font.setScale(1.2f);
        Assets.font.draw(batchFont, distanceStr, screenWidth/2 - Assets.font.getBounds(distanceStr).width/2, screenHeight/2 + 80);
        Assets.font.draw(batchFont, candyStr, screenWidth/2 - Assets.font.getBounds(candyStr).width/2, screenHeight/2 + 45);
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
