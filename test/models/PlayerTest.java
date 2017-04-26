package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/25/2017.
 */
public class PlayerTest {
    Player player= new Player("Pemain");
    @Test
    public void generalTest() {
        assertTrue(player.getPlayerName()=="Pemain");
        assertTrue(player.getScores()==0);
        player.makeScore(); player.makeScore();
        assertTrue(player.getScores()==2);
    }
    Paddle paddle= new Paddle(2,2,3);
    @Test
    public void paddleTest() {
        player.add(paddle);
        assertTrue(player.getPaddle()==paddle);
    }
}