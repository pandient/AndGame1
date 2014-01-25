package com.khoa.demo1;

/**
 * Created by Saad Shaharyar.
 */
public abstract class GameObject {



    public GameObject(){

    }

    abstract void update(float delta);
    abstract void render();

}