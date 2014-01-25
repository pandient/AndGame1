package com.badlogic.gradletest;

import com.badlogic.gdx.Game;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */
public class TheGame extends Game {
    @Override
    public void create() {
        setScreen(new PlayScreen());
    }

}
