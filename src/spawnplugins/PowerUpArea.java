package spawnplugins;

import models.Ball;
import models.Paddle;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public abstract class PowerUpArea extends CenterArea {
    int typePU;

    public int getTypePU() { return typePU; }
    abstract void usePU(Ball b, Paddle p);
}
