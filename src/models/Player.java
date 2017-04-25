package models;

import nameformat.InvalidNameFormatException;
import nameformat.InvalidNameFormatExceptionCode;

public class Player {
    private Paddle paddle;
    private int scores;
    private String playerName;

    public Player(String playerName){
        try {
            validateName(playerName);
        } catch (InvalidNameFormatException e) {
            System.out.println(e.getMessage());
        }
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

    private void validateName(String name) throws InvalidNameFormatException{

        if(name.length()==0)
            throw new InvalidNameFormatException(InvalidNameFormatExceptionCode.S_ZERO_LENGTH);
        if(name.length()>10)
            throw new InvalidNameFormatException(InvalidNameFormatExceptionCode.S_MAXIMUM);
        if (name.matches("[a-zA-Z]+")){
            this.playerName = name;
        } else {
            throw new InvalidNameFormatException(InvalidNameFormatExceptionCode.S_NOT_STRING);
        }
    }
}
