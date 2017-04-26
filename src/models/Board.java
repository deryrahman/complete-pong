package models;

import java.awt.*;

/**
 * Board class as model class for board used in-game
 * Board is the background of the game, place where game take place
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Board {
    /**
     * member to define all constraints of the board
     * <p>
     * both vertical and horizontal
     */
    private int minX, minY, maxX, maxY;

    /**
     * define the board's color
     */
    private Color colorFilled;

    /**
     * define the default color for board (BLACK)
     */
    private static final Color DEFAULT_COLOR_FILLED = Color.BLACK;

    /**
     * Board constructor, initialize all member with color based
     * of parameter
     * @param x = left constraint
     * @param y = top constraint
     * @param width = width of the board, length from left to right constraint
     * @param height = height of the board, length from top to bottom constraint
     * @param colorFilled = board's color
     */
    public Board(int x, int y, int width, int height, Color colorFilled){
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
        this.colorFilled = colorFilled;
    }

    /**
     * Board constructor with default color
     * @param x = left constraint
     * @param y = top constraint
     * @param width = width of the board, length from left to right constraint
     * @param height = height of the board, length from top to bottom constraint
     */
    public Board(int x, int y, int width, int height){
        this(x,y,width,height,DEFAULT_COLOR_FILLED);
    }

//    public void set(int x, int y, int width, int height){
//        minX = x;
//        minY = y;
//        maxX = x + width - 1;
//        maxY = y + height - 1;
//    }

    // getter
    /**
     * get board's color
     * @return board's color
     */
    public Color getColorFilled(){
        return colorFilled;
    }

    /**
     * get board's left constraint
     * @return left constraint
     */
    public int getMinX(){
        return minX;
    }

    /**
     * get board's top constraint
     * @return top constraint
     */
    public int getMinY(){
        return minY;
    }

    /**
     * get board's right constraint
     * @return right constraint
     */
    public int getMaxX(){
        return maxX;
    }

    /**
     * get board's bottom constraint
     * @return bottom constraint
     */
    public int getMaxY(){
        return maxY;
    }
}
