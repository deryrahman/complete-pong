package controllers;

import spawnplugins.BallPowerUp;
import spawnplugins.BrickArea;
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
    private BrickArea brickArea;
    private BallPowerUp ballPowerUp;
    private PaddlePowerUp paddlePowerUp;

    public CenterAreaController(GameView gameView){
        this.centerArea = gameView.getCenterArea();
        this.brickArea = gameView.getBrickArea();
        this.ballPowerUp = gameView.getBallPowerUp();
        this.paddlePowerUp = gameView.getPaddlePowerUp();
    }

    @Override
    public void run() {
        while(true){
            int type;
            Random rand = new Random();
            type = rand.nextInt(10);
            if (type == 0) {
                ballPowerUp.spawn();
            } else if (type == 1) {
                paddlePowerUp.spawn();
            } else {
                brickArea.spawn();
            }
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
