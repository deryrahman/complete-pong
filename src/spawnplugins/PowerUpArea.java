package spawnplugins;

import models.Ball;
import models.Paddle;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public abstract class PowerUpArea extends CenterArea {

    abstract void usePU(Ball b, Paddle p);

}
