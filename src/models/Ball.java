package models;

import java.awt.*;

/**
 * Ball class as model class for ball used in-game
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Ball {
    /**
     * the horizontal position of ball
     */
    private float x;

    /**
     * the vertical position of ball
     */
    private float y;

    /**
     * the horizontal speed of ball
     */
    private float speedX;

    /**
     * the vertical speed of ball
     */
    private float speedY;

    /**
     * the radius of ball because ball is circle
     */
    private float radius;

    /**
     * the real ball combination. Combination from speedX and speedY
     * with angle supported
     */
    private float speed;

    /**
     * color of the ball
     */
    private Color color;

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
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speedX = speed * (float)Math.cos(Math.toRadians(angleInDegree));
        this.speedY = -speed * (float)Math.sin(Math.toRadians(angleInDegree));
        this.color = color;
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
     * get the real speed using Phytagoras formula
     * <p>
     * using speedX and speedY to count the real ball speed with angle
     * @return float as ball speed
     */
    public float getSpeed(){
        return (float)Math.sqrt(Math.pow(speedX,2)+Math.pow(speedY,2));
    }

    /**
     * get the angle that ball currently faces
     * @return float as ball angle
     */
    public float getMoveAngle(){
        return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
    }

    // debug
    /**
     * toString method for debug some other methods
     * @return String to print on console
     */
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
     * get ball's horizontal position
     * @return ball x member
     */
    public float getX(){
        return x;
    }

    /**
     * get ball's vertical position
     * @return ball y position
     */
    public float getY(){
        return y;
    }

    /**
     * get ball's radius
     * @return ball radius
     */
    public float getRadius(){
        return radius;
    }

    /**
     * get ball's horizontal speed
     * @return ball horizontal speed
     */
    public float getSpeedX() { return speedX; }

    /**
     * get ball's vertical speed
     * @return ball vertical speed
     */
    public float getSpeedY() { return speedY; }

    // setter
    /**
     * set new value for horizontal position
     * @param x = new value for member x
     */
    public void setX(float x){ this.x = x; }

    /**
     * set new value for horizontal position
     * @param y = new value for member y
     */
    public void setY(float y){ this.y = y; }

    /**
     * update the position of the ball based on ball movement
     */
    public void updateMove() {
        this.x += speedX;
        this.y += speedY;
    }

    /**
     * stop the ball by set all speed to zero
     */
    public void setSpeedToZero(){
        speedX=0;
        speedY=0;
    }

    /**
     * set a new speed with new angle
     * @param speed as new speed
     * @param angleInDegree as new angle
     */
    public void setSpeed(float speed, float angleInDegree){
        this.speedX = speed * (float)Math.cos(Math.toRadians(angleInDegree));
        this.speedY = -speed * (float)Math.sin(Math.toRadians(angleInDegree));
    }

    /**
     * set a new vertical speed
     * @param speedY = new vertical speed
     */
    public void setSpeedY(float speedY){
        this.speedY=speedY;
    }

    /**
     * reverse horizontal speed (horizontal speed * -1)
     */
    public void reverseSpeedX(){ speedX = -speedX; }

    /**
     * reverse vertical speed (vertical speed * -1)
     */
    public void reverseSpeedY(){ speedY = -speedY; }
}
