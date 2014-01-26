package com.khoa.demo1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */
public class PlayScreen extends Screen {

    public static SpriteBatch batch;
    Texture ground;
    private TiledMap map;
    Texture background1, background2;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera guiCam;
    private SpriteBatch batchFont;
    Player player;
    int backgroundPosition = 0;
    static final int backgroundSpeed = 100;

    public PlayScreen(Game game) {
        super(game);
        gameObjects = new ArrayList<GameObject>();
        batch = new SpriteBatch();
        ground = new Texture("ground.png");

        map = new TmxMapLoader().load("data/map2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        player = new Player(120, 64,(TiledMapTileLayer) map.getLayers().get(0));
        guiCam = new OrthographicCamera(512,480);
        guiCam.position.set(480, 320, 0);
        guiCam.viewportWidth = 1024;
        guiCam.viewportHeight = 512;
        background1 = new Texture("data/parallax.png");
        background2 = new Texture("data/parallax.png");
        backgroundPosition = 0;

        guiCam.position.set(player.getX() + player.getWidth()*12, 256, 0);
        guiCam.update();

        gameObjects.add(player);
        Gdx.input.setInputProcessor(player);

       batchFont = new SpriteBatch();
    }

    @Override
    public void update(float deltaTime) {
        for(GameObject go : gameObjects){
            go.update(deltaTime);
        }
        guiCam.position.set(player.getX() + (player.getWidth() * 12), 256, 0);

        guiCam.update();

        backgroundPosition -= backgroundSpeed * deltaTime;
        if(backgroundPosition < -background1.getWidth()){
            backgroundPosition += background1.getWidth();
        }
    }

    @Override
    public void present(float deltaTime) {

        Gdx.gl.glClearColor(0.4f, 0.6f, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


        renderer.setView(guiCam);

        batch.begin();
        batch.draw(background1, 0 + backgroundPosition, 0);
        batch.draw(background1, background1.getWidth() + backgroundPosition, 0);
        batch.draw(background1, background1.getWidth() * 2 + backgroundPosition, 0);
        batch.end();

        batch.begin();
        renderer.render();
        player.render();
        batch.end();



        batchFont.begin();
        Assets.font.setScale(0.8f);
        Assets.font.draw(batchFont, player.getDistance() + "m", 10, 502);
        Assets.font.draw(batchFont, "$" + player.getCoin(), 10, 480);
        batchFont.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
    @Override
    public void resize(int width , int height){
        guiCam.viewportWidth = width / 2.5f;
        guiCam.viewportHeight = height / 2.5f;
    }
}
