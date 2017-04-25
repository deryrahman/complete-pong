package models;

import java.awt.*;
import java.util.Date;

public class Paddle extends MovingObject {
    private float length;
    private float deltaLength;
    private float width;
    private Color color;
    private long startTime;
    private long elapsedTime;
    private long durationPU;
    private float defaultLength;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    public Paddle(float x, float y, float length, Color color){
        setX(x);
        setY(y);
        setSpeedY(0);
        setSpeedX(0);
        this.defaultLength = length;
        this.length = length;
        this.width = 10;
        this.color = color;
        this.durationPU = 32*100000;
        this.deltaLength = 50;
    }
    public Paddle(float x, float y, float length){
        this(x,y,length,DEFAULT_COLOR);
    }

    public void startTimer(){
        startTime = System.currentTimeMillis();
        if(elapsedTime<0)
            elapsedTime=durationPU-1;
        elapsedTime +=durationPU;
    }

    public long getElapsedTime(){
        elapsedTime -= (new Date()).getTime() - startTime;
        return elapsedTime;
    }

    // getter
    public Color getColor(){
        return color;
    }
    public float getLength(){
        return length;
    }
    public float getWidth(){
        return width;
    }

    // setter
    public void changeLength() {
        long inc = elapsedTime/durationPU;
        this.length = defaultLength + (inc>0?inc:0)*deltaLength;
    }
}
