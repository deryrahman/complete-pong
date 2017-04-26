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
        this.setMinimumSize(new Dimension(canvasWidth,canvasHeight));
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
    public int getCanvasHeight(){ return canvasHeight; }
    public int getCanvasWidth(){ return canvasWidth; }

    class DrawCanvas extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(players[0].getScores()>=30 || players[1].getScores()>=30)
                showWinner(g);
            else
                draw(g);
        }
    }

    public void showWinner(Graphics g){
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
        g.setColor(board.getColorFilled());
        g.fillRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);
        g.drawRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);

        // Scores and Player name
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Impact", Font.PLAIN, 100));
        g.drawString(winner,canvasWidth/2-winner.length()*100/2,150);
        g.setFont(new Font("Impact", Font.PLAIN, 75));
        g.drawString(showWinner,canvasWidth/2-showWinner.length()*75/2,260);
    }

    public void draw(Graphics g) {

        // Board
        g.setColor(board.getColorFilled());
        g.fillRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);
        g.drawRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);

        // Scores and Player name
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
