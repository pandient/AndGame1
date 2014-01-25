package com.badlogic.gradletest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */
public class Player extends GameObject {

    public static final float WIDTH = 32;
    public static final float HEIGHT = 64;
    public boolean isJumping = false;

    private Texture image;

    public Player(float x, float y) {
        super(x, y, WIDTH, HEIGHT);

        image = new Texture("player.png");
    }

    public void update(float delta)
    {
        if(position.y > 16){
            velocity.add(0, -1700f*delta);
        } else {
            isJumping = false;
            velocity.set(0, 0);
            position.y = 16;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !isJumping){
            isJumping = true;
            velocity.set(0, 1000);
        }
        position.add(velocity.x * delta, velocity.y * delta);
        bounds.setPosition(position);
    }

    public void render()
    {
        PlayScreen.batch.draw(image, position.x, position.y);
    }
}
