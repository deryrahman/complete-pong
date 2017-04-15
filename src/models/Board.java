package models;

import java.awt.*;

public class Board {
    private int minX, minY, maxX, maxY;
    private Color colorFilled;
    private static final Color DEFAULT_COLOR_FILLED = Color.BLACK;

    public Board(int x, int y, int width, int height, Color colorFilled){
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
        this.colorFilled = colorFilled;
    }

    public Board(int x, int y, int width, int height){
        this(x,y,width,height,DEFAULT_COLOR_FILLED);
    }

//    public void set(int x, int y, int width, int height){
//        minX = x;
//        minY = y;
//        maxX = x + width - 1;
//        maxY = y + height - 1;
//    }

    public Color getColorFilled(){
        return colorFilled;
    }
    public int getMinX(){
        return minX;
    }
    public int getMinY(){
        return minY;
    }
    public int getMaxX(){
        return maxX;
    }
    public int getMaxY(){
        return maxY;
    }
}
