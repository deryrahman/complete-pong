package models;


import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * JUnit for unit test. Test Ball Class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class BallTest {
    /**
     * initialize ball
     */
    Ball ball= new Ball(2,3,1, 5, 200, Color.BLACK);
    @Test
    public void constructorTest() {
        assertTrue(ball.getX()==2);
        assertTrue(ball.getY()==3);
        assertTrue(ball.getRadius()==1);
        assertTrue(ball.getSpeedX()== 5*(float)Math.cos(Math.toRadians(200)));
        assertTrue(ball.getSpeedY()== -5* (float)Math.sin(Math.toRadians(200)));
        assertTrue(ball.getColor()==Color.black);
    }

    /**
     * method test
     */
    @Test
    public void setterTest() {
        ball.setX(5);
        assertTrue(ball.getX()==5);
        ball.setY(4);
        assertTrue(ball.getY()==4);
        ball.setSpeedToZero();
        assertTrue(ball.getSpeedX()==0);
        assertTrue(ball.getSpeedY()==0);
        ball.setSpeedY(3);
        assertTrue(ball.getSpeedY()==3);
        ball.setSpeed(4,0);
        assertTrue(ball.getSpeedX()==4);
        assertTrue(ball.getSpeedY()==0);
    }
}