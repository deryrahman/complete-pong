package views;

import models.Ball;
import models.Board;
import models.Paddle;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final int UPDATE_RATE = 100;
    private Ball ball;
    private Board board;
    private Paddle[] paddles = new Paddle[2];

    private DrawCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;

    public GameView(int width, int height){
        super("Pong Game");
        canvasWidth = width;
        canvasHeight = height;

        ball = new Ball(canvasWidth/2,canvasHeight/2,10,5,20);
        board = new Board(0,0,canvasWidth,canvasHeight);
        paddles[0] = new Paddle(25,canvasHeight/2,100);
        paddles[1] = new Paddle(canvasWidth-25,canvasHeight/2,100);
        canvas = new DrawCanvas();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(canvasWidth,canvasHeight);

        this.add(canvas, BorderLayout.CENTER);

        this.setVisible(true);
        gameStartShow();
    }

    public void gameStartShow(){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    ball.move(board,paddles);
                    repaint();
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
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

        for(Paddle paddle : paddles){
            g.setColor(paddle.getColor());
            g.fillRect((int)(paddle.getX()-paddle.getWidth()/2),(int)(paddle.getY()-paddle.getLength()/2),(int)paddle.getWidth(),(int)paddle.getLength());
        }
    }
}
