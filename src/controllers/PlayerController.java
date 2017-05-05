package controllers;

import models.Ball;
import models.Player;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * PlayerController class as controller for Player class
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class PlayerController implements Runnable, KeyListener{
    /**
     * define the update rate as constant
     */
    private static final int UPDATE_RATE = 100;

    /**
     * define player that's controlled
     */
    private Player player;

    /**
     * number of player as player identity
     */
    private int playerNumber;

    /**
     * information about canvas height to limit paddle movement
     * based on canvas size
     */
    private int canvasHeight;

    /**
     * information about canvas width to limit paddle movement
     * based on canvas size
     */
    private int canvasWidth;

    /**
     * Ball object used to knowing the last player hit by the ball
     */
    private Ball ball;

    /**
     * Thread used for multiprogramming with concurrency
     */
    private Thread t;

    /**
     * PlayerController constructor, initialized based on objects
     * on view class
     * @param gameView = source
     * @param i = player identifier
     */
    public PlayerController(GameView gameView, int i){
        player = gameView.getPlayers()[i];
        playerNumber=i;
        canvasHeight = gameView.getCanvasHeight();
        canvasWidth = gameView.getCanvasWidth();
        ball = gameView.getBall();
        gameView.addKeyListener(this);
    }

    /**
     * override run method fron runnable
     * give sign to thread to run
     * <p>
     * responsible on every actions done by player
     * e.g. paddle movement
     */
    @Override
    public void run() {
        while (true){

            // update paddle move
            movePaddle();

            // paddle length
            player.getPaddle().changeLength();

            // move bot
            if(playerNumber==0 && player.getPlayerName().equals("Bot")){
                botMove();
            }
            try{
                Thread.sleep(1000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * update paddle movement by position and speed
     */
    private void movePaddle() {
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
    }

    /**
     * update bot movement
     */
    private void botMove() {
        if(ball.getX()<canvasWidth/2) {
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

    /**
     * Override keyTyped method from runnable
     * control actions when key typed
     * @param keyEvent= key that being type
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Override keyPressed method from runnable
     * control actions when key pressed
     * @param keyEvent = key that being pressed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
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

    /**
     * Override keyPressed method from runnable
     * control actions when key pressed
     * @param keyEvent = key that being released
     */
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

    /**
     * Constructor for thread and ask thread to start
     */
    public void start(){
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }
}
