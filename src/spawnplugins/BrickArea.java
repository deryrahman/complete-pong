package spawnplugins;

import models.Ball;
import models.Paddle;

import java.awt.*;
import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */
public class BrickArea implements SpawnPlugin{
    private float height;
    private float width;
    private float brickLength;
    private float brickWidth;
    private float brickBorder;
    private Color color;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static boolean [][] bool_matrix;

    public BrickArea() {
        bool_matrix = new boolean[10][10];
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                bool_matrix[i][j] = false;
            }
        }
        this.brickLength = 40;
        this.brickWidth = 20;
        this.brickBorder = 2;
        this.width = 200;
        this.height = 400;
        this.color = DEFAULT_COLOR;
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
    public boolean getBrick(int x,int y) { return bool_matrix[x][y]; }
    public float getBrickBorder() { return brickBorder; }
    public String toString() {
        return "Brick";
    }

    @Override
    public void spawn() {
        Random rand = new Random();
        int nx = rand.nextInt(10);
        int ny = rand.nextInt(10);
        bool_matrix[nx][ny] = true;
    }

    @Override
    public void destroy(int x, int y) {
        bool_matrix[x][y] = false;
    }
}
