package models;

import java.awt.*;

public class Ball extends MovingObject{
    private float radius;
    private Color color;
    private int belongsTo;
    private static final Color DEFAULT_COLOR = Color.WHITE;

    public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color){
        setX(x);
        setY(y);
        this.radius = radius;
        setSpeedX(speed * (float)Math.cos(Math.toRadians(angleInDegree)));
        setSpeedY(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
        this.color = color;
        this.belongsTo = -1;
    }
    public Ball(float x, float y, float radius, float speed, float angleInDegree){
        this(x,y,radius,speed,angleInDegree,DEFAULT_COLOR);
    }

    // debug
    public String toString(){
        return "Speed: " + getSpeed() + " | Angle: " + getMoveAngle() + "\n";
    }

    // getter
    public Color getColor(){
        return color;
    }
    public float getRadius(){
        return radius;
    }
    public int getBelongsTo() { return belongsTo; }

    // setter
    public void setBelongsTo(int belongsTo) { this.belongsTo = belongsTo; }
}
