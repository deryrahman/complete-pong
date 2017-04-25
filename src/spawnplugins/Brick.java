package spawnplugins;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class BrickArea extends CenterArea{

    public BrickArea(int width, int height) {
        this.width=width;
        this.height=height;
        type = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                type[i][j] = NO_BRICK;
            }
        }
    }

    @Override
    public void spawn(int i, int j) {
        type[i][j] = BRICK;
    }

    @Override
    public Color getColor() {
        return BRICK_COLOR;
    }
}
