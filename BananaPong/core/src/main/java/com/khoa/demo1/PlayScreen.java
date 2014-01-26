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
import java.util.Random;

/**
 * Created by Khoa Nguyen on 24/01/14.
 */

public class PlayScreen extends Screen {

    public static SpriteBatch batch;
    Texture ground;
    private TiledMap map1, map2, map3;

    Texture background1, background2;
    public static OrthogonalTiledMapRenderer2 renderer, renderer2;
    private OrthographicCamera guiCam;
    private SpriteBatch batchFont;
    Player player;
    int backgroundPosition = 0;
    static final int backgroundSpeed = 100;
    static int rendererToUse = 1;
    boolean randomized = false;
    int mapCount = 1;
    Random rn = new Random();

    public PlayScreen(Game game) {
        super(game);
        gameObjects = new ArrayList<GameObject>();
        batch = new SpriteBatch();
        ground = new Texture("ground.png");

        map1 = new com.khoa.demo1.TmxMapLoader().load("data/map1.tmx", 0);
        map2 = new com.khoa.demo1.TmxMapLoader().load("data/map2.tmx", 128);
        renderer = new OrthogonalTiledMapRenderer2(map1);
        renderer2 = new OrthogonalTiledMapRenderer2(map2);
        player = new Player(120, 64,(TiledMapTileLayer) map1.getLayers().get(0));
        guiCam = new OrthographicCamera(512,480);
        guiCam.position.set(480, 320, 0);
        guiCam.viewportWidth = 1024;
        guiCam.viewportHeight = 512;
        background1 = new Texture("data/parallax.png");
        background2 = new Texture("data/parallax.png");
        backgroundPosition = 0;

        guiCam.position.set(player.getX() + player.getWidth() * 8, 256, 0);
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

        backgroundPosition -= backgroundSpeed * deltaTime;
        if(backgroundPosition < -background1.getWidth()){
            backgroundPosition += background1.getWidth();
        }

//        if(player.getX() > 32*128 && rendererToUse != 2 && player.getX() < 32 * 128 * 2){
//          rendererToUse = 2;
//            map1 = new com.khoa.demo1.TmxMapLoader().load("data/map3.tmx", 128*2);
//            renderer.setMap(map1);
//        } else if (player.getX() > 32*128 * 2 && rendererToUse != 1 && player.getX() < 32*128*3){
//            System.out.println("SECOND CALLED");
//            rendererToUse = 1;
//            map2 = new com.khoa.demo1.TmxMapLoader().load("data/map1.tmx", 128*3);
//            renderer2.setMap(map2);
//        } else if (player.getX() > 32*128*3 && rendererToUse != 2){
//            System.out.println("THIRD CALLED");
//            rendererToUse = 2;
//            map1 = new com.khoa.demo1.TmxMapLoader().load("data/map2.tmx", 128*4);
//            renderer.setMap(map1);
//        }
        if((player.getX() - 500)% (256*32) < 64*32 && !randomized){
            System.out.println("FIRST CALLED");
            int mapNumber = rn.nextInt(3);
            if(mapNumber == 0){
                map2 = new com.khoa.demo1.TmxMapLoader().load("data/map1.tmx", 128*mapCount);
            } else if (mapNumber == 1){
                map2 = new com.khoa.demo1.TmxMapLoader().load("data/map2.tmx", 128*mapCount);
            } else if (mapNumber == 2){
                map2 = new com.khoa.demo1.TmxMapLoader().load("data/map3.tmx", 128*mapCount);
            }
            renderer2.setMap(map2);
            rendererToUse = 1;
            randomized = true;
            mapCount++;
        } else if ((player.getX() - 500) % (256*32) < 128*32 && (player.getX() - 500) % (256*32) > 64*32){

            randomized = false;
        } else if ((player.getX() - 500) % (256*32) < 172*32 && !randomized){
            System.out.println("SECOND CALLED");
            int mapNumber = rn.nextInt(3);
            if(mapNumber == 0){
                map1 = new com.khoa.demo1.TmxMapLoader().load("data/map1.tmx", 128*mapCount);
            } else if (mapNumber == 1){
                map1 = new com.khoa.demo1.TmxMapLoader().load("data/map2.tmx", 128*mapCount);
            } else if (mapNumber == 2){
                map1 = new com.khoa.demo1.TmxMapLoader().load("data/map3.tmx", 128*mapCount);
            }
            renderer.setMap(map1);
            rendererToUse = 2;
            randomized = true;
            mapCount++;
        } else if ((player.getX() - 500) % (256*32) < 256*32 && (player.getX() - 500) % (256*32) > 172*32){
            randomized = false;

        }




        if (!player.getAlive())
            game.setScreen(new GameOverScreen(game, player.getDistance(), player.getCoin()));
    }

