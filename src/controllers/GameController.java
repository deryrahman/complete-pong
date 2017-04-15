package controllers;

import models.Ball;
import models.Board;
import models.Paddle;
import views.GameView;

public class GameController implements Runnable {
    private static final int UPDATE_RATE = 100;

    // model
    private Ball ball;
    private Board board;
    private Paddle[] paddles = new Paddle[2];
    // view
    private GameView gameView;

    public GameController(int width, int height){
        ball = new Ball(width/2,height/2,10,5,20);
        board = new Board(0,0,width,height);
        paddles[0] = new Paddle(25,height/2,100);
        paddles[1] = new Paddle(width-25,height/2,100);
        gameView = new GameView(width,height);
        gameView.add(ball);
        gameView.add(board);
        gameView.add(paddles);
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
}
