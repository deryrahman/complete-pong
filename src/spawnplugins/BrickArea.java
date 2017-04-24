package spawnplugins;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class BrickArea extends CenterArea{

    public BrickArea() {
        super();
        color = Color.WHITE;
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
        type_matrix[nx][ny] = 1;
    }
}
