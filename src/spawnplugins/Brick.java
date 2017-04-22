package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */
public class Brick{
    private float x;
    private float y;
    private float length;
    private float width;
    private Color color;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    public Brick() {
        Random rand = new Random();
        int nx = rand.nextInt(10);
        int ny = rand.nextInt(10);
        x = 400+20*nx;
        y = 40*ny;
        this.length = 40;
        this.width = 20;
        this.color = color;
    }

    // getter
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public Color getColor(){
        return color;
    }
    public float getLength(){
        return length;
    }
    public float getWidth(){
        return width;
    }

    public String toString() {
        return "Brick";
    }
//
//    @Override
//    public void changeBSpeed(Ball b, float newspeed) {
//        newspeed = 0;
//        b.increaseSpeed(newspeed);
//    }
//
//    @Override
//    public void changeLPaddle(Paddle p, float newpaddle) {
//        newpaddle = p.getLength();
//        p.changeLength(newpaddle);
//    }
}
