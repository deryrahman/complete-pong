package models;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * JUnit for unit test. Test Paddle Class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class PaddleTest {
    /**
     * initialize paddle
     */
    Paddle paddle = new Paddle(4,6,3, Color.BLACK);

    /**
     * constructor test
     */
    @Test
    public void constructorTest() {
        assertTrue(paddle.getX()==4);
        assertTrue(paddle.getY()==6);
        assertTrue(paddle.getSpeedY()==0);
        assertTrue(paddle.getLength()==3);
        assertTrue(paddle.getWidth()==10);
        assertTrue(paddle.getColor()==Color.BLACK);
    }

    /**
     * method test
     */
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