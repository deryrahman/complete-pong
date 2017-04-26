package models;

import java.awt.*;

/**
 * Ball class as model class for ball used in-game
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Ball extends MovingObject{
    /**
     * the radius of ball because ball is circle
     */
    private float radius;

    /**
     * color of the ball
     */
    private Color color;

    /**
     * define the last player hit by the ball
     */
    private int belongsTo;

    /**
     * define default ball color
     */
    private static final Color DEFAULT_COLOR = Color.WHITE;

    /**
     * Ball Class Constructor, initialize all members of ball
     * @param x as horizontal position
     * @param y as vertical position
     * @param radius as ball radius
     * @param speed as combined speed
     * @param angleInDegree as angle of speed in degrees
     * @param color as color of the ball
     */
    public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color){
        setX(x);
        setY(y);
        this.radius = radius;
        setSpeedX(speed * (float)Math.cos(Math.toRadians(angleInDegree)));
        setSpeedY(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
        this.color = color;
        this.belongsTo = -1;
    }

    /**
     * Ball Class Constructor, initialize all members of ball with default color
     * @param x as horizontal position
     * @param y as vertical position
     * @param radius as ball radius
     * @param speed as combined speed
     * @param angleInDegree as angle of speed in degrees
     */
    public Ball(float x, float y, float radius, float speed, float angleInDegree){
        this(x,y,radius,speed,angleInDegree,DEFAULT_COLOR);
    }

    /**
     * toString method for debug some other methods
     * @return String to print on console
     */
    // debug
    public String toString(){
        return "Speed: " + getSpeed() + " | Angle: " + getMoveAngle() + "\n";
    }

    // getter
    /**
     * get ball's color
     * @return ball color member
     */
    public Color getColor(){
        return color;
    }

    /**
     * get ball's radius
     * @return ball radius
     */
    public float getRadius(){
        return radius;
    }

    /**
     * get the player number that owned the ball
     * @return int as player number
     */
    public int getBelongsTo() { return belongsTo; }

    // setter

    /**
     * set a new owner of the ball. Used when ball hit the player's paddle
     * @param belongsTo new owner
     */
    public void setBelongsTo(int belongsTo) { this.belongsTo = belongsTo; }
}
