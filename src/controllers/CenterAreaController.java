package controllers;

import centerboard.CenterArea;
import views.GameView;

import java.util.Random;

/**
 * CenterAreaController class as controller for brick and power up
 * @author Faiz Ghifari Haznitrama <13515010@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class CenterAreaController implements Runnable {
    /**
     * define the update rate
     */
    private static final int UPDATE_RATE = 100;

    /**
     * thread specially responsible for players to implements
     * multithreading by concurrency
     */
    private Thread t;

    /**
     * define center area for place brick and power up
     */
    private CenterArea centerArea;

    /**
     * CenterAreaController constructor, initialize center area
     * @param gameView source for center area
     */
    public CenterAreaController(GameView gameView){
        this.centerArea = gameView.getCenterArea();
    }

    /**
     * override run method fron runnable
     * give sign to thread to run
     * <p>
     * responsible on every actions done all objects except player
     * e.g. ball movement
     */
    @Override
    public void run() {
        while(true){
            int type;
            Random rand = new Random();
            type = rand.nextInt(12);
            if(type>3 || type==0){
                centerArea.setRandomCell(1);
            } else
                centerArea.setRandomCell(type);

            try {
                Thread.sleep(200000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Constructor for thread and ask thread to start
     */
    public void start() {
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }
}
