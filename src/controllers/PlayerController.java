package controllers;

import models.Ball;
import models.Player;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

public class PlayerController implements Runnable, KeyListener{
    private static final int UPDATE_RATE = 100;
    private Player player;
    private Ball ball;
    private int playerNumber;
    private int canvasHeight;
    private int canvasWidth;
    private Thread t;

    public PlayerController(GameView gameView, int i){
        player = gameView.getPlayers()[i];
        playerNumber=i;
        canvasHeight = gameView.getCanvasHeight();
        canvasWidth = gameView.getCanvasWidth();
        ball = gameView.getBall();
        gameView.addKeyListener(this);
    }

    @Override
    public void run() {
        while (true){
            float minYPaddle = player.getPaddle().getY()-player.getPaddle().getLength()/2;
            float maxYPaddle = player.getPaddle().getY()+player.getPaddle().getLength()/2;
            if(minYPaddle<0 || maxYPaddle>canvasHeight)
                if(minYPaddle<0)
                    player.getPaddle().setY((int) (player.getPaddle().getLength()/2));
                if(maxYPaddle>canvasHeight)
                    player.getPaddle().setY((int) (canvasHeight-player.getPaddle().getLength()/2));
            else {
                player.getPaddle().updateMove();
            }

            player.getPaddle().changeLength();

            if(playerNumber==0){
                if((ball.getX()<canvasWidth/2 && playerNumber==0) || (ball.getX()>canvasWidth/2 && playerNumber==1)) {
                    Random rand = new Random();
                    int rd = rand.nextInt(5);
                    if (rd == 0) rd = 5;
                    float minYPad = player.getPaddle().getY() - player.getPaddle().getLength() / 2;
                    float maxYPad = player.getPaddle().getY() + player.getPaddle().getLength() / 2;
                    if (minYPad + player.getPaddle().getLength() / rd < ball.getY())
                        player.getPaddle().setSpeedY(4);
                    else if (maxYPad - player.getPaddle().getLength() / rd > ball.getY())
                        player.getPaddle().setSpeedY(-4);
                    else
                        player.getPaddle().setSpeedY(0);
                } else {
                    if(player.getPaddle().getY()>canvasHeight/2){
                        player.getPaddle().setSpeedY(-4);
                    } else {
                        player.getPaddle().setSpeedY(4);
                    }
                }
            }
            try{
                Thread.sleep(1000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        System.out.println(keyEvent.getKeyCode());
        if(playerNumber==0){
            switch (keyEvent.getKeyCode()){
                case KeyEvent.VK_W:
                    player.getPaddle().setSpeedY(-4);
                    break;
                case KeyEvent.VK_S:
                    player.getPaddle().setSpeedY(4);
                    break;
            }
        } else if (playerNumber==1){
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_UP:
                    player.getPaddle().setSpeedY(-4);
                    break;
                case KeyEvent.VK_DOWN:
                    player.getPaddle().setSpeedY(4);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        float direction = -player.getPaddle().getSpeedY();
        if((direction>0 && keyEvent.getKeyCode()==KeyEvent.VK_W && playerNumber==0) ||
                (direction>0 && keyEvent.getKeyCode()==KeyEvent.VK_UP && playerNumber==1) ||
                (direction<0 && keyEvent.getKeyCode()==KeyEvent.VK_S && playerNumber==0) ||
                (direction<0 && keyEvent.getKeyCode()==KeyEvent.VK_DOWN && playerNumber==1)) {
            player.getPaddle().setSpeedY(0);
        }
    }

    public void start(){
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }
}
