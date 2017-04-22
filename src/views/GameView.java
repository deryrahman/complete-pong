package views;

import models.Ball;
import models.Board;
import models.Paddle;
import models.Player;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    // model
    private Board board;
    private Ball ball;
    private Player[] players;

    private DrawCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;

    private Dimension WindowSize;

    public GameView(int width, int height){
        super("Complete Pong!");
        canvasWidth = width;
        canvasHeight = height+30;
        canvas = new DrawCanvas();
        WindowSize = new Dimension(width, height);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        this.setBounds(x, y, WindowSize.width, WindowSize.height);

        ball = new Ball(width/2,height/2,10,5,20);
        board = new Board(0,0,width,height);
        players = new Player[2];
        players[0] = new Player("Player 1");
        players[0].add(new Paddle(25,height/2,100));
        players[1] = new Player("Player 2");
        players[1].add(new Paddle(width-25,height/2,100));

        this.setLayout(new BorderLayout());
        this.setSize(canvasWidth,canvasHeight);
        this.add(canvas, BorderLayout.CENTER);
        this.requestFocusInWindow(true);
        this.requestFocus();
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

    class DrawCanvas extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
            g.drawString("Ball " + ball.toString(), 20, 30);
        }
    }

    public void draw(Graphics g) {
        g.setColor(board.getColorFilled());
        g.fillRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);
        g.drawRect(board.getMinX(), board.getMinY(), board.getMaxX() - board.getMinX() - 1, board.getMaxY() - board.getMinY() - 1);

        g.setColor(ball.getColor());
        g.fillOval((int)(ball.getX()-ball.getRadius()),(int)(ball.getY()-ball.getRadius()),(int)(2*ball.getRadius()),(int)(2*ball.getRadius()));

        for(Player player : players){
            Paddle paddle = player.getPaddle();
            g.setColor(paddle.getColor());
            g.fillRect((int)(paddle.getX()-paddle.getWidth()/2),(int)(paddle.getY()-paddle.getLength()/2),(int)paddle.getWidth(),(int)paddle.getLength());
        }
    }
}
