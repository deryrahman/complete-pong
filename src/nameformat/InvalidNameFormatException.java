package nameformat;

/**
 * InvalidNameFormatException class as Exception class
 * class that specially handle exception in-game
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class InvalidNameFormatException extends Exception{
    /**
     * define error code
     */
    private ErrorCode errorCode;

    /**
     * Class constructor, initialize error code
     * @param errorCode = code for errors
     */
    public InvalidNameFormatException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    /**
     * method for debug
     * @return error message
     */
    public String getMessage(){
        return getErrorCode().getMessage();
    }

    /**
     * getter for error code
     * @return error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
