package views;

import models.Ball;
import models.Board;
import models.Paddle;
import models.Player;
import models.centerboard.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * GameView Class as view class provide all graphics for pong game
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class GameView extends JFrame {
    // Constant
    /**
     * define maximum score
     */
    public final int MAX_SCORES = 30;

    // model
    /**
     * define game board (background)
     */
    private Board board;

    /**
     * define ball
     */
    private Ball ball;

    /**
     * define all players
     */
    private Player[] players;

    /**
     * define center area
     */
    private CenterArea centerArea;

    /**
     * define canvas for drawing
     */
    private DrawCanvas canvas;

    /**
     * define canvas width
     */
    private int canvasWidth;

    /**
     * define canvas height
     */
    private int canvasHeight;

    /**
     * define the size of the window
     */
    private Dimension WindowSize;

    /**
     * GameView Constructor, initialize all members
     * @param width = canvas width
     * @param height = canvas height
     * @param player1 = player1 info
     * @param player2 = player2 info
     */
    public GameView(int width, int height, String player1, String player2){
        super("Complete Pong!");
        canvasWidth = width;
        canvasHeight = height+30;
        canvas = new DrawCanvas();
        WindowSize = new Dimension(width, height);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display in the center of the screen
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        this.setBounds(x, y, WindowSize.width, WindowSize.height);

        // Get random angle
        Random rand = new Random();
        int randomAngle = rand.nextInt(360);
        while ((randomAngle>45 && randomAngle<135) || (randomAngle>225 && randomAngle<315))
            randomAngle-=45;

        // Model initiation
        ball = new Ball(width/2,height/2,10,6,randomAngle);
        board = new Board(0,0,width,height);
        players = new Player[2];
        players[0] = new Player(player1);
        players[0].add(new Paddle(25,height/2,100));
        players[1] = new Player(player2);
        players[1].add(new Paddle(width-25,height/2,100));
        centerArea = new CenterArea(600,400);

        this.setLayout(new BorderLayout());
        this.setSize(canvasWidth,canvasHeight);
        this.setMinimumSize(new Dimension(canvasWidth,canvasHeight));
        this.add(canvas, BorderLayout.CENTER);
        this.requestFocusInWindow(true);
        this.requestFocus();
        //this.setVisible(true);
    }

    /**
     * start game, make all graphics visible
     */
    public void play(){
        this.setVisible(true);
    }

    /**
     * get game ball
     * @return game ball
     */
    public Ball getBall(){
        return ball;
    }

    /**
     * get game board
     * @return game board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * get all players
     * @return all players in array
     */
    public Player[] getPlayers(){
        return players;
    }

    /**
     * check if there's a winner of the game
     * @return true if a player has reach maximum score
     */
    public boolean isHasWinner() { return players[0].getScores()>=MAX_SCORES || players[1].getScores()>=MAX_SCORES; }

    /**
     * get center area
     * @return center area
     */
    public CenterArea getCenterArea() { return centerArea; }

    /**
     * get canvas height
     * @return canvas height
     */
    public int getCanvasHeight(){ return canvasHeight; }

    /**
     * get canvas width
     * @return canvas width
     */
    public int getCanvasWidth(){ return canvasWidth; }

    /**
     * DrawCanvas as special class to extends JPanel
     * use to paint component on Graphics
     */
    class DrawCanvas extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(isHasWinner())
                showWinner(g);
            else
                draw(g);
        }
    }

    /**
     * method to show the winner of the game
     * @param g = graphic source
     */
    public void showWinner(Graphics g){
        // Show Winner
        String winner = "WINNER!";
        int scoreWinner;
        String playerWinner;
        if(players[0].getScores()>players[1].getScores()){
            scoreWinner = players[0].getScores();
            playerWinner = players[0].getPlayerName();
        } else {
            scoreWinner = players[1].getScores();
            playerWinner = players[1].getPlayerName();
        }
        String showWinner = playerWinner + " : " + scoreWinner;

        // Board
        drawBoard(g);

        // Scores and Player name
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Impact", Font.PLAIN, 100));
        g.drawString(winner,canvasWidth/2-winner.length()*100/2,150);
        g.setFont(new Font("Impact", Font.PLAIN, 75));
        g.drawString(showWinner,canvasWidth/2-showWinner.length()*75/2,260);
    }

    /**
     * method for draw board
     * @param g = graphic source
     */
    private void drawBoard(Graphics g){
        int boardWidth = board.getMaxX() - board.getMinX() - 1;
        int boardHeight = board.getMaxY() - board.getMinY() - 1;
        g.setColor(board.getColorFilled());
        g.fillRect(board.getMinX(), board.getMinY(), boardWidth, boardHeight);
        g.drawRect(board.getMinX(), board.getMinY(), boardWidth, boardHeight);
    }

    /**
     * method for draw all status of the player needed into display
     * @param g = graphic source
     */
    private void drawPlayerStatus(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Impact", Font.PLAIN, 25));
        g.drawString(" " + players[0].getPlayerName(),50,40);
        g.drawString(" " + players[1].getPlayerName(),canvasWidth-100-50,40);
        g.setFont(new Font("Impact", Font.PLAIN, 100));
        g.drawString(" " + players[0].getScores(),50,160);
        g.drawString(" " + players[1].getScores(),canvasWidth-100-50,160);

        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Elapsed 1 : " + players[0].getPaddle().getElapsedTime(),20,canvasHeight-70);
        g.drawString("Elapsed 2 : " + players[1].getPaddle().getElapsedTime(),20,canvasHeight-50);
        g.drawString("Movement 1 : " + players[0].getPaddle().getY(),canvasWidth/2-150,canvasHeight-50);
        g.drawString("Movement 2 : " + players[1].getPaddle().getY(),canvasWidth/2,canvasHeight-50);
    }

    /**
     * method for draw ball
     * @param g = graphic source
     */
    private void drawBall(Graphics g){
        g.setColor(ball.getColor());
        g.fillOval((int)(ball.getX()-ball.getRadius()),(int)(ball.getY()-ball.getRadius()),(int)(2*ball.getRadius()),(int)(2*ball.getRadius()));

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Ball speed : "+ball.getSpeed(),canvasWidth-150,canvasHeight-90);
        g.drawString("Ball x : "+ball.getX(),canvasWidth-150,canvasHeight-70);
        g.drawString("Ball y : "+ball.getY(),canvasWidth-150,canvasHeight-50);
    }

    /**
     * method for draw paddle
     * @param g = graphic source
     */
    private void drawPaddle(Graphics g){
        for(Player player : players){
            Paddle paddle = player.getPaddle();
            g.setColor(paddle.getColor());
            g.fillRect((int)(paddle.getX()-paddle.getWidth()/2),(int)(paddle.getY()-paddle.getLength()/2),(int)paddle.getWidth(),(int)paddle.getLength());
        }
    }

    /**
     * method for draw object on center area
     * @param g = graphic source
     */
    private void drawCenterArea(Graphics g){
        for(int i = 0;i < centerArea.getMatrixHeight();i++){
            for(int j = 0;j < centerArea.getMatrixWidth();j++){
                Cell cell = centerArea.getCell(i,j);
                if (cell!=null) {
                    g.setColor(cell.getColor());
                    float minX, minY, brickLengthShow, brickWidthShow;
                    minX = canvasWidth / 2 - centerArea.getWidth() / 2 + cell.getCellWidth() * j + cell.getCellBorder();
                    minY = cell.getCellLength() * i + cell.getCellBorder();
                    brickLengthShow = cell.getCellLength() - 2 * cell.getCellBorder();
                    brickWidthShow = cell.getCellWidth() - 2 * cell.getCellBorder();
                    g.fillRect((int) (minX), (int) (minY), (int) brickWidthShow, (int) brickLengthShow);
                }
            }
        }
    }

    /**
     * combined method to draw all things needed for the game
     * @param g = graphic source
     */
    public void draw(Graphics g) {
        // Board
        drawBoard(g);

        // Scores and Player name
        drawPlayerStatus(g);

        // Ball
        drawBall(g);

        // Paddle
        drawPaddle(g);

        // Center area
        drawCenterArea(g);
    }
}
