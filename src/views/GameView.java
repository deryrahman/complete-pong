package views;

import models.Ball;
import models.Board;
import models.Paddle;
import models.Player;
import centerboard.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameView extends JFrame {
    // model
    private Board board;
    private Ball ball;
    private Player[] players;
    private CenterArea centerArea;
    private Brick brick;
    private BallPowerUp ballPowerUp;
    private PaddlePowerUp paddlePowerUp;

    private DrawCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;

    private Dimension WindowSize;

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
        System.out.println(randomAngle);
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
        centerArea = new CenterArea(200,400);

        this.setLayout(new BorderLayout());
        this.setSize(canvasWidth,canvasHeight);
        this.add(canvas, BorderLayout.CENTER);
        this.requestFocusInWindow(true);
        this.requestFocus();
        //this.setVisible(true);
    }

    public GameView(int width, int height) {
        this(width, height, "Player 1", "Player2");
    }

    public void play(){
        this.setVisible(true);
    }

    public Ball getBall(){
        return ball;
    }
    public Board getBoard(){
        return board;
    }
    public Player[] getPlayers(){
        return players;
    }

    public CenterArea getCenterArea() { return centerArea; }
//    public Brick getBrickArea(){ return brickArea; }
    public BallPowerUp getBallPowerUp() { return ballPowerUp; }
    public PaddlePowerUp getPaddlePowerUp() { return paddlePowerUp; }
    public int getCanvasHeight(){ return canvasHeight; }
    public int getCanvasWidth(){ return canvasWidth; }

    class DrawCanvas extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
//            g.drawString("Ball " + ball.toString(), 20, 30);
            draw(g);
        }
    }

    public void draw(Graphics g) {

        // Board
        g.setColor(board.getColorFilled());
        g.fillRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);
        g.drawRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);

        // Scores
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Impact", Font.PLAIN, 100));
        g.drawString(" " + players[0].getScores(),50,120);
        g.drawString(" " + players[1].getScores(),canvasWidth-100-50,120);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Elapsed 1 : " + players[0].getPaddle().getElapsedTime(),20,canvasHeight-70);
        g.drawString("Elapsed 2 : " + players[1].getPaddle().getElapsedTime(),20,canvasHeight-50);

        g.setColor(ball.getColor());
        g.fillOval((int)(ball.getX()-ball.getRadius()),(int)(ball.getY()-ball.getRadius()),(int)(2*ball.getRadius()),(int)(2*ball.getRadius()));

        for(Player player : players){
            Paddle paddle = player.getPaddle();
            g.setColor(paddle.getColor());
            g.fillRect((int)(paddle.getX()-paddle.getWidth()/2),(int)(paddle.getY()-paddle.getLength()/2),(int)paddle.getWidth(),(int)paddle.getLength());
        }

        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
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
}
