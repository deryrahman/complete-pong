package models;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * JUnit for unit test. Test Board Class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class BoardTest {
    /**
     * initialize board
     */
    Board board= new Board(5,5,100,200, Color.CYAN);

    /**
     * constructor test
     */
    @Test
    public void constructorTest() {
        assertTrue(board.getMinX()==5);
        assertTrue(board.getMinY()==5);
        assertTrue(board.getMaxX()==104);
        assertTrue(board.getMaxY()==204);
        assertTrue(board.getColorFilled()==Color.CYAN);
    }
}