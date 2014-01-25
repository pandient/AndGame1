package com.badlogic.gradletest;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by joseph on 24/01/14.
 */
public class Box extends GameObject {

    public static final float WIDTH = 32;
    public static final float HEIGHT = 32;

    private Texture image;

    public Box(float x, float y) {
        super(x, y, WIDTH, HEIGHT);

        image = new Texture("badlogic.jpg");
    }

    @Override
    void update(float delta) {
        position.add(-180*delta, 0);
        bounds.setPosition(position);
    }

    @Override
    void render() {
        PlayScreen.batch.draw(image, position.x, position.y, WIDTH, HEIGHT);
    }
}
