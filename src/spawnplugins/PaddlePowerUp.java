package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class PaddlePowerUp extends PowerUpArea {
    public PaddlePowerUp() {
        super();
        color = Color.orange;
    }

    @Override
    public void usePU(Ball b, Paddle p) {
        p.changeLength(150);
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
        type_matrix[nx][ny] = 2;
    }
}
