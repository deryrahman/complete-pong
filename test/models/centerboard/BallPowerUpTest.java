package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit for unit test. Testing BallPowerUp Class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class BallPowerUpTest {

    /**
     * initialize ball power up
     */
    BallPowerUp ballPowerUp= new BallPowerUp();

    /**
     * constructor test
     */
    @Test
    public void constructorTest() {
        assertTrue(ballPowerUp.getCellBorder()==2);
        assertTrue(ballPowerUp.getCellLength()==40);
        assertTrue(ballPowerUp.getCellWidth()==20);
    }

    /**
     * method test
     */
    @Test
    public void generalTest() {
        assertTrue(ballPowerUp.getColor()==Cell.BALL_PU_COLOR);
        ballPowerUp.spawn();
        assertTrue(ballPowerUp.type==Cell.BALL_POWERUP);
    }
}