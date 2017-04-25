package models;

import java.awt.*;
import java.util.Date;

public class Paddle {
    private float x;
    private float y;
    private float speedY;
    private float length;
    private float deltaLength;
    private float width;
    private Color color;
    private long startTime;
    private long elapsedTime;
    private long endElapsedTime;
    private long durationPU;
    private float defaultLength;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    public Paddle(float x, float y, float length, Color color){
        this.x = x;
        this.y = y;
        this.speedY = 0;
        this.defaultLength = length;
        this.length = length;
        this.width = 10;
        this.color = color;
        this.durationPU = 4*100000;
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

    public void setDefaultLength(){
        this.length = defaultLength;
    }

    public long getDurationPU(){return durationPU;}

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
    public float getSpeedY() { return speedY; }

    // setter
    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }
    public void changeLength() {
        long inc = elapsedTime/durationPU;
        this.length = defaultLength + (inc>0?inc:0)*deltaLength;
    }
    public void updateMove(){
        y+=speedY;
    }
    public void setY(int y){ this.y = y; }
}
