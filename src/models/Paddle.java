package models;

import java.awt.*;

public class Paddle {
    private float x;
    private float y;
    private float length;
    private float width;
    private Color color;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    public Paddle(float x, float y, float length, Color color){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = 10;
        this.color = color;
    }
    public Paddle(float x, float y, float length){
        this(x,y,length,DEFAULT_COLOR);
    }

    public void move(){

    }
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
}
