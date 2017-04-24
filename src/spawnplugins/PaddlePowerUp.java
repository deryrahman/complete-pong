package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class PaddlePowerUp extends PowerUpArea {
    public PaddlePowerUp() {
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++) {
                bool_matrix[i][j] = false;
            }
        }
        color = Color.orange;
        typePU = 2;
    }

    @Override
    public void usePU(Ball b, Paddle p) {
        p.changeLength(150);
    }
}
