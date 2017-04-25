package controllers;

import spawnplugins.BallPowerUp;
import spawnplugins.Brick;
import spawnplugins.CenterArea;
import spawnplugins.PaddlePowerUp;
import views.GameView;

import java.util.Random;

/**
 * Created by Admin on 24/04/2017.
 */
public class CenterAreaController implements Runnable {
    private static final int UPDATE_RATE = 100;
    private Thread t;
    private CenterArea centerArea;

    public CenterAreaController(GameView gameView){
        this.centerArea = gameView.getCenterArea();
    }

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

    public void start() {
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }
}
