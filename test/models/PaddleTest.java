package models;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/25/2017.
 */
public class PaddleTest {
    Paddle paddle = new Paddle(4,6,3, Color.BLACK);
    @Test
    public void constructorTest() {
        assertTrue(paddle.getX()==4);
        assertTrue(paddle.getY()==6);
        assertTrue(paddle.getSpeedY()==0);
        assertTrue(paddle.getLength()==3);
        assertTrue(paddle.getWidth()==10);
        assertTrue(paddle.getColor()==Color.BLACK);
    }
    @Test
    public void setterTest() {
        paddle.setSpeedY(2);
        assertTrue(paddle.getSpeedY()==2);
        paddle.updateMove();
        assertTrue(paddle.getY()==8);
        paddle.setY(4);
        assertTrue(paddle.getY()==4);
    }
}