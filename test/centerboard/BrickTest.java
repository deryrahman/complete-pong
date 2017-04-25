package centerboard;;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/26/2017.
 */
public class BrickTest {
    Brick brick= new Brick();
    @Test
    public void constructorTest() {
        assertTrue(brick.getCellBorder()==2);
        assertTrue(brick.getCellLength()==40);
        assertTrue(brick.getCellWidth()==20);
    }
    @Test
    public void generalTest() {
        brick.spawn();
        assertTrue(brick.type==Cell.BRICK);
        assertTrue(brick.getColor()==Cell.BRICK_COLOR);
        brick.destroy();
        assertTrue(brick.type==Cell.NO_BRICK);
    }
}