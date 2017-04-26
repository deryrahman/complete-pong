package models;

import java.awt.*;
import java.util.Date;

/**
 * Paddle Class as model for paddle used in-game
 * Every player has paddle as an object to be played
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Paddle extends MovingObject {

    /**
     * define the paddle's length
     */
    private float length;

    /**
     * additional length for paddle
     */
    private float deltaLength;

    /**
     * define the paddle's width
     */
    private float width;

    /**
     * define the paddle's color
     */
    private Color color;

    /**
     * the time paddle starts to grow (used additional length)
     */
    private long startTime;

    /**
     * paddle back into normal
     */
    private long elapsedTime;

    /**
     * how long the paddle grows
     */
    private long durationPU;

    /**
     * default length of every paddle
     */
    private float defaultLength;

    /**
     * define the default color for paddle
     */
    private static final Color DEFAULT_COLOR = Color.WHITE;

    /**
     * Paddle constructor, initialize all paddle's members
     * @param x = horizontal pos
     * @param y = vertical pos
     * @param length = vertical size
     * @param color = paddle's color
     */
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

    /**
     * Paddle constructor with default color
     * @param x = horizontal pos
     * @param y = vertical pos
     * @param length = vertical size
     */
    public Paddle(float x, float y, float length){
        this(x,y,length,DEFAULT_COLOR);
    }

    /**
     * method to start the timer when paddle get PU
     */
    public void startTimer(){
        startTime = System.currentTimeMillis();
        if(elapsedTime<0)
            elapsedTime=durationPU-1;
        elapsedTime +=durationPU;
    }

    /**
     * method to get time when paddle ended using PU
     * @return long as time
     */
    public long getElapsedTime(){
        elapsedTime -= (new Date()).getTime() - startTime;
        return elapsedTime;
    }

    // getter

    /**
     * /**
     * get paddle's color
     * @return paddle's color
     */
    public Color getColor(){
        return color;
    }

    /**
     * get vertical size of paddle
     * @return paddle's length
     */
    public float getLength(){
        return length;
    }

    /**
     * get horizontal size of paddle
     * @return paddle's width
     */
    public float getWidth(){
        return width;
    }

    // setter

    /**
     * set a new length for paddle
     */
    public void changeLength() {
        long inc = elapsedTime/durationPU;
        this.length = defaultLength + (inc>0?inc:0)*deltaLength;
    }
}
