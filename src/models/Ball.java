package models;

import java.awt.*;

public class Ball {
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private float radius;
    private Color color;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speedX = speed * (float)Math.cos(Math.toRadians(angleInDegree));
        this.speedY = -speed * (float)Math.sin(Math.toRadians(angleInDegree));
        this.color = color;
    }
    public Ball(float x, float y, float radius, float speed, float angleInDegree){
        this(x,y,radius,speed,angleInDegree,DEFAULT_COLOR);
    }
    public void move(Board board, Paddle[] paddle){
        float ballMinX = board.getMinX() + radius;
        float ballMinY = board.getMinY() + radius;
        float ballMaxX = board.getMaxX() - radius;
        float ballMaxY = board.getMaxY() - radius;

        x+=speedX;
        y+=speedY;

        if(x<ballMinX){
            speedX = -speedX;
            x = ballMinX;
        } else if (x > ballMaxX) {
            speedX = -speedX;
            x = ballMaxX;
        }

        if (y < ballMinY) {
            speedY = -speedY;
            y = ballMinY;
        } else if (y > ballMaxY) {
            speedY = -speedY;
            y = ballMaxY;
        }

    }
    public float getSpeed(){
        return (float)Math.sqrt(Math.pow(speedX,2)*Math.pow(speedY,2));
    }
    public float getMoveAngle(){
        return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
    }

    // debug
    public String toString(){
        return "Speed: " + getSpeed() + " | Angle: " + getMoveAngle() + "\n";
    }

    // getter
    public Color getColor(){
        return color;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getRadius(){
        return radius;
    }
}
