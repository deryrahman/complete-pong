package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit for unit test. Test PaddlePowerUp class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class PaddlePowerUpTest {

    /**
     * initialize paddle power up
     */
    PaddlePowerUp paddlePowerUp= new PaddlePowerUp();

    /**
     * constructor test
     */
    @Test
    public void constructorTest() {
        assertTrue(paddlePowerUp.getCellBorder()==2);
        assertTrue(paddlePowerUp.getCellLength()==40);
        assertTrue(paddlePowerUp.getCellWidth()==20);
    }

    /**
     * method test
     */
    @Test
    public void generalTest() {
        assertTrue(paddlePowerUp.getColor()==Cell.PAD_PU_COLOR);
        paddlePowerUp.spawn();
        assertTrue(paddlePowerUp.type==Cell.PAD_POWERUP);
    }
}