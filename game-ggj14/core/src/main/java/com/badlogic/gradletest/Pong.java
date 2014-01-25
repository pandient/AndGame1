package com.badlogic.gradletest;

/**
 * Created by Saad Shaharyar.
 */

public class Pong extends Game{
    boolean firstTimeCreate = true;

    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    @Override
    public void create() {
        Assets.load();
        super.create();
    }


}
