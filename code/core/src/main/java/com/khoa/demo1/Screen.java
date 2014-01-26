package com.khoa.demo1;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Saad Shaharyar.
 */

public abstract class Screen {
    Game game;
    ArrayList<GameObject> gameObjects;

    public SpriteBatch spriteBatch;

    public float screenWidth;
    public float screenHeight;

    public Screen(Game game){
        this.game = game;

        spriteBatch = new SpriteBatch();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    protected void setScreen(Screen screen) {
        game.setScreen(screen);
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void resize(int x, int y);
}