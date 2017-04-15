package controllers;

import models.Ball;
import models.Board;
import models.Player;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable {
    private static final int UPDATE_RATE = 100;
    private Thread t;

    // model
    private Ball ball;
    private Board board;
    // view
    private GameView gameView;

    public GameController(GameView gameView){
        this.gameView = gameView;
        ball = gameView.getBall();
        board = gameView.getBoard();
    }

    public void update(){
        gameView.repaint();
    }

    public void moveBall(){
        float ballMinX = board.getMinX() + ball.getRadius();
        float ballMinY = board.getMinY() + ball.getRadius();
        float ballMaxX = board.getMaxX() - ball.getRadius();
        float ballMaxY = board.getMaxY() - ball.getRadius();

        ball.updateMove();

        if(ball.getX()<ballMinX){
            ball.reverseSpeedX();
            ball.setX(ballMinX);
        } else if (ball.getX() > ballMaxX) {
            ball.reverseSpeedX();
            ball.setX(ballMaxX);
        }

        if (ball.getY() < ballMinY) {
            ball.reverseSpeedY();
            ball.setY(ballMinY);
        } else if (ball.getY() > ballMaxY) {
            ball.reverseSpeedY();
            ball.setY(ballMaxY);
        }
    }

    @Override
    public void run() {
        while(true){
            moveBall();
            update();
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
}
