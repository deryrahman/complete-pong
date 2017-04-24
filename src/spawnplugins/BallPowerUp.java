package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class BallPowerUp extends PowerUpArea {
    public BallPowerUp() {
        super();
        color = Color.red;
    }

    @Override
    public void usePU(Ball b, Paddle p) {
        b.setSpeed((float) (b.getSpeed()+1),b.getMoveAngle());
    }

    @Override
    public void spawn() {
        int nx,ny;
        do {
            Random rand = new Random();
            nx = rand.nextInt(10);
            ny = rand.nextInt(10);
        } while(bool_matrix[nx][ny]);
        bool_matrix[nx][ny] = true;
        type_matrix[nx][ny] = 3;
    }

}
