package com.badlogic.gradletest;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */
public class PlayScreen extends Screen {

    public static SpriteBatch batch;
    Texture ground;

    Player player;

    public PlayScreen(Game game) {
        super(game);
        gameObjects = new ArrayList<GameObject>();
        batch = new SpriteBatch();
        ground = new Texture("ground.png");

        player = new Player(120, 16);
        gameObjects.add(player);
    }

    @Override
    public void update(float deltaTime) {
        for(GameObject go : gameObjects){
            go.update(deltaTime);
        }
    }

    @Override
    public void present(float deltaTime) {

        Gdx.gl.glClearColor(0.4f, 0.6f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(ground, 0, 0);
        player.render();
        batch.end();
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
