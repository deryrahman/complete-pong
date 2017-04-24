package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class BallPowerUp extends PowerUpArea {
    public BallPowerUp() {
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++) {
                bool_matrix[i][j] = false;
            }
        }
        color = Color.red;
        typePU = 1;
    }

    @Override
    public void usePU(Ball b, Paddle p) {
        float getAngle = b.getMoveAngle();
        b.setSpeed(8,getAngle);
    }
}
