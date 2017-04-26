package models.centerboard;

import models.Ball;
import models.Paddle;

/**
 * Interface for power up. All power ups must implement
 * all methods provided here
 * @author Faiz Ghifari Haznitrama <13515010@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */

public interface PowerUp {
    /**
     * Use power up based on the owner of the ball
     * @param b = ball
     * @param p = paddle last hit the ball
     */
    void usePU(Ball b, Paddle p);
}
