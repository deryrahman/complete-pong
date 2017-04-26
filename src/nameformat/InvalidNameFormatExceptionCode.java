package nameformat;

/**
 * InvalidNameFormatExceptionCode class implements error code
 * handle information about error message
 * @author Dery Rahman Ahaddienata <13515097@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public enum InvalidNameFormatExceptionCode implements ErrorCode {

    /**
     * define error message
     */
    S_NOT_STRING("Name must be alphabeth"),
    S_ZERO_LENGTH("Name must be filled"),
    S_MAXIMUM("Name length hit the maximum allowed");

    /**
     * member for error message
     */
    private final String message;

    /**
     * Class constructor, initialize error message
     * @param message = error message
     */
    private InvalidNameFormatExceptionCode(String message){
        this.message = message;
    }

    /**
     * method for debug, get error message
     * @return error message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
