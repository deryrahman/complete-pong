package models;

/**
 * MovingObject Class as abstract class
 * Handle all members and methods needed by
 * all objects in package centerboard
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public abstract class MovingObject {
    /**
     * define horizontal position
     */
    private float x;

    /**
     * define vertical position
     */
    private float y;

    /**
     * define horizontal speed
     */
    private float speedX;

    /**
     * define vertical speed
     */
    private float speedY;

    // getter

    /**
     * get horizontal position
     * @return horizontal position
     */
    public float getX(){
        return x;
    }

    /**
     * get vertical position
     * @return vertical position
     */
    public float getY(){
        return y;
    }

    /**
     * get horizontal speed
     * @return horizontal speed
     */
    public float getSpeedX() { return speedX; }

    /**
     * get vertical speed
     * @return vertical speed
     */
    public float getSpeedY() { return speedY; }

    // setter

    /**
     * set new horizontal pos
     * @param x = new horizontal pos
     */
    public void setX(float x){ this.x = x; }

    /**
     * set new vertical pos
     * @param y = new vertical pos
     */
    public void setY(float y){ this.y = y; }

    /**
     * set new horizontal speed
     * @param speedX = horizontal speed
     */
    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }

    /**
     * set new vertical speed
     * @param speedY = vertical speed
     */
    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    /**
     * update position based on movement
     */
    public void updateMove() {
        this.x += speedX;
        this.y += speedY;
    }

    /**
     * stop the objects, set all speed to zero
     */
    public void setSpeedToZero(){
        speedX=0;
        speedY=0;
    }

    /**
     * set new speed based on angle
     * @param speed = new speed
     * @param angleInDegree = object angle
     */
    public void setSpeed(float speed, float angleInDegree){
        this.speedX = speed * (float)Math.cos(Math.toRadians(angleInDegree));
        this.speedY = -speed * (float)Math.sin(Math.toRadians(angleInDegree));
    }

    /**
     * reverse horizontal speed
     */
    public void reverseSpeedX(){ speedX = -speedX; }

    /**
     * reverse vertical speed
     */
    public void reverseSpeedY(){ speedY = -speedY; }

    /**
     * get real speed based on phytagoras formula
     * @return real speed
     */
    public float getSpeed(){
        return (float)Math.sqrt(Math.pow(getSpeedX(),2)+Math.pow(getSpeedY(),2));
    }

    /**
     * get object's angle
     * @return object's angle
     */
    public float getMoveAngle(){
        return (float)Math.toDegrees(Math.atan2(-getSpeedY(), getSpeedX()));
    }
}
