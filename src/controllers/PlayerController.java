package controllers;

import models.Player;
import views.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class PlayerController implements Runnable, KeyListener{
    private static final int UPDATE_RATE = 100;
    private Player player;
    private int playerNumber;
    private Thread t;

    private final Set<Character> pressed = new HashSet<Character>();

    public PlayerController(GameView gameView, int i){
        player = gameView.getPlayers()[i];
        playerNumber = i;
        gameView.addKeyListener(this);
    }

    @Override
    public void run() {
        while (true){
            player.getPaddle().updateMove();
            try{
                Thread.sleep(1000 / UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        pressed.add(keyEvent.getKeyChar());
        for(char c : pressed) {
            if(playerNumber==0){
                switch (keyEvent.getKeyCode()){
                    case KeyEvent.VK_W:
                        player.getPaddle().setSpeedY(-3);
                        break;
                    case KeyEvent.VK_S:
                        player.getPaddle().setSpeedY(3);
                        break;
                }

            } else if (playerNumber==1){
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        player.getPaddle().setSpeedY(-3);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.getPaddle().setSpeedY(3);
                        break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        player.getPaddle().setSpeedY(0);
    }

    public void start(){
        if(t==null){
            t = new Thread(this);
            t.start();
        }
    }
}
