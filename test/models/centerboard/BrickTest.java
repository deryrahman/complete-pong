package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/27/2017.
 */
public class BrickTest {
    Brick brick= new Brick();
    @Test
    public void constructorTest() {
        assertTrue(brick.getCellLength()==40);
        assertTrue(brick.getCellWidth()==20);
        assertTrue(brick.getCellBorder()==2);
    }
    @Test
    public void generalTest() {
        assertTrue(brick.getColor()==Cell.BRICK_COLOR);
        brick.spawn();
        assertTrue(brick.type==Cell.BRICK);
    }
}