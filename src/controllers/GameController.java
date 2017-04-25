package controllers;

import models.Ball;
import models.Board;
import models.Paddle;
import models.Player;
import spawnplugins.*;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
    private float ballSpeed;

    private boolean isMakeScore;
    // model
    private Player player1;
    private Player player2;
    private Ball ball;
    private Board board;
    private CenterArea centerArea;
    private Brick brickArea;
    private BallPowerUp ballPowerUp;
    private PaddlePowerUp paddlePowerUp;
    // view
    private GameView gameView;

    public GameController(GameView gameView){
        this.gameView = gameView;
        ball = gameView.getBall();
        board = gameView.getBoard();
        player1 = gameView.getPlayers()[0];
        player2 = gameView.getPlayers()[1];
        centerArea = gameView.getCenterArea();
        isMakeScore = false;
        ballSpeed = ball.getSpeed();
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
            player2.makeScore();
            isMakeScore = true;
        } else if (ball.getX() > ballMaxX) {
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player1.makeScore();
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
            ball.setSpeed(ball.getSpeed(),(player1.getPaddle().getY()-ball.getY())/player1.getPaddle().getLength()*90);
//            ball.setSpeedY(-(player1.getPaddle().getY()-ball.getY())/player1.getPaddle().getLength()*10);
            ball.setBelongsTo(0);
        }

        if(ball.getX()>minXPaddle2 && ball.getY()>minYPaddle2 && ball.getY()<maxYPaddle2){
            ball.reverseSpeedX();
            ball.setX(minXPaddle2);
            ball.setSpeed(ball.getSpeed(),(player2.getPaddle().getY()-ball.getY())/player1.getPaddle().getLength()*90);
            ball.reverseSpeedX();
//            ball.setSpeedY(-(player2.getPaddle().getY()-ball.getY())/player2.getPaddle().getLength()*10);
            ball.setBelongsTo(1);
        }
    }

    private void ballHitBrick() {
        int brickMinX, brickMaxX;
        int brickMinY, brickMaxY;
        for(int i = 0;i < 10;i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = centerArea.getCell(i,j);
                if(cell != null) {
                    brickMinY = (int) (cell.getCellLength()*i-ball.getRadius());
                    brickMaxY = (int) (cell.getCellLength()*(i+1)+ball.getRadius());
                    brickMinX = (int) (gameView.getCanvasWidth()/2-centerArea.getWidth()/2+cell.getCellWidth()*j-ball.getRadius());
                    brickMaxX = (int) (gameView.getCanvasWidth()/2-centerArea.getWidth()/2+cell.getCellWidth()*(j+1)+ball.getRadius());
                    if(ball.getX()>brickMinX && ball.getX()<brickMaxX &&
                            ball.getY()>brickMinY && ball.getY()<brickMaxY) {
                        if(Math.abs(ball.getX()-brickMinX)<6) {
                            if(ball.getSpeedX()>0)
                                ball.reverseSpeedX();
                        } else if (Math.abs(ball.getX()-brickMaxX)<6){
                            if(ball.getSpeedX()<0)
                                ball.reverseSpeedX();
                        }
                        if(Math.abs(ball.getY()-brickMinY)<6) {
                            if(ball.getSpeedY()>0)
                                ball.reverseSpeedY();
                        } else if (Math.abs(ball.getY()-brickMaxY)<6){
                            if(ball.getSpeedY()<0)
                                ball.reverseSpeedY();
                        }
                        if(cell instanceof BallPowerUp) {
                            ((BallPowerUp) cell).usePU(ball,null);
                        }
                        if(cell instanceof PaddlePowerUp){
                            if(ball.getBelongsTo()==0)
                                ((PaddlePowerUp) cell).usePU(null,player1.getPaddle());
                            else if (ball.getBelongsTo()==1)
                                ((PaddlePowerUp) cell).usePU(null,player2.getPaddle());
                        }
                        centerArea.destroyCell(i,j);
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
            Random rand = new Random();
            int randomAngle = rand.nextInt(360);
            while ((randomAngle>45 && randomAngle<135) || (randomAngle>225 && randomAngle<315))
                randomAngle-=45;
            ball.setSpeed(ballSpeed,randomAngle);
            isMakeScore=false;
            ball.setBelongsTo(-1);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
