package spawnplugins;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class CenterArea implements SpawnPlugin{
    static boolean[][] bool_matrix = new boolean[10][10];
    static int[][] type_matrix = new int[10][10];
    static final Color DEFAULT_COLOR = Color.BLACK;
    private final float height = 400;
    private final float width = 200;
    private final float brickLength = 40;
    private final float brickWidth = 20;
    private final float brickBorder = 2;
    Color color;

    public CenterArea() {
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++) {
                bool_matrix[i][j] = false;
                type_matrix[i][j] = 0;
            }
        }
        color = DEFAULT_COLOR;
    }

    // getter
    public Color getColor(){
        return color;
    }
    public float getHeight() { return height; }
    public float getWidth() { return width; }
    public float getBrickLength(){
        return brickLength;
    }
    public float getBrickWidth(){
        return brickWidth;
    }
    public float getBrickBorder() { return brickBorder; }
    public boolean getBrick(int x,int y) { return bool_matrix[x][y]; }
    public int getType(int x,int y) { return type_matrix[x][y]; }

    @Override
    public void spawn() {
        int nx,ny;
        do {
            Random rand = new Random();
            nx = rand.nextInt(10);
            ny = rand.nextInt(10);
        } while(bool_matrix[nx][ny]);
        bool_matrix[nx][ny] = true;
    }

    @Override
    public void destroy(int x, int y) {
        bool_matrix[x][y] = false;
        type_matrix[x][y] = 0;
    }
}
