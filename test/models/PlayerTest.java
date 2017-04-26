package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit for unit test. Test Player Class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class PlayerTest {
    /**
     * initialize player
     */
    Player player= new Player("Pemain");

    /**
     * method test
     */
    @Test
    public void generalTest() {
        assertTrue(player.getPlayerName()=="Pemain");
        assertTrue(player.getScores()==0);
        player.makeScore(); player.makeScore();
        assertTrue(player.getScores()==2);
    }
    Paddle paddle= new Paddle(2,2,3);

    /**
     * constructor test
     */
    @Test
    public void paddleTest() {
        player.add(paddle);
        assertTrue(player.getPaddle()==paddle);
    }
}