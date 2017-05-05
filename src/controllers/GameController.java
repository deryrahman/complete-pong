package controllers;

import models.Ball;
import models.Board;
import models.Player;
import models.centerboard.*;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * GameController class as controller for all objects in-game
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class GameController implements Runnable, KeyListener {
    /**
     * define the update rate as constant
     */
    private static final int UPDATE_RATE = 100;

    /**
     * thread specially responsible for players to implements
     * multithreading by concurrency
     */
    private Thread t;

    /**
     * ball's most left side
     */
    private float ballMinX;

    /**
     * ball's most top side
     */
    private float ballMinY;

    /**
     * ball's most right side
     */
    private float ballMaxX;

    /**
     * ball's most bottom side
     */
    private float ballMaxY;

    /**
     * paddle1's most left side
     */
    private float minXPaddle1;
    /**
     * paddle2's most left side
     */
    private float minXPaddle2;

    /**
     * paddle1's most top side
     */
    private float minYPaddle1;

    /**
     * paddle1's most bottom side
     */
    private float maxYPaddle1;

    /**
     * paddle2's most top side
     */
    private float minYPaddle2;

    /**
     * paddle's most bottom side
     */
    private float maxYPaddle2;

    /**
     * define the ball speed
     */
    private float ballSpeed;

    /**
     * boolean to check when the ball scored
     */
    private boolean isMakeScore;
    // model
    /**
     * define player 1
     */
    private Player player1;
    /**
     * define player 2
     */
    private Player player2;
    /**
     * define ball used in-game
     */
    private Ball ball;
    /**
     * define the board (background)
     */
    private Board board;
    /**
     * define special area for powerups and bricks
     */
    private CenterArea centerArea;
    // view
    /**
     * define the source from package view
     */
    private GameView gameView;

    /**
     * Game controller constructor, initialize all members
     * based on source
     * @param gameView source from package view
     */
    public GameController(GameView gameView){
        this.gameView = gameView;
        ball = gameView.getBall();
        board = gameView.getBoard();
        player1 = gameView.getPlayers()[0];
        player2 = gameView.getPlayers()[1];
        centerArea = gameView.getCenterArea();
        isMakeScore = true;
        ballSpeed = ball.getSpeed();
        gameView.addKeyListener(this);
    }

    /**
     * repaint all objects
     */
    public void updatePaint(){
        gameView.repaint();
    }

    /**
     * method that responsible to move the ball
     */
    public void moveBall(){
        ball.updateMove();
        updateBoundary();

        ballHitBrick();
        ballHitPaddle();
        ballInGameBorder();
    }

    /**
     * method to keep the ball on the board
     */
    private void ballInGameBorder() {
        if(ball.getX()<ballMinX){
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player2.makeScore(5);
            isMakeScore = true;
        } else if (ball.getX() > ballMaxX) {
            ball.reverseSpeedX();
            ball.setX(gameView.getWidth()/2);
            ball.setY(gameView.getHeight()/2);
            ball.setSpeedToZero();
            player1.makeScore(5);
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

    /**
     * method for checking the ball whenever it hits a paddle
     */
    private void ballHitPaddle() {
        if(ball.getX()<minXPaddle1 && ball.getY()>minYPaddle1 && ball.getY()<maxYPaddle1){
            float angle = (player1.getPaddle().getY()-ball.getY())/player1.getPaddle().getLength()*90;
            ball.reverseSpeedX();
            ball.setX(minXPaddle1);
            ball.setSpeed(ball.getSpeed(),angle);
            ball.setBelongsTo(0);
        }

        if(ball.getX()>minXPaddle2 && ball.getY()>minYPaddle2 && ball.getY()<maxYPaddle2){
            float angle = (player2.getPaddle().getY()-ball.getY())/player1.getPaddle().getLength()*90;
            ball.reverseSpeedX();
            ball.setX(minXPaddle2);
            ball.setSpeed(ball.getSpeed(),angle);
            ball.reverseSpeedX();
            ball.setBelongsTo(1);
        }
    }

    /**
     * method for checking the ball whenever it hits a brick or power up
     */
    private void ballHitBrick() {
        int brickMinX, brickMaxX;
        int brickMinY, brickMaxY;
        for(int i = 0;i < centerArea.getMatrixHeight();i++) {
            for (int j = 0; j < centerArea.getMatrixWidth(); j++) {
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
                        if(cell instanceof Brick){
                            if(ball.getBelongsTo()==0)
                                player1.makeScore();
                            else if(ball.getBelongsTo()==1)
                                player2.makeScore();
                        }
                        centerArea.destroyCell(i,j);
                    }
                }
            }
        }
    }

    /**
     * update all attributes needed every movement occurs
     */
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

    /**
     * override run method fron runnable
     * give sign to thread to run
     * <p>
     * responsible on every actions done all objects except player
     * e.g. ball movement
     */
    @Override
    public void run() {
        while(true){
            moveBall();
            updatePaint();
            if(gameView.isHasWinner()) break;
            try {
                Thread.sleep(1000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        updatePaint();
    }

    /**
     * Constructor for thread and ask thread to start
     */
    public void start(){
        if(t==null){
            t = new Thread(this);
            t.start();
        }
        ball.setSpeedToZero();
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

    /**
     * Override keyPressed method from runnable
     * control actions when key pressed
     * @param keyEvent = key that being released
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
