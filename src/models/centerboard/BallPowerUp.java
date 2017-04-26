package models.centerboard;

import models.Ball;
import models.Paddle;

import java.awt.*;

/**
 * BallPlayerUp Class as model for type-ball power up
 * Take responsibility on spawning ball power ups
 * @author Faiz Ghifari Haznitrama <13515010@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */

public class BallPowerUp extends Cell implements PowerUp {
    /**
     * Use power up based on the owner of the ball
     * @param b = ball
     * @param p = paddle last hit the ball
     */
    @Override
    public void usePU(Ball b, Paddle p) {
        b.setSpeed((b.getSpeed()+1),b.getMoveAngle());
    }

    /**
     * spawning power up on center area
     */
    @Override
    public void spawn() {
        type = BALL_POWERUP;
    }

    /**
     * get power up color
     * @return power up color
     */
    @Override
    public Color getColor() {
        return BALL_PU_COLOR;
    }
}
