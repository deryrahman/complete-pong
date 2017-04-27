package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/27/2017.
 */
public class BallPowerUpTest {
    BallPowerUp ballPowerUp= new BallPowerUp();
    @Test
    public void constructorTest() {
        assertTrue(ballPowerUp.getCellLength()==40);
        assertTrue(ballPowerUp.getCellWidth()==20);
        assertTrue(ballPowerUp.getCellBorder()==2);
    }
    @Test
    public void generalTest() {
        assertTrue(ballPowerUp.getColor()==Cell.BALL_PU_COLOR);
        ballPowerUp.spawn();
        assertTrue(ballPowerUp.type==Cell.BALL_POWERUP);
    }
}