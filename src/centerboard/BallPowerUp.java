package centerboard;

import models.Ball;
import models.Paddle;

import java.awt.*;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class BallPowerUp extends Cell implements PowerUp {
    @Override
    public void usePU(Ball b, Paddle p) {
        b.setSpeed((b.getSpeed()+1),b.getMoveAngle());
    }

    @Override
    public void spawn() {
        type = BALL_POWERUP;
    }

    @Override
    public Color getColor() {
        return BALL_PU_COLOR;
    }
}
