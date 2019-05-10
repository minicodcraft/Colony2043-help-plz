package com.colony2043.thryl.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.colony2043.thryl.screens.playScreen;


public class Bullet {
    String path = "C:\\Users\\minicodcraft\\Downloads\\game\\core\\assets\\";
    private static final int SPEED = 10;
    private static Texture texture;
    float x, y;
    float sx, sy;
    double yPos = Gdx.input.getY(); // the y i want to go to
    double xPos = Gdx.input.getX(); // the x i want to go to

    public Bullet(float x, float y, double startX, double startY) {
        this.x = x;
        this.y = y;

        double startX1 = xPos - startX;
        double startY1 = yPos - startY;
        double angle1 = Math.atan(startY1 / startX1);
        double angle2 = Math.atan(startX1 / startY1);
        sx = (float)(SPEED * (Math.sin(angle2)));
        sy = (float)(SPEED * (Math.sin(angle1)));
        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (texture == null) {
            texture = new Texture(path + "Bullet.png");
        }
    }

    public  void update (float deltaTime){
        if(x > 0 && y > 0) {
            x += sx;
            y += sy;
        }
        else if(x < 0 && y > 0){
            x -= sx;
            y += sy;
        }
        else if(x < 0 && y < 0){
            x -= sx;
            y -= sy;
        }
        else if(x > 0 && y < 0){
            x += sx;
            y -= sy;
        }
    }

    public void render (SpriteBatch batch){
            batch.draw(texture, x, y);
    }
}


