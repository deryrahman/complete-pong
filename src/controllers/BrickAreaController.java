package controllers;

import spawnplugins.BrickArea;
import views.GameView;

/**
 * Created by Admin on 24/04/2017.
 */
public class BrickAreaController implements Runnable {
    private static final int UPDATE_RATE = 100;
    private Thread t;
    private BrickArea brickArea;

    public BrickAreaController(GameView gameView){
        this.brickArea = gameView.getBrickArea();
    }

    @Override
    public void run() {
        while(true){
            brickArea.spawn();
            try {
                Thread.sleep(100000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }
}
