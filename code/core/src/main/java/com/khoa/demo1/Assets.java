package com.khoa.demo1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Saad Shaharyar.
 */
public class Assets {
    public static Texture textureatlas;
    public static TextureRegion mainMenuScreenRegion;
    public static TextureRegion gameoverscreenRegion;
    public static TextureRegion aboutScreenRegion;
    public static BitmapFont font;


    public static Sound gameOver;
    public static Sound jump;
    public static Sound obstacleHit;
    public static Sound pickUp;

    public static Music backgroundMusic;


    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load(){
        textureatlas = loadTexture("data/screen_atlas.png");
        mainMenuScreenRegion = new TextureRegion(textureatlas, 0, 512, 1024, 512);
        gameoverscreenRegion = new TextureRegion(textureatlas, 0, 0, 1024, 512);
        aboutScreenRegion = new TextureRegion(textureatlas, 0, 640, 480, 320);

        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);


        gameOver = Gdx.audio.newSound(Gdx.files.internal("soundeffect/gameover.mp3"));

        jump = Gdx.audio.newSound(Gdx.files.internal("soundeffect/jump.mp3"));
        pickUp = Gdx.audio.newSound(Gdx.files.internal("soundeffect/pickup.mp3"));
        obstacleHit = Gdx.audio.newSound(Gdx.files.internal("soundeffect/obstaclehit.mp3"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundmusic/POL-sand-and-water-short16.wav"));
        backgroundMusic.setVolume(.2f);
        backgroundMusic.setLooping(true);



    }

    public static void playBackgroundMusic(){}
    {
        backgroundMusic.play();
    }
    public static void stopBackgroundMusic(){}
    {
        backgroundMusic.pause();
    }

    public static void playSound(Sound sound){
        sound.play(1);
    }

}