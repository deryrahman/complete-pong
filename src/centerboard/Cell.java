package centerboard;

import java.awt.*;

/**
 * Cell Class as model abstract class for all other classes
 * on package centerboard. Responsible for all member used
 * by all classes on package centerboard
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */

public abstract class Cell {
    /**
     * define the type of object (power up or brick)
     */
    protected int type;

    /**
     * define cell length
     */
    protected int cellLength = 40;

    /**
     * define cell width
     */
    protected int cellWidth = 20;

    /**
     * define cell border
     */
    protected int cellBorder = 2;

    /**
     * default color for all object on package centerboard
     */
    public static final Color DEFAULT_COLOR = Color.BLACK;

    /**
     * define default color for brick
     */
    public static final Color BRICK_COLOR = Color.WHITE;

    /**
     * define default color for paddle power up
     */
    public static final Color PAD_PU_COLOR = Color.ORANGE;

    /**
     * define default color for ball power up
     */
    public static final Color BALL_PU_COLOR = Color.RED;

    /**
     * type 0 sign no object
     */
    public static final int NO_BRICK = 0;

    /**
     * type 1 sign brick
     */
    public static final int BRICK = 1;

    /**
     * type 2 sign paddle power up
     */
    public static final int PAD_POWERUP = 2;

    /**
     * type 3 sign ball power up
     */
    public static final int BALL_POWERUP = 3;

    /**
     * abstract method spawn, implemented by all real classes
     * on package centerboard for spawning objects
     */
    abstract public void spawn();

    /**
     * abstract method getColor to get object's color
     * @return object's color
     */
    abstract public Color getColor();

    /**
     * destroy object on that place
     */
    public void destroy(){
        type = NO_BRICK;
    }

    /**
     * return cell length
     * @return cell length
     */
    public int getCellLength() { return cellLength; }

    /**
     * return cell width
     * @return cell width
     */
    public int getCellWidth() { return  cellWidth; }

    /**
     * return cell border
     * @return cell border
     */
    public int getCellBorder() { return  cellBorder; }
}
