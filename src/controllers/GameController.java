package controllers;

import models.Ball;
import models.Board;
import models.Player;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable, KeyListener {
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
        gameView.addKeyListener(this);
    }

    public void update(){
        gameView.repaint();
    }

    public void moveBall(){
        float ballMinX = board.getMinX() + ball.getRadius();
        float ballMinY = board.getMinY() + ball.getRadius();
        float ballMaxX = board.getMaxX() - ball.getRadius();
        float ballMaxY = board.getMaxY() - ball.getRadius();
        Player player1 = gameView.getPlayers()[0];
        Player player2 = gameView.getPlayers()[1];
        float minXPaddle1 = player1.getPaddle().getX()+player1.getPaddle().getWidth()/2+ball.getRadius();
        float minXPaddle2 = player2.getPaddle().getX()-player2.getPaddle().getWidth()/2-ball.getRadius();
        float minYPaddle1 = player1.getPaddle().getY()-player1.getPaddle().getLength()/2-ball.getRadius();
        float maxYPaddle1 = player1.getPaddle().getY()+player1.getPaddle().getLength()/2+ball.getRadius();
        float minYPaddle2 = player2.getPaddle().getY()-player2.getPaddle().getLength()/2-ball.getRadius();
        float maxYPaddle2 = player2.getPaddle().getY()+player2.getPaddle().getLength()/2+ball.getRadius();

        ball.updateMove();

        if(ball.getX()<minXPaddle1 && ball.getY()>minYPaddle1 && ball.getY()<maxYPaddle1){
            ball.reverseSpeedX();
            ball.setX(minXPaddle1);
        }

        if(ball.getX()>minXPaddle2 && ball.getY()>minYPaddle2 && ball.getY()<maxYPaddle2){
            ball.reverseSpeedX();
            ball.setX(minXPaddle2);
        }

        if(ball.getX()<ballMinX){
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player1.makeScore();
        } else if (ball.getX() > ballMaxX) {
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player2.makeScore();
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println(KeyEvent.VK_SPACE);
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
            ball.setSpeed(5,20);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
