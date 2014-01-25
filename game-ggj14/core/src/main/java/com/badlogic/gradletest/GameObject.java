package com.badlogic.gradletest;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.security.PublicKey;

/**
 * Created by Saad Shaharyar.
 */
public abstract class GameObject {



    public GameObject(){

    }

    abstract void update(float delta);
    abstract void render();

}