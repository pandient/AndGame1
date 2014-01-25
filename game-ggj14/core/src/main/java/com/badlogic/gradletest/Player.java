package com.badlogic.gradletest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */
public class Player extends GameObject {

    public static final float WIDTH = 32;
    public static final float HEIGHT = 64;
    public float velocity = 5;

    private Texture image;

    public Player(float x, float y) {
        super(x, y, WIDTH, HEIGHT);

        image = new Texture("player.png");
    }

    public void update(float delta)
    {
        position.add(velocity * delta, 0);
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(image, position.x, position.y);
    }
}
