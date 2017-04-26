package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit for unit test. Test Brick Class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class BrickTest {

    /**
     * initialize brick
     */
    Brick brick= new Brick();

    /**
     * constructor test
     */
    @Test
    public void constructorTest() {
        assertTrue(brick.getCellBorder()==2);
        assertTrue(brick.getCellLength()==40);
        assertTrue(brick.getCellWidth()==20);
    }

    /**
     * method test
     */
    @Test
    public void generalTest() {
        brick.spawn();
        assertTrue(brick.type==Cell.BRICK);
        assertTrue(brick.getColor()==Cell.BRICK_COLOR);
    }
}