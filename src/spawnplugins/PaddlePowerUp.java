package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class PaddlePowerUp extends Cell implements PowerUp {
    @Override
    public void usePU(Ball b, Paddle p) {
        p.changeLength(150);
    }

    @Override
    public void spawn() {
        type = PAD_POWERUP;
    }

    @Override
    public Color getColor() {
        return PAD_PU_COLOR;
    }
}
