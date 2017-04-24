package controllers;

import models.Ball;
import models.Board;
import models.Player;
import spawnplugins.BrickArea;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable, KeyListener {
    private static final int UPDATE_RATE = 100;
    private Thread t;

    private float ballMinX;
    private float ballMinY;
    private float ballMaxX;
    private float ballMaxY;
    private float minXPaddle1;
    private float minXPaddle2;
    private float minYPaddle1;
    private float maxYPaddle1;
    private float minYPaddle2;
    private float maxYPaddle2;

    private boolean isMakeScore;
    // model
    private Player player1;
    private Player player2;
    private Ball ball;
    private Board board;
    private BrickArea brickArea;
    // view
    private GameView gameView;

    public GameController(GameView gameView){
        this.gameView = gameView;
        ball = gameView.getBall();
        board = gameView.getBoard();
        player1 = gameView.getPlayers()[0];
        player2 = gameView.getPlayers()[1];
        brickArea = gameView.getBrickArea();
        isMakeScore = false;
        gameView.addKeyListener(this);
    }

    public void updatePaint(){
        gameView.repaint();
    }



    public void moveBall(){
        ball.updateMove();
        updateBoundary();

        ballHitBrick();
        ballHitPaddle();
        ballInGameBorder();
    }

    private void ballInGameBorder() {
        if(ball.getX()<ballMinX){
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player1.makeScore();
            isMakeScore = true;
        } else if (ball.getX() > ballMaxX) {
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player2.makeScore();
            isMakeScore = true;
        }

        if (ball.getY() < ballMinY) {
            ball.reverseSpeedY();
            ball.setY(ballMinY);
        } else if (ball.getY() > ballMaxY) {
            ball.reverseSpeedY();
            ball.setY(ballMaxY);
        }
    }

    private void ballHitPaddle() {
        if(ball.getX()<minXPaddle1 && ball.getY()>minYPaddle1 && ball.getY()<maxYPaddle1){
            ball.reverseSpeedX();
            ball.setX(minXPaddle1);
        }

        if(ball.getX()>minXPaddle2 && ball.getY()>minYPaddle2 && ball.getY()<maxYPaddle2){
            ball.reverseSpeedX();
            ball.setX(minXPaddle2);
        }
    }

    private void ballHitBrick() {
        int brickMinX, brickMaxX;
        int brickMinY, brickMaxY;
        for(int i = 0;i < 10;i++) {
            for (int j = 0; j < 10; j++) {
                if(brickArea.getBrick(i,j)) {
                    brickMinY = 40*i; brickMaxY = 40*(i+1);
                    brickMinX = (int) (400-brickArea.getWidth()/2+20*i); brickMaxX = (int) (400-brickArea.getWidth()/2+20*(i+1));
                    if(ball.getX()>brickMinX && ball.getX()<brickMaxX &&
                            ball.getY()>brickMinY && ball.getY()<brickMaxY) {
                        ball.reverseSpeedX();
                        brickArea.destroy(i,j);
                    }
                }
            }
        }
    }

    private void updateBoundary() {
        ballMinX = board.getMinX() + ball.getRadius();
        ballMinY = board.getMinY() + ball.getRadius();
        ballMaxX = board.getMaxX() - ball.getRadius();
        ballMaxY = board.getMaxY() - ball.getRadius();
        minXPaddle1 = player1.getPaddle().getX()+player1.getPaddle().getWidth()/2+ball.getRadius();
        minXPaddle2 = player2.getPaddle().getX()-player2.getPaddle().getWidth()/2-ball.getRadius();
        minYPaddle1 = player1.getPaddle().getY()-player1.getPaddle().getLength()/2-ball.getRadius();
        maxYPaddle1 = player1.getPaddle().getY()+player1.getPaddle().getLength()/2+ball.getRadius();
        minYPaddle2 = player2.getPaddle().getY()-player2.getPaddle().getLength()/2-ball.getRadius();
        maxYPaddle2 = player2.getPaddle().getY()+player2.getPaddle().getLength()/2+ball.getRadius();
    }

    @Override
    public void run() {
        while(true){
            moveBall();
            updatePaint();
            try {
                Thread.sleep(1000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println(KeyEvent.VK_SPACE);
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE && isMakeScore){
            ball.setSpeed(5,20);
            isMakeScore=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
