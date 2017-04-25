package centerboard;;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/26/2017.
 */
public class PaddlePowerUpTest {
    PaddlePowerUp paddlePowerUp= new PaddlePowerUp();
    @Test
    public void constructorTest() {
        assertTrue(paddlePowerUp.getCellBorder()==2);
        assertTrue(paddlePowerUp.getCellLength()==40);
        assertTrue(paddlePowerUp.getCellWidth()==20);
    }
    @Test
    public void generalTest() {
        assertTrue(paddlePowerUp.getColor()==Cell.PAD_PU_COLOR);
        paddlePowerUp.spawn();
        assertTrue(paddlePowerUp.type==Cell.PAD_POWERUP);
        paddlePowerUp.destroy();
        assertTrue(paddlePowerUp.type==Cell.NO_BRICK);
    }
}