    @Override
    public void present(float deltaTime) {

        if(rendererToUse == 1){
            Gdx.gl.glClearColor(0.4f, 0.6f, 0.2f, 1);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

            guiCam.position.set(player.getX() + (player.getWidth() * 8), 256, 0);

            guiCam.update();
            renderer.setView(guiCam);
            renderer2.setView(guiCam);

            renderer.getSpriteBatch().begin();
            renderer.getSpriteBatch().draw(background1, guiCam.position.x - guiCam.viewportWidth / 2 + backgroundPosition, 0);
            renderer.getSpriteBatch().draw(background1, guiCam.position.x + background1.getWidth()-guiCam.viewportWidth/2 + backgroundPosition, 0);
            renderer.getSpriteBatch().draw(background1, guiCam.position.x + background1.getWidth() * 2 + backgroundPosition -guiCam.viewportWidth/2, 0);
            renderer.getSpriteBatch().end();

            renderer.render();
            renderer.getSpriteBatch().begin();
            renderer.renderTileLayer((TiledMapTileLayer) renderer2.getMap().getLayers().get(0), 128);
            renderer.getSpriteBatch().end();

            renderer.getSpriteBatch().begin();
            player.render(renderer.getSpriteBatch());
            renderer.getSpriteBatch().end();

            batchFont.begin();
            Assets.font.setScale(0.8f);
            Assets.font.draw(batchFont, player.getDistance() + " m", 10, 502);
            Assets.font.draw(batchFont, "@ " + player.getCoin(), 10, 480);
            batchFont.end();


        } else {
            Gdx.gl.glClearColor(0.4f, 0.6f, 0.2f, 1);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

            guiCam.position.set(player.getX() + (player.getWidth() * 8), 256, 0);

            guiCam.update();
            renderer.setView(guiCam);
            renderer2.setView(guiCam);

            renderer2.getSpriteBatch().begin();
            renderer2.getSpriteBatch().draw(background1, guiCam.position.x -guiCam.viewportWidth/2 + backgroundPosition, 0);
            renderer2.getSpriteBatch().draw(background1, guiCam.position.x + background1.getWidth()-guiCam.viewportWidth/2 + backgroundPosition, 0);
            renderer2.getSpriteBatch().draw(background1, guiCam.position.x + background1.getWidth() * 2 + backgroundPosition -guiCam.viewportWidth/2, 0);
            renderer2.getSpriteBatch().end();

            renderer2.render();
            renderer.getSpriteBatch().begin();
            renderer.renderTileLayer((TiledMapTileLayer) renderer.getMap().getLayers().get(0), 256);
            renderer.getSpriteBatch().end();

            renderer2.getSpriteBatch().begin();
            player.render(renderer2.getSpriteBatch());
            renderer2.getSpriteBatch().end();

            batchFont.begin();
            Assets.font.setScale(0.8f);
            Assets.font.draw(batchFont, player.getDistance() + " m", 10, 502);
            Assets.font.draw(batchFont, "@ " + player.getCoin(), 10, 480);
            batchFont.end();
        }
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

    public void makeNextLevel(){
        System.out.println("MAKE NEXT LEVEL CALLED");
    }
}
