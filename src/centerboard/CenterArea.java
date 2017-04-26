package centerboard;

import java.util.Random;

/**
 * Center Area Class as model class to provide area
 * for centerboard objects to spawn itself
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */

public class CenterArea {
    /**
     * define all places provided
     */
    protected static Cell[][] cell;

    /**
     * define vertical size of center area
     */
    protected int height;

    /**
     * define horizontal size of center area
     */
    protected int width;

    /**
     * define vertical zone provided by cell
     */
    protected int matrixHeight=10;

    /**
     * define horizontal zone provided by cell
     */
    protected int matrixWidth=10;

    /**
     * CenterArea constructor, initialize area
     * @param width = horizontal area
     * @param height = vertical area
     */
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

    /**
     * get area's height
     * @return area's height
     */
    public float getHeight() { return height; }

    /**
     * get area's width
     * @return area's width
     */
    public float getWidth() { return width; }

    /**
     * get object on place (i,j)
     * @param i = horizontal
     * @param j = vertical
     * @return object cell
     */
    public Cell getCell(int i, int j) { return cell[i][j]; }

    /**
     * spawning random object on random place on center area
     * @param type = parameter for object spawned
     */
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

    /**
     * destroy object on cell
     * @param i = horizontal
     * @param j = vertical
     */
    public void destroyCell(int i, int j) { cell[i][j] = null; }

}
