package com.badlogic.gradletest;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Saad Shaharyar.
 */
public abstract class GameObject extends Rectangle {
    public final Vector2 position;
    public final Rectangle bounds;
    public final Vector2 velocity;
    public final Vector2 accel;

    public GameObject(float x, float y, float width, float height){
        this.position = new Vector2(x,y);
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }

    abstract void update(float delta);
    abstract void render();

}