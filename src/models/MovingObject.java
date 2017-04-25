package models;

/**
 * Created by dery on 4/26/17.
 */
public abstract class MovingObject {
    private float x;
    private float y;
    private float speedX;
    private float speedY;

    // getter
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getSpeedX() { return speedX; }
    public float getSpeedY() { return speedY; }

    // setter
    public void setX(float x){ this.x = x; }
    public void setY(float y){ this.y = y; }
    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }
    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    public void updateMove() {
        this.x += speedX;
        this.y += speedY;
    }

    public void setSpeedToZero(){
        speedX=0;
        speedY=0;
    }
    public void setSpeed(float speed, float angleInDegree){
        this.speedX = speed * (float)Math.cos(Math.toRadians(angleInDegree));
        this.speedY = -speed * (float)Math.sin(Math.toRadians(angleInDegree));
    }
    public void reverseSpeedX(){ speedX = -speedX; }
    public void reverseSpeedY(){ speedY = -speedY; }

    public float getSpeed(){
        return (float)Math.sqrt(Math.pow(getSpeedX(),2)+Math.pow(getSpeedY(),2));
    }
    public float getMoveAngle(){
        return (float)Math.toDegrees(Math.atan2(-getSpeedY(), getSpeedX()));
    }
}
