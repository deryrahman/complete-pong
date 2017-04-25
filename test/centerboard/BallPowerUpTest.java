package centerboard;;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/26/2017.
 */
public class BallPowerUpTest {
    BallPowerUp ballPowerUp= new BallPowerUp();
    @Test
    public void constructorTest() {
        assertTrue(ballPowerUp.getCellBorder()==2);
        assertTrue(ballPowerUp.getCellLength()==40);
        assertTrue(ballPowerUp.getCellWidth()==20);
    }
    @Test
    public void generalTest() {
        assertTrue(ballPowerUp.getColor()==Cell.BALL_PU_COLOR);
        ballPowerUp.spawn();
        assertTrue(ballPowerUp.type==Cell.BALL_POWERUP);
        ballPowerUp.destroy();
        assertTrue(ballPowerUp.type==Cell.NO_BRICK);
    }
}