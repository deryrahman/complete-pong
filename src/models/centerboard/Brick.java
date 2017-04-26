package models.centerboard;

import java.awt.*;

/**
 * Brick Class as model for brick
 * Take responsibility on spawning bricks
 * @author Faiz Ghifari Haznitrama <13515010@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */

public class Brick extends Cell{

    /**
     * spawning brick on center area
     */
    @Override
    public void spawn() {
        type = BRICK;
    }

    /**
     * get brick's color
     * @return brick's color
     */
    @Override
    public Color getColor() {
        return BRICK_COLOR;
    }
}
