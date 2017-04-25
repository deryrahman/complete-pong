package spawnplugins;

import java.awt.*;

/**
 * Created by dery on 4/26/17.
 */
public abstract class Cell {
    protected int type;

    protected int cellLength = 40;
    protected int cellWidth = 20;
    protected int cellBorder = 2;

    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final Color BRICK_COLOR = Color.WHITE;
    public static final Color PAD_PU_COLOR = Color.ORANGE;
    public static final Color BALL_PU_COLOR = Color.RED;

    public static final int NO_BRICK = 0;
    public static final int BRICK = 1;
    public static final int PAD_POWERUP = 2;
    public static final int BALL_POWERUP = 3;

    abstract public void spawn();
    abstract public Color getColor();
    public void destroy(){
        type = NO_BRICK;
    }

    public int getCellLength() { return cellLength; }
    public int getCellWidth() { return  cellWidth; }
    public int getCellBorder() { return  cellBorder; }
}
