package centerboard;

import java.util.Random;

/**
 * Created by Faiz Ghifari Haznitrama on 22/04/2017.
 */

public class CenterArea {
    protected static Cell[][] cell;

    protected int height;
    protected int width;
    protected int matrixHeight=10;
    protected int matrixWidth=10;

    public CenterArea(int width, int height) {
        this.width=width;
        this.height=height;
        cell = new Cell[matrixHeight][matrixWidth];
        for(int i = 0; i < matrixHeight; i++){
            cell[i] = new Cell[matrixWidth];
//            for(int j = 0; j < width; j++) {
//                cell[i][j].type = Cell.NO_BRICK;
//            }
        }
    }

    public float getHeight() { return height; }
    public float getWidth() { return width; }
    public Cell getCell(int i, int j) { return cell[i][j]; }
    public void setRandomCell(int type) {
        int i, j;
        Random rand = new Random();
        i = rand.nextInt(10);
        j = rand.nextInt(10);
        if(cell[i][j]==null) {
            if (type == Cell.BRICK) {
                cell[i][j] = new Brick();
            } else if (type == Cell.BALL_POWERUP) {
                cell[i][j] = new BallPowerUp();
            } else if (type == Cell.PAD_POWERUP) {
                cell[i][j] = new PaddlePowerUp();
            }
            cell[i][j].spawn();
        }
    }
    public void destroyCell(int i, int j) { cell[i][j] = null; }

}
