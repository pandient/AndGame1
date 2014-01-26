package com.khoa.demo1;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */
public class Player extends GameObject implements InputProcessor {

    private Vector2 velocity = new Vector2();

    private float speedX = 60 * 3, speedY = 60 * 30, gravity = 60 * 50f, animationTime = 0;

    private boolean canJump = true;

    private TiledMapTileLayer mapLayer;

    private Texture image;
    private Sprite sprite;
    private String blockedKey = "blocked";
    private String coinKey = "candy";
    private String platformKey = "platform";

    private int numDistance;
    private int numCoin;

    public Player(float x, float y , TiledMapTileLayer groundLayer) {

        super();
        this.mapLayer = groundLayer;
        image = new Texture("player.png");
        sprite = new Sprite(image);
        sprite.setPosition(x,y);
        velocity.x = speedX;
        velocity.y = 0;
        numDistance = 0;
        numCoin = 0;
    }

    public void update(float delta)
    {
        Gdx.app.log("Player", delta + "");
        // apply gravity
        if(!canJump){
            velocity.y -= gravity * delta;
        }

        // clamp velocity
        if(velocity.y > speedY)
            velocity.y = speedY;
        else if(velocity.y < -speedY)
            velocity.y = -speedY;

        // save old position
        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;

        // move on x
        setX(getX() + velocity.x * delta);

        if(velocity.x > 0) // going right
            collisionX = collidesRight();

        // react to x collision
        if(collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        // move on y
        setY(getY() + velocity.y * delta);

        if(velocity.y < 0) // going down
            canJump = collisionY = collidesBottom() ;
        else if(velocity.y > 0) // going up
            collisionY = collidesTop();


        // react to y collision
        if(collisionY) {


            setY(oldY);
            collisionY = false;
            while(!collisionY){
                setY(getY() - 1);
                collisionY = collidesBottom();
            }
            setY(getY() + 1);
            velocity.y = 0;
        }

        collectCoin();
        numDistance += velocity.x * (delta/2);
    }

    @Override
    void render() {
        Gdx.app.log("", "Shouldn't call this");
    }

    public void render(SpriteBatch batch)
    {
        //PlayScreen.batch.draw(image, position.x, position.y);
        sprite.draw(batch);
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = mapLayer.getCell((int) (x / mapLayer.getTileWidth()), (int) (y / mapLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
    }

    public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += mapLayer.getTileHeight() / 2)
        {
            if(isCellBlocked(getX() + getWidth(), getY() + step))
                return true;
        }
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += mapLayer.getTileWidth() / 2)
        {
            if(isCellBlocked(getX() + step, getY() + getHeight()))
                return true;
        }
        return false;
    }

    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += mapLayer.getTileWidth() / 2)
        {
            if(isCellBlocked(getX() + step, getY()))
                return true;
            if(isCellPlatform(getX() + step, getY())){
                return true;

            }
        }
        return false;
    }

    private boolean isCellPlatform(float x, float y) {

        TiledMapTileLayer.Cell cell = mapLayer.getCell((int) (x / mapLayer.getTileWidth()), (int) (y / mapLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(platformKey);
    }

    private void isCoin(float x, float y ) {
        TiledMapTileLayer.Cell cell = mapLayer.getCell((int) (x / mapLayer.getTileWidth()), (int) (y / mapLayer.getTileHeight()));
        if( cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(coinKey)){
            mapLayer.setCell((int) (x / mapLayer.getTileWidth()), (int) (y / mapLayer.getTileHeight()), null);
            numCoin += 1;
        }
    }

    private void collectCoin() {
        for(float step = 0; step < getHeight(); step += mapLayer.getTileHeight() / 2) {
            isCoin(getX() + getWidth(), getY() + step);
        }
    }

    public int getCoin() {
        return numCoin;
    }

    public int getDistance() {
        return numDistance;
    }

    public float getHeight() {
        return this.sprite.getHeight();
    }

    public float getWidth() {
        return this.sprite.getWidth();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public TiledMapTileLayer getMapLayer() {
        return mapLayer;
    }

    public void setMapLayer(TiledMapTileLayer mapLayer) {
        this.mapLayer = mapLayer;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.W:
                if(canJump) {
                    velocity.y = speedY / 1.8f;
                    canJump = false;
                }
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.A:
            case Input.Keys.D:

        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(canJump) {
            velocity.y = speedY / 1.8f;
            canJump = false;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public float getX() {
        return this.sprite.getX();
    }

    public float getY() {
        return this.sprite.getY();
    }

    public void setX(float x) {
        this.sprite.setX(x);
    }

    public void setY(float y) {
        this.sprite.setY(y);
    }
}
