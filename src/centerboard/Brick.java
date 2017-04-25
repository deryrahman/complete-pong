package centerboard;

import java.awt.*;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class Brick extends Cell{

    @Override
    public void spawn() {
        type = BRICK;
    }

    @Override
    public Color getColor() {
        return BRICK_COLOR;
    }
}
