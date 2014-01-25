package com.badlogic.gradletest;


import java.util.ArrayList;

/**
 * Created by Saad Shaharyar.
 */
public abstract class Screen {
    Game game;
    ArrayList<GameObject> gameObjects;


    public Screen(Game game){
        this.game = game;

    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}