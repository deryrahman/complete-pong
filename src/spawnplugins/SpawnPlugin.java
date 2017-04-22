package spawnplugins;

import models.Ball;
import models.Paddle;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */
public interface SpawnPlugin {
    void changeBSpeed(Ball b, float newspeed);
    void changeLPaddle(Paddle p, float newpaddle);
}
