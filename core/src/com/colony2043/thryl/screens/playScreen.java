package com.colony2043.thryl.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.colony2043.thryl.GameMain;
import com.colony2043.thryl.entities.Bullet;

import java.util.ArrayList;

public class playScreen implements Screen {
    private GameMain game;
    private OrthographicCamera cameraMain;
    private Viewport viewport;
    
    private float x, y;

    float shootTimer = 0;
    float rangetimer = 0;
    float rangeWait = 2f;
    float shootWait = .3f;

    ShapeRenderer renderer;

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();

    public Sprite
            playSpriteSD, playSpriteD,
            playSpriteDW, playSpriteW,
            playSpriteAW, playSpriteA,
            playSpriteAS, playSpriteS;

    public Sprite playSprite;

    String path = "C:\\Users\\minicodcraft\\Downloads\\game\\core\\assets";
    private Sprite HUD;
    private  Sprite background;

    public playScreen(GameMain game) {
        rangetimer = 0;
        this.game = game;
        game.batch = new SpriteBatch();

        playerTex();

        HUD = new Sprite(new Texture(Gdx.files.internal(path + "\\HUD_BAR.png")));
        HUD.setPosition(0, 0);
        HUD.setSize(1920, 201);


        background = new Sprite(new Texture(Gdx.files.internal(path + "\\badlogic.jpg")));
        background.setPosition(0, 201);
        background.setSize(1920, 879);

        cameraMain = new OrthographicCamera();
        viewport = new ExtendViewport(1920, 1080, cameraMain);
        viewport.apply();

        cameraMain.position.set(cameraMain.viewportWidth/2, cameraMain.viewportHeight/2, 0);
    }

    public void playerTex(){
        playSpriteSD = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\1SD.png")));
         playSpriteD = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\2D.png")));
        playSpriteDW = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\3DW.png")));
         playSpriteW = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\4W.png")));
        playSpriteAW = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\5AW.png")));
         playSpriteA = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\6A.png")));
        playSpriteAS = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\7AS.png")));
         playSpriteS = new Sprite(new Texture(Gdx.files.internal(path + "\\8d\\8S.png")));

        playSpriteSD.setSize(140, 140);
         playSpriteD.setSize(140, 140);
        playSpriteDW.setSize(140, 140);
         playSpriteW.setSize(140, 140);
        playSpriteAW.setSize(140, 140);
         playSpriteA.setSize(140, 140);
        playSpriteAS.setSize(140, 140);
         playSpriteS.setSize(140, 140);

        playSprite = playSpriteS;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        playSprite.setPosition((w / 2) - (playSprite.getWidth() / 2), (h / 2) - playSprite.getHeight()/2);
        playSprite.setSize(140, 140);
    }

    public void render(float delta) {
         // HUD
        HUD.setPosition(playSprite.getX() - 860, playSprite.getY() - 540);
         // cam
        cameraMain.position.set(100 + playSprite.getX(), playSprite.getY(), 0);
        cameraMain.update();


        // shooting
        shootTimer += delta;
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && shootTimer >= shootWait){
            double startX = playSprite.getX();
            double startY = playSprite.getY();
            shootTimer = 0;
            bullets.add(new Bullet(70 + playSprite.getX(), 70 + playSprite.getY(), startX, startY));
        }
        // update bullets
        for(Bullet bullet : bullets){
            bullet.update(delta);
            if(rangetimer >= rangeWait){
                bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll((bulletsToRemove));

        update(delta);

        cameraMain.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cameraMain.combined);
        game.batch.begin();
        background.draw(game.batch);
        playSprite.draw(game.batch);
        HUD.draw(game.batch);
        game.batch.end();

        game.batch.begin();
        for(Bullet bullet : bullets){
            bullet.render(game.batch);
        }
        game.batch.end();
    }

    private void update(float delta) {
        playerMove();
    }

    private void playerMove(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteA;
                playSprite.setPosition(x, y);
                playSprite.translateX(-0.5f);
            }
            else {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteA;
                playSprite.setPosition(x, y);
                playSprite.translateX(-5.0f);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteD;
                playSprite.setPosition(x, y);
                playSprite.translateX(0.5f);
            }
            else {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteD;
                playSprite.setPosition(x, y);
                playSprite.translateX(5.0f);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteS;
                playSprite.setPosition(x, y);
                playSprite.translateY(-0.5f);
            }
            else {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteS;
                playSprite.setPosition(x, y);
                playSprite.translateY(-5.0f);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteW;
                playSprite.setPosition(x, y);
                playSprite.translateY(0.5f);
            }
            else {
                x = playSprite.getX();
                y = playSprite.getY();
                playSprite = playSpriteW;
                playSprite.setPosition(x, y);
                playSprite.translateY(5.0f);
            }
        }
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
        HUD.getTexture().dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cameraMain.position.set(cameraMain.viewportWidth/2,cameraMain.viewportHeight/2,0);
    }
}
