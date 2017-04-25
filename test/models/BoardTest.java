package models;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Rahman on 4/25/2017.
 */
public class BoardTest {
    Board board= new Board(5,5,100,200, Color.CYAN);
    @Test
    public void constructorTest() {
        assertTrue(board.getMinX()==5);
        assertTrue(board.getMinY()==5);
        assertTrue(board.getMaxX()==104);
        assertTrue(board.getMaxY()==204);
        assertTrue(board.getColorFilled()==Color.CYAN);
    }
}