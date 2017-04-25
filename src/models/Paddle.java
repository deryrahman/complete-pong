package models;

import java.awt.*;

/**
 * Paddle class as model for paddle used in-game
 * paddle is used by every player including bot
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Paddle {
    /**
     * paddle's horizontal position
     */
    private float x;

    /**
     * paddle's vertical position
     */
    private float y;

    /**
     * paddle's speed. Only vertical
     */
    private float speedY;

    /**
     * paddle's length (vertical)
     */
    private float length;

    /**
     * paddle's width (horizontal)
     */
    private float width;

    /**
     * paddle's color
     */
    private Color color;

    /**
     * define the default color for paddle (WHITE)
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
        this.x = x;
        this.y = y;
        this.speedY = 0;
        this.length = length;
        this.width = 10;
        this.color = color;
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

    // getter
    /**
     * get horizontal position
     * @return paddle's x
     */
    public float getX(){
        return x;
    }

    /**
     * get vertical position
     * @return paddle's y
     */
    public float getY(){
        return y;
    }

    /**
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

    /**
     * get paddle's speed, only vertical
     * @return paddle's speedY
     */
    public float getSpeedY() { return speedY; }

    // setter
    /**
     * set a new speed for paddle
     * @param speedY = new speed
     */
    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    /**
     * set a new length for paddle
     * @param length = new length
     */
    public void changeLength(float length) {
        this.length = length;
    }

    /**
     * update vertical position based on paddle vertical movement
     */
    public void updateMove(){
        y+=speedY;
    }

    /**
     * set new vertical position
     * @param y = new vertical position
     */
    public void setY(int y){ this.y = y; }
}
