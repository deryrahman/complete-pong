package spawnplugins;

import java.awt.*;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class BrickArea extends CenterArea{

    public BrickArea() {
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++) {
                bool_matrix[i][j] = false;
            }
        }
        color = DEFAULT_COLOR;
    }
}
