package views;

import models.Ball;
import models.Board;
import models.Paddle;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    // model
    Board board;
    Ball ball;
    Paddle[] paddles;

    private DrawCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;

    public GameView(int width, int height){
        super("Complete Pong!");
        canvasWidth = width;
        canvasHeight = height;
        canvas = new DrawCanvas();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(canvasWidth,canvasHeight);
        this.add(canvas, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void add(Object o) {
        if(o instanceof  Ball)
            this.ball = (Ball) o;
        if(o instanceof Board)
            this.board = (Board) o;
        if(o instanceof Paddle[])
            this.paddles = (Paddle[]) o;
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
