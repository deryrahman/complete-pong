package models;

import nameformat.InvalidNameFormatException;
import nameformat.InvalidNameFormatExceptionCode;

/**
 * Player class as model for player in-game
 * Any information and object used by player
 * is provided and be responsible here
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class Player {
    /**
     * Paddle that player uses to play
     */
    private Paddle paddle;
    /**
     * Player score
     */
    private int scores;
    /**
     * Player score
     */
    private String playerName;

    /**
     * Player constructor, initialize score zero
     * and playername by param
     * @param playerName = player name
     */
    public Player(String playerName){
        try {
            validateName(playerName);
        } catch (InvalidNameFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        scores = 0;
    }

    // getter
    /**
     * get playername
     * @return player's name
     */
    public String getPlayerName(){
        return playerName;
    }
    /**
     * get player score
     * @return player's score
     */
    public int getScores(){
        return scores;
    }
    /**
     * get player paddle
     * @return player's paddle
     */
    public Paddle getPaddle(){
        return paddle;
    }

    /**
     * add player's score by 1
     */
    public void makeScore(){
        scores++;
    }

    /**
     * add player's score by param
     * @param scores = scores added
     */
    public void makeScore(int scores) { this.scores+=scores; }
    /**
     * add a new object for player, used by paddle
     * @param o = new object
     */
    public void add(Object o){
        if(o instanceof Paddle)
            paddle = (Paddle) o;
    }

    /**
     * Validate name. Name must be in alphabetic and under 10 characters
     * @param name = name player
     * @throws InvalidNameFormatException = for exception
     */
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
