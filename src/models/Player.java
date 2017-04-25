package models;

public class Player {
    private Paddle paddle;
    private int scores;
    private String playerName;

    public Player(String playerName){
        this.playerName = playerName;
        scores = 0;
    }

    // getter
    public String getPlayerName(){
        return playerName;
    }
    public int getScores(){
        return scores;
    }
    public Paddle getPaddle(){
        return paddle;
    }

    public String toString(){
        return playerName + " " + scores;
    }
    public void makeScore(){
        scores++;
    }
    public void makeScore(int scores) { this.scores+=scores; }
    public void add(Object o){
        if(o instanceof Paddle)
            paddle = (Paddle) o;
    }
}
