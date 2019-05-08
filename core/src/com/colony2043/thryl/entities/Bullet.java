package com.colony2043.thryl.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Bullet {
    String path = "C:\\Users\\minicodcraft\\Downloads\\game\\core\\assets\\";
    public static final int SPEED = 2000;
    private static Texture texture;
    float x, y;
    public boolean remove = false;

    public Bullet (float x, float y) {
        this.x = x;
        this.y = y;

        if (texture == null)
            texture = new Texture(path + "Bullet.png");
    }


    public void update (float deltaTime) {
        y +=(SPEED * deltaTime);
        x +=(SPEED * deltaTime);
        if (y > Gdx.graphics.getHeight()|| y < (-1 * Gdx.graphics.getHeight()))
            remove = true;
        else if (x > Gdx.graphics.getWidth() || x < (-1 * Gdx.graphics.getWidth())){
            remove = true;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }
}